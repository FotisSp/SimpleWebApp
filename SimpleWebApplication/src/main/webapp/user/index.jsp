<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<style>
.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.buttonCol {background-color: #008CBA;}

</style>

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
				<td>Home Address</td>
				<td><input type="text" name="homeAddress"></td>
			</tr>
			<tr>
				<td>Work Address</td>
				<td><input type="text" name="workAddress"></td>
			</tr>
			<tr>
				<td><input type="submit" class="button buttonCol" name="register" value="Register"></td>
				<td><input type="submit" class="button buttonCol" name="back" value="Back"
					formnovalidate></td>
			</tr>
		</table>
	</form>

</body>
</html>