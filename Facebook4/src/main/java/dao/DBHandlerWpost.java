package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import model.Wposts;

public class DBHandlerWpost {
	

	    private SessionFactory sessionFactory;

	    public DBHandlerWpost() {
	        
	    	sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Wposts.class).buildSessionFactory();
	    }

	    // Method to save a WPost
	    public void save(Wposts wpost) {
	        Transaction transaction = null;
	        try (Session session = sessionFactory.openSession()) {
	            transaction = session.beginTransaction();
	            session.save(wpost); // Save the WPost object
	            transaction.commit();
	            System.out.println("Post saved successfully.");
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            System.out.println("Error in saving wpost: " + e.getMessage());
	        }
	    }

	    // Method to get WPosts for a given email
	    public ArrayList<Wposts> getWposts(String email) {
	        ArrayList<Wposts> wposts = new ArrayList<>();
	        DBHandlerFriends fdb = new DBHandlerFriends();
	        ArrayList<String> friends = fdb.getFriends(email);
	        friends.add(email); 
	        
	        try (Session session = sessionFactory.openSession()) {
	            String hql = "FROM Wposts ORDER BY wid DESC";
	            Query<Wposts> query = session.createQuery(hql, Wposts.class);
	            List<Wposts> allPosts = query.getResultList();
	            
	            for (Wposts post : allPosts) {
	                if (friends.contains(post.getSender())) {
	                    wposts.add(post);
	                }
	            }
	        } catch (Exception ex) {
	            System.out.println("Error in getWpost: " + ex.getMessage());
	        }
	        
	        return wposts;
	    }

	    // Close the SessionFactory when the application shuts down
	    public void shutdown() {
	        sessionFactory.close();
	    }
	}

