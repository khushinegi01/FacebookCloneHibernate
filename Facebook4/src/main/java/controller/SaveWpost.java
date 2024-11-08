package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Wposts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import dao.DBHandlerWpost;

@MultipartConfig
public class SaveWpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    HttpSession session = request.getSession();
			    String sender = session.getAttribute("email").toString();
			    String message = request.getParameter("message");
			    String dop = new Date().toString();

			    // Get file content:
			    Part newPost = request.getPart("newPost");

			    String fileName = System.currentTimeMillis() + ".jpg";
			    String relativePath = "newPost/" + fileName;

			    String uploadPath = getServletContext().getRealPath("newPost");
			    System.out.println("uploadPath:   " + uploadPath);

			    File uploadDir = new File(uploadPath);
			    if (!uploadDir.exists()) {
			        uploadDir.mkdirs();
			    }

			    File file = new File(uploadPath, fileName);

			    // For uploading image
			    try (FileOutputStream fos = new FileOutputStream(file); InputStream is = newPost.getInputStream()) {
			        byte[] data = new byte[is.available()];
			        is.read(data);
			        fos.write(data);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }

			    // Create a new Wpost object
			    Wposts wpost = new Wposts(0, sender, message, dop, relativePath);
			    
			    // Use DBHandlerWposts to save the post
			    DBHandlerWpost dbHandler = new DBHandlerWpost();
			    dbHandler.save(wpost); // Save the Wpost object using Hibernate

			    // Redirect to welcome page
			    response.sendRedirect("welcome");
			}

	

}
