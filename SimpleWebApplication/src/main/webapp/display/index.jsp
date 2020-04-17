<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/buttonStyle.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/displayIndex.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>User Records</title>

</head>

<body>
	<div class="header">
		<h1>List of all Registered Users</h1>
	</div>

	<div align="center">
		<table id="users">
			<thead>
				<tr>
					<th>Name</th>
					<th>Surname</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${data}" var="entry">
					<%-- <tr	onclick="window.open('${pageContext.request.contextPath }/displayUsersServlet?id=${entry.key}')"> --%>
					<tr	onclick="window.location='${pageContext.request.contextPath }/displayUsersServlet?id=${entry.key}'">
						<td>${entry.value.getName()}</td>
						<td>${entry.value.getSurname()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form method="post"
			action="${pageContext.request.contextPath }/displayUsersServlet">
			<input type="submit" class="button buttonCol" name="backFromList"
				value="Back" formnovalidate></input>
		</form>
	</div>

	<div class="toTop">
		<button onclick="topFunction()" id="topBtn" title="Go to top"
			class="fa fa-home"></button>
		<script src="scripts/goToTop.js"></script>
	</div>
</body>
</html>