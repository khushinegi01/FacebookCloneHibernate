package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Users;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@MultipartConfig
public class updateuser extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		int birthDate = Integer.parseInt(request.getParameter("birthDate"));
		String birthMonth = request.getParameter("birthMonth").trim();
		int birthYear =Integer.parseInt(request.getParameter("birthYear"));
		String gender = request.getParameter("gender").trim();
		Part profile = request.getPart("profile");
        String relativePath = null;

		String fileName = System.currentTimeMillis() + ".jpg";
		relativePath = "profile/" + fileName;

		String uploadPath = getServletContext().getRealPath("profile");
		System.out.println("uploadPath:   " + uploadPath);
		

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		File file = new File(uploadPath, fileName);

		// for uploading image
		try (FileOutputStream fos = new FileOutputStream(file); InputStream is = profile.getInputStream()) {

			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		SessionFactory  sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
		Session s = sf.getCurrentSession();
		Transaction t = s.beginTransaction();
		Users user = new Users( firstName, lastName , email, password, birthDate, birthMonth, birthYear, gender, relativePath);
		s.update(user);
		t.commit();
		
		response.sendRedirect("welcome");
	}

}
