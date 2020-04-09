<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<title>Simple Web Application</title>
</head>
<body>

	<h3>Register User</h3>
	${msg }
	<form method="post" action="${pageContext.request.contextPath }/user">
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><input type="text" name="surname"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><select id="gender" name="gender">
					<option value="male">Male</option>
					<option value="female">Female</option>
					<option value="other">Other</option>
				</select></td>
			</tr>
			<tr>
				<td>Birthdate</td>
				<td><input type="date" name="birthdate" required></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form>

</body>
</html>