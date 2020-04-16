<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/userDataStyle.css">
	
<title>User Data</title>
</head>
<body>
	<h2>User info</h2>

	<form method="post"
		action="${pageContext.request.contextPath}/displayUsersServlet">
		<table>
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
			<tr>
				<td><input type="submit" class="button buttonCol"
					name="backFromUser" value="Back"></td>
			</tr>
		</table>
	</form>

</body>
</html>