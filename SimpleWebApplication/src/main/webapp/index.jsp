<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<h1 style="text-align: center;"><span style="color: #33cccc;"><strong>Simple Web Application</strong></span></h1>
<p>&nbsp;</p>

<form action="displayUsersServlet" method="post">
	<p style="text-align: center;"><button formmethod="post" type="submit" name="display">Display Users</button></p>
</form>

<form action="welcomeServlet" method="post">
    <p style="text-align: center;"><button formmethod="post" type="submit" name="register">Register New User</button></p>
</form>