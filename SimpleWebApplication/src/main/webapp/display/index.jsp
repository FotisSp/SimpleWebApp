<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

<style>
table, th, td {
	border: 2px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: center;
}

table#t01 tr:nth-child(even) {
  background-color: #eee;
}
table#t01 tr:nth-child(odd) {
 background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}

td a {
    display:block;
    width:100%;
}

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

.myclass{
    display:inline-block;
    width: 100%;
    height: 100%;
    cursor: pointer;
}
</style>

<title>JSP List Users Records</title>

</head>

<body>
	<div align="center">

		<table style="width: 50%" id="t01">
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
			</tr>
			<c:forEach items="${data}" var="entry">
				<tr>
					<td>
					<a
						href="${pageContext.request.contextPath }/displayUsersServlet?id=${entry.key}">
							${entry.value.getName()} </a>
					</td>
					<td>
					<input class='myclass' type='button' value="${entry.value.getSurname()}"/>
					
					</td>
				</tr>
			</c:forEach>
		</table>

		<form method="post" action="${pageContext.request.contextPath }/displayUsersServlet">
			<input type="submit" class="button buttonCol" name="back" value="Back" formnovalidate></input>
		</form>
	</div>
</body>
</html>