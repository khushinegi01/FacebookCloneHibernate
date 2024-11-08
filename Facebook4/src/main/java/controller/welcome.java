package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Friend;
import model.Wposts;

import java.io.IOException;
import java.util.ArrayList;



import dao.DBHandlerFriends;
import dao.DBHandlerWpost;




public class welcome extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email ;
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("email") == null) {
		    response.sendRedirect("index.jsp"); 
		    return;
		}
		email= session.getAttribute("email").toString();
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
		DBHandlerFriends fdb = new DBHandlerFriends();
		DBHandlerWpost wdb = new DBHandlerWpost();
		ArrayList<Friend> pfriends = fdb.getPendingRequests(email);
		request.setAttribute("pfriends", pfriends);
		ArrayList<Wposts> wposts = wdb.getWposts(email);
		request.setAttribute("wposts", wposts);
		ArrayList<String> afriends = fdb.getFriends(email);
		request.setAttribute("afriends", afriends);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
