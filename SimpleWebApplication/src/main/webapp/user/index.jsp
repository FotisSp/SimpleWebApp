<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/indexStyle.css">
 <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/buttonStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/userIndex.css">
	
<title>Register</title>
</head>
<body>

	<div class="header">
		<h1>Register User</h1>
		<p>Complete the required data to register.</p>
	</div>
	<div class="serverMessage">
		<p>${msg}</p>
	</div>

	<form method="post" action="${pageContext.request.contextPath }/user">
		<div class="registerData">

			<label for="name"><b>Name</b></label>
		    <input type="text" placeholder="Enter Name *" name="name" required>
		
		    <label for="surname"><b>Lastname</b></label>
		    <input type="text" placeholder="Enter Lastname *" name="surname" required>
		
		    <label for="gender"><b>Gender</b></label>
		    <select id="gender" name="gender" required>
			    <option value="none" selected disabled hidden="">
			    	Select Gender *
				</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				<option value="Other">Other</option>
			</select>
			
			<label for="birthdate"><b>Birthdate</b></label>
		    <input type="date" placeholder="Enter Birthdate *" name="birthdate" id="datefield" min="1900-01-01" max="2020-04-17" required>
		    
		    <label for="homeAddress"><b>Home Address</b></label>
		    <input type="text" placeholder="Enter Home Address" name="homeAddress">
		    
		    <label for="workAddress"><b>Work Address</b></label>
		    <input type="text" placeholder="Enter Work Address" name="workAddress">
		    
		    <p>Fields with asterisk (*) are required.</p>

			<div class="buttonContainer">
				<input type="submit" class="button buttonCol" name="back"
					value="Back" formnovalidate>
				<input type="submit" class="button buttonCol" name="register"
					value="Register">	
			</div>
		</div>
	</form>

<script src="../scripts/maxDate.js"></script>
</body>
</html>