
<%@page import="model.Wposts"%>
<%@page import="dao.DBHandlerUser"%>
<%@page import="model.Friend"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="style2.css">
<title>Welcome</title>
</head>
<body>
<%
    Users user = null; // Declare the user variable
    String email = (String) session.getAttribute("email");
    ArrayList<Friend> pfriends = (ArrayList<Friend>) request.getAttribute("pfriends");
    ArrayList<String> afriends = (ArrayList<String>) request.getAttribute("afriends");
    ArrayList<Wposts> wposts = (ArrayList<Wposts>) request.getAttribute("wposts");
    
    if (email != null) {
        DBHandlerUser udb = new DBHandlerUser(); // Initialize your DBHandlerUser
        try {
            user = udb.checkUser(email); // Fetch user details by email
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
    }
%>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires", 0);
%>


<nav class="navbar navbar-expand-lg navbar-light ">
		<img class="facebook"
			src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Facebook_logo_%28square%29.png/600px-Facebook_logo_%28square%29.png">
		<a class="navbar-brand welcome" href="#">Welcome  <%= user.getFirstName() %>  <%= user.getLastName() %></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				
			</ul>
		</div>
			<div class="dropdown">
					<img
					src="<%=user.getProfile() %>"
					class="profile" alt="Profile Image"
					style="width: 100px; height: 100px; border-radius:50px;object-fit:cover;" id="profileImage">
			       <div id="myDropdown" class="dropdown-content">
					
                   <button type="button" class="btn" data-toggle="modal" data-target="#myModal">Edit</button>

		
					<br>
					<a href="Logout" class="btn">Logout</a> 
				 </div>  
			</div>
	</nav>
	
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Edit</h4>

				</div>

				<!-- Modal Body -->
				<div class="modal-body">
					<form action="updateuser" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<input type="text" class="form-control" id="firstName"
								value="<%=user.getFirstName()%>" name="firstName">
								<br><br>
							
							<input type="text" class="form-control" id="lastName"
								value="<%=user.getLastName()%>" name="lastName">
						</div>
						<div class="form-group">
						
							<input type="email" class="form-control" id="email"
								value="<%=user.getEmail()%>" name="email" readonly>
						</div>
						<div class="form-group">
							
							<input type="password" class="form-control" id="password"
								value="<%=user.getPassword()%> " name="password" readonly>
						</div>
						
						<label for="password">Date of Birth:-</label> <br> <br>
						<div>
							<label for="number">Date</label> 
							<input type="number"
								class="form-control" id="birthDate"
								value="<%=user.getBirthDate()%>" name="birthDate"><br>
							<br>
						</div>
						<div>
							<label for="number">Month</label> 
							<select id="birthMonth"
								name="birthMonth">
								<option value="Jan"
									<%="Jan".equals(user.getBirthMonth()) ? "selected" : ""%>>Jan</option>
								<option value="Feb"
									<%="Feb".equals(user.getBirthMonth()) ? "selected" : ""%>>Feb</option>
								<option value="Mar"
									<%="Mar".equals(user.getBirthMonth()) ? "selected" : ""%>>Mar</option>
								<option value="Apr"
									<%="Apr".equals(user.getBirthMonth()) ? "selected" : ""%>>Apr</option>
								<option value="May"
									<%="May".equals(user.getBirthMonth()) ? "selected" : ""%>>May</option>
								<option value="Jun"
									<%="Jun".equals(user.getBirthMonth()) ? "selected" : ""%>>Jun</option>
								<option value="Jul"
									<%="Jul".equals(user.getBirthMonth()) ? "selected" : ""%>>Jul</option>
								<option value="Aug"
									<%="Aug".equals(user.getBirthMonth()) ? "selected" : ""%>>Aug</option>
								<option value="Sep"
									<%="Sep".equals(user.getBirthMonth()) ? "selected" : ""%>>Sep</option>
								<option value="Oct"
									<%="Oct".equals(user.getBirthMonth()) ? "selected" : ""%>>Oct</option>
								<option value="Nov"
									<%="Nov".equals(user.getBirthMonth()) ? "selected" : ""%>>Nov</option>
								<option value="Dec"
									<%="Dec".equals(user.getBirthMonth()) ? "selected" : ""%>>Dec</option>
							</select>

						</div>
						
						<br> <br>
						
						<div>
							<label for="number">Year</label> 
							<input type="number"
								class="form-control" id="dobYear" name="birthYear"
								value="<%=user.getBirthYear()%>">
								<br> <br>
						</div>
						<div>
							<label for="text">Gender</label>
							<br> <br> 
							<input type="radio" id="female" name="gender" value="female"
								<%="female".equals(user.getGender()) ? "checked" : ""%>
								required> 
								<label for="female">Female</label> 
							<input type="radio" id="male" name="gender" value="male"
								<%="male".equals(user.getGender()) ? "checked" : ""%> required>
							<label for="male">Male</label>
							 <input type="radio" id="others"
								name="gender" value="others"
								<%="others".equals(user.getGender()) ? "checked" : ""%>>
							<label for="others">Others</label>
						</div>
						<br>
						<label for="number">Change Profile Photo</label>
						<input type="file" name="profile" value="<%=user.getProfile()%>">


						<br> <br>
						<button type="submit" class="btn btn-primary">Edit</button>
						<br> <br>
				
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
				</div>

			</div>
		</div>

		<!-- Modal Footer -->
	</div>
	
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-3 pendingRequests">
			Pending Requests
<%
    if (pfriends != null && !pfriends.isEmpty()) {
        for (Friend pfr : pfriends) {
            DBHandlerUser udb = new DBHandlerUser();
             user = udb.checkUser(pfr.getSender()); // Fetch user details

            if (user != null) { // Ensure user is found
%>
                <div class="friend-request">
                    <div class="top-section">
                        <div class="profile-pic">
                            <img
                                src="<%= user.getProfile() %>" 
                                alt="Profile Picture" style="width: 70px; height: 70px; border-radius:50%; object-fit:cover;">
                        </div>
                        <div class="details">
                            <h4><%= user.getFirstName() %></h4> 
                            <a style="color: green" href="UpdateRequest?stat=1&fid=<%= pfr.getFid() %>">Accept</a>
                            <a style="color: red" href="UpdateRequest?stat=2&fid=<%= pfr.getFid() %>">Reject</a>
                        </div>
                    </div>
                    <div class="button"></div>
                </div>
<%
            }
        }
    } else {
%>
        <h4>No Pending Requests</h4>
<%
    }
%>

			</div>
	<div class="col-6 sendRequests">
				<p>Send Requests</p>
				<div>

					<form action="SendRequest" method="post">

						<input type="email" name="receiver" placeholder="Make New Friends">
						<input type="submit" value="Send Request">

					</form>

				</div>
				
			<!-- 	for writing wall post code -->
				<hr>
				<p style="font-size: 20px">Write A Post</p>
				<div>

					<form action="SaveWpost" method="post" enctype="multipart/form-data">

						<textarea type="text" class="form-control" name="message" placeholder="Share your thoughts " rows="3"></textarea>
						<br> 
						
						<input type="file" name="newPost">
						<input type="submit" class="btn " style="background-color: #4469b0; color:white" value="Share">
	
					</form>

				</div>
				<br>
				<%
				if (wposts != null) {
					for (Wposts wpost : wposts) {
						 DBHandlerUser udb = new DBHandlerUser();
						 user = udb.checkUser(wpost.getSender());
				%> 
				<br>
				<div class="alignCardtoCenter">
					<div class="card" style="width: 30rem;">
						<div class="card-body"> 
							<div class="profile-pic">
							<img
								src="<%= user.getProfile() %>" 
								alt="Profile Picture" style="width: 70px; height: 70px; border-radius:50px;object-fit:cover;">
						   </div>
							 <h5 class="card-title"><%=user.getFirstName()%></h5>
							<h6 class="card-subtitle mb-2 text-muted"><%=wpost.getDop()%></h6>
							<h4 class="card-text"><%=wpost.getMessage()%></h4>
							<img src="<%=wpost.getNewPost() %>" style="width: 10rem; height: 10rem; object-fit: cover; border-radius: 10px;">
							<br> 
							<a href="#" class="card-link" style="color: #ff1b16de">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
                            <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1"/>
                            </svg> Like </a> 
							<a href="#" class="card-link" style="color:#4469b0">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left-dots-fill" viewBox="0 0 16 16">
                            <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H4.414a1 1 0 0 0-.707.293L.854 15.146A.5.5 0 0 1 0 14.793zm5 4a1 1 0 1 0-2 0 1 1 0 0 0 2 0m4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0m3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
                            </svg> Comment </a>
						</div>
					</div>
				</div>
			<br>

					 	<%
						}
						}
						%> 

		</div>
		<div class="col-3 friends">


			Friends
			<%
		    if (afriends != null) {
		 	for (String fr : afriends) {
		 		DBHandlerUser udb = new DBHandlerUser();
		  		 user = udb.checkUser(fr);
		   %>

			<div class="friend-request">
				<div class="top-section">
					<div class="profile-pic">
						<img
								src="<%= user.getProfile() %>"
								alt="Profile Picture" style="width: 70px; height: 70px; border-radius:50px;object-fit:cover;">
					</div>
					<div class="details">
						<h4><%=user.getFirstName()%></h4>
					</div>
				</div>
			</div>

			<%
			}
			} else {
			%>
			<h4>No Friends</h4>
			<%
			}
			%>
			</div>
	</div>
	</div>
<script src="script.js"></script>
</body>
</html>