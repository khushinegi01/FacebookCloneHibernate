package dao;




import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Friend;

public class DBHandlerFriends {

    private SessionFactory sf;

    public DBHandlerFriends() {
      
        sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Friend.class).buildSessionFactory();
    }
			
	
    
    
    public void save(Friend friend) {
        Transaction t = null;
        try (Session s = sf.openSession()) {
            // Check if a friend request already exists
            String hql = "FROM Friend WHERE (sender = :sender AND receiver = :receiver) OR (sender = :receiver AND receiver = :sender)";
            Query<Friend> query = s.createQuery(hql, Friend.class);
            query.setParameter("sender", friend.getSender());
            query.setParameter("receiver", friend.getReceiver());
            
            List<Friend> existingRequests = query.getResultList();
            
            if (existingRequests.isEmpty()) { // Proceed only if no existing requests
                t = s.beginTransaction();
                s.save(friend);
                t.commit();
                System.out.println("Friend request sent successfully.");
            } else {
                System.out.println("Friend request already exists.");
            }
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
    }

			
    public void changeStat(int fid, int stat) {
        Transaction transaction = null;
        try (Session session = sf.openSession()) {
            transaction = session.beginTransaction();
            
            // Fetch the Friend object based on the fid
            Friend friend = session.get(Friend.class, fid);
            if (friend != null) {
                friend.setStat(stat); // Update the stat
                session.update(friend); // Persist the changes
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("No friend found with fid: " + fid);
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    
    
    public ArrayList<Friend> getPendingRequests(String email)
	{
		ArrayList<Friend> pfriends = new ArrayList<>();
		try (Session s = sf.openSession())
		{
			
			String hql = "FROM Friend WHERE receiver = :email AND stat = 0";
	        Query<Friend> query = s.createQuery(hql, Friend.class);
	        query.setParameter("email", email);
	        pfriends = (ArrayList<Friend>) query.getResultList();
				 
				
			}
	
		
		catch(Exception ex)
		{
			System.out.println("Error:-"+ex.getMessage());
		}
		
	return pfriends;
	}
    
    
    
    public ArrayList<String> getFriends(String email) {
        ArrayList<String> afriends = new ArrayList<>();
        try (Session session = sf.openSession()) {
            String hql = "FROM Friend f WHERE f.stat = 1 AND (f.receiver = :email OR f.sender = :email) ORDER BY f.fid DESC";
            Query<Friend> query = session.createQuery(hql, Friend.class);
            query.setParameter("email", email);
            
            List<Friend> friendsList = query.getResultList();
            for (Friend friend : friendsList) {
                if (email.equals(friend.getSender())) {
                    afriends.add(friend.getReceiver());
                } else {
                    afriends.add(friend.getSender());
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return afriends;
    }

    
}
