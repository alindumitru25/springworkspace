<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo home page</title>
</head>
<body>
	<h2>
		Welcome to the company page!
	</h2>
	<hr>
	
	<!-- Display user name & role -->
	<p>
		User: <security:authentication property="principal.username" />
		<br /><br />
		Role(s): <security:authentication property="principal.authorities" />
	</p>
	<hr>
	
	<hr>

	<security:authorize access="hasRole('MANAGER')">
		<!-- Add link to point to /leaders -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
			(Only for Manager)
		</p>
		<hr>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<!-- Add link to point to /systems -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">Admin access</a>
			(Only for Admin)
		</p>
		<hr>
	</security:authorize>
	
	<!-- Logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>
