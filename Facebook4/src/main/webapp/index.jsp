<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="container">
        <div class="row">
            <div class="col-md-1">
                <!-- just for extra margin in the left  -->
            </div>
            <div class="col-md-5 col-sm-12 mt-4">
                <br><br><br><br><br><br><br><br>
                <h1 class="logo">facebook</h1>
                <p class="sub-head">Facebook helps you connect and share with the people in your life.</p>
            </div>
            <div class="col-md-5 col-sm-12 d-flex justify-content-center align-items-center mt-4">
                <br><br><br><br><br><br>
                <div class="panel panel-default box">
                    <div class="panel-body">
                        <form action="Login" method="post">
                            <div class="row">
                              <div class="col">
                                <input type="text" class="form-control form-control-lg textbox" placeholder="Email address" name="email">
                              </div>
                              <div class="col">
                                <input type="password" class="form-control form-control-lg textbox" placeholder="Password" name="password">
                              </div>
                              <input type="submit" class="btn btn-primary btn-block login" value="Log in">
                              <a href="#">
                              <p class="text-center aflogin">Forgotten password?</p>
                              </a>
                              <hr>
                              <div class="text-center">
                                <button class="btn btn-success" id="signform" type="button" value="Create new account">Create new account</button>
                                
                              </div>
                            </div>
                          </form>
                          <%
                          if(request.getParameter("message")!=null)
                        	  request.getParameter("message");
                          %>
                    </div>
                </div>
                <div id="myModal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<h2>Sign Up</h2>
				<p>It's quick and easy.</p>
				<form action="SaveUser" method="post" enctype="multipart/form-data">
					<div class="input-group">
						<input type="text" id="firstName" name="firstName" placeholder="First Name">
						<input type="text" id="lastName" name="lastName" placeholder="Last Name" >
					</div>
					<input type="email" id="email" name="email" placeholder="Mobile number or email address"><br>
					<input type="password" id="password" name="password" placeholder="New password"><br>
				    <label for="dob">Date of birth</label><br>
					<div class="dob-group">
						<input type="number" id="birthDate" name="birthDate" placeholder="DD" min="1" max="31"> 
						<select id="birthMonth" name="birthMonth">
							<option value="Jan">Jan</option>
							<option value="Feb">Feb</option>
							<option value="Mar">Mar</option>
							<option value="Apr">Apr</option>
							<option value="May">May</option>
							<option value="Jun">Jun</option>
							<option value="Jul">Jul</option>
							<option value="Aug">Aug</option>
							<option value="Sep">Sep</option>
							<option value="Oct">Oct</option>
							<option value="Nov">Nov</option>
							<option value="Dec">Dec</option>
						</select> 
						<input type="number" id="birthYear" name="birthYear" placeholder="YYYY" min="1900" max="2024">
					</div>
					<br> <label for="gender">Gender</label><br>
					<div class="gender-group">
						<input type="radio" id="female" name="gender" value="female">
						<label for="female">Female</label> 
						<input type="radio" id="male" name="gender" value="male">
						<label for="male">Male</label> 
						<input type="radio" id="others" name="gender" value="others">
						<label for="male">Others</label>
					</div>
					<br>
					<input type="file" name="profile">
					<input type="submit" class="submit-btn" value="Sign Up">
				</form>
			</div>
		</div>
		
	
	     
                
            </div>

            <div class="col-md-1">
                <!-- just for extra margin in the right  -->
            </div>            
        </div>
    </div>
<script src="script.js"></script>  
</body>

</html>