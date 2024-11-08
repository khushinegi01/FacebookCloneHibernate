package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Users;

public class DBHandlerUser {
	private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Users.class)
            .buildSessionFactory();
	
	public void saveUser(Users user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            System.out.println("User saved successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Method to check if a user exists by email
    public Users checkUser(String email) {
        Users user = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Users u WHERE u.email = :email"; // HQL query
            Query<Users> query = session.createQuery(hql, Users.class);
            query.setParameter("email", email);
            
            user = query.uniqueResult(); // Fetch a single user

            if (user == null) {
                System.out.println("No user found for email: " + email);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return user;
    }

    // Close the SessionFactory when the application shuts down
    public static void shutdown() {
        sessionFactory.close();
    }
	}
