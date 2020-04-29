<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/buttonStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/indexStyle.css">

<title>Simple Web App</title>
</head>

<body>
	<div class="header">
		<h1>Simple Web App</h1>
		<p>
			A simple web application using Tomcat and MySQL.
		</p>
	</div>

	<form action="displayUsersServlet" method="post">
		<p style="text-align: center;">
			<button formmethod="post" type="submit" class="button buttonCol"
				name="display">Display Users</button>
		</p>
	</form>

	<form action="welcomeServlet" method="post">
		<p style="text-align: center;">
			<button formmethod="post" type="submit" class="button buttonCol"
				name="register">Register New User</button>
		</p>
	</form>

	<input type="image" src="Images/github.png" class="github" 
		onclick="window.open('https://github.com/FotisSp/SimpleWebApp','_blank')" />
	<div id="MyClockDisplay" class="clock" onload="showTime()"></div>
	<script src="scripts/clock.js"></script>
</body>
