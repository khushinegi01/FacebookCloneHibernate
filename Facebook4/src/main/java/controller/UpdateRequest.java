package controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.DBHandlerFriends;

;


public class UpdateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int fid= Integer.parseInt(request.getParameter("fid"));
		int stat= Integer.parseInt(request.getParameter("stat"));
		DBHandlerFriends fdb = new DBHandlerFriends();
		fdb.changeStat(fid, stat);
		response.sendRedirect("welcome");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
