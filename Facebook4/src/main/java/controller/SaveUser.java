package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



import dao.DBHandlerUser;

@MultipartConfig
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int birthDate = Integer.parseInt(request.getParameter("birthDate"));
		String birthMonth = request.getParameter("birthMonth");
		int birthYear =Integer.parseInt(request.getParameter("birthYear"));
		String gender = request.getParameter("gender");
		
		
		
		
		
		Part profile = request.getPart("profile");
        String relativePath;

        String uploadPath = getServletContext().getRealPath("profile");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

       
        if (profile != null && profile.getSize() > 0) {
            String fileName = System.currentTimeMillis() + ".jpg"; 
            relativePath = "profile/" + fileName;
            File file = new File(uploadPath, fileName);

            // Save uploaded image
            try (FileOutputStream fos = new FileOutputStream(file); InputStream is = profile.getInputStream()) {
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            
            String defaultImage;
            if ("male".equals(gender)) {
                defaultImage = "DefaultImage/mdummy.jpg"; 
            } else if ("others".equals(gender)) {
                defaultImage = "DefaultImage/odummy.jpg"; 
            } else {
                defaultImage = "DefaultImage/gdummy.jpg"; 
            
            }

            
            relativePath = "profile/" + email + "/pp.jpg"; 
            File userDir = new File(uploadPath, email);
            if (!userDir.exists()) {
                userDir.mkdir();
            }

            try (FileInputStream fis = new FileInputStream(new File(getServletContext().getRealPath(defaultImage)));
                 FileOutputStream fos = new FileOutputStream(new File(userDir, "pp.jpg"))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		
		
		
		
		Users user = new Users( firstName, lastName , email, password, birthDate, birthMonth, birthYear, gender, relativePath);
		System.out.println("Saved profile image at: " + relativePath);
		DBHandlerUser udb = new DBHandlerUser();
		udb.saveUser(user);
		response.sendRedirect("index.jsp");
	}

}
