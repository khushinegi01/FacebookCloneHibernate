package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
        Session session = sf.openSession();
        
      
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        
       
        if (email.isEmpty()) {
            response.sendRedirect("index.jsp?message=missing_email");
            return;
        }
        
        if (password.isEmpty()) {
            response.sendRedirect("index.jsp?message=missing_password");
            return;
        }
        
        
        Transaction tx = session.beginTransaction();
        Users user = session.get(Users.class, email); 
        tx.commit();
        session.close();
        
        if (user == null) {
       
            response.sendRedirect("index.jsp?message=invalid_email");
        } else if (user.getPassword().equals(password)) {
            
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("email", email);
            response.sendRedirect("welcome");
        } else {
           
            response.sendRedirect("index.jsp?message=invalid_password");
        }
    }
	}


