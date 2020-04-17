<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/userDataStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/buttonStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/indexStyle.css">
	
<title>User Data</title>
</head>
<body>
	<div class="header">
		<h1>User info</h1>
	</div>

	<form method="post"
		action="${pageContext.request.contextPath}/displayUsersServlet">
		<div align="center">
			<table id="user">
				<tr>
					<td>Name</td>
					<td>${data.getName()}</td>
				</tr>
				<tr>
					<td>Surname</td>
					<td>${data.getSurname()}</td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>${data.getGender()}</td>
				</tr>
				<tr>
					<td>Birthdate</td>
					<td>${data.getBirthdate()}</td>
				</tr>
				<tr>
					<td>Home Address</td>
					<td>${data.getHomeAddress()}</td>
				</tr>
				<tr>
					<td>Work Address</td>
					<td>${data.getWorkAddress()}</td>
				</tr>
			</table>
			<input type="submit" class="button buttonCol" name="backFromUser" value="Back">
		</div>
	</form>

</body>
</html>