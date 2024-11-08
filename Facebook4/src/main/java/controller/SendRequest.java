package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Friend;
import model.Users;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.DBHandlerFriends;

public class SendRequest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sender=session.getAttribute("email").toString();
		String receiver = request.getParameter("receiver");
		int stat=0;
		int fid = 0;
		SessionFactory  sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Friend.class).buildSessionFactory();
		Session s = sf.getCurrentSession();
		Transaction t = s.beginTransaction();
		DBHandlerFriends fdb = new DBHandlerFriends();
		Friend friends = new Friend(fid, sender, receiver, stat);
		fdb.save(friends);
		
		t.commit();
		response.sendRedirect("welcome");
		
	}

}
