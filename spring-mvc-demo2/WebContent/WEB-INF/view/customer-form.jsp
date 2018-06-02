<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Customer order form</title>
		
		<style>
			.error {
				color: red
			}
		</style>
	</head>
	<body>
		Asterisk fields are required
		<br /><br />
		<form:form action="processForm" modelAttribute="customer">
			First name: <form:input path="firstName" />
			<br /><br />
			Last name (*): <form:input path="lastName" />
			<form:errors path="lastName" cssClass="error" />
			<br /><br />
			
			Free passes (0-10): <form:input path="freePasses" />
			<form:errors path="freePasses" cssClass="error" />
			<br /><br />
			
			Postal code: <form:input path="postalCode" />
			<form:errors path="postalCode" cssClass="error" />
			<br /><br />
			
			Course code: <form:input path="courseCode" />
			<form:errors path="courseCode" cssClass="error" />
			<br /><br />

			<!--Country:
			<form:select path="country" >
				<form:options items="${costumer.countryOptions}" />
			</form:select>
			<br /><br /> -->
			
			<!-- Programming language
			Java <form:radiobutton path="favoriteLanguage" value="Java" />
			JavaScript <form:radiobutton path="favoriteLanguage" value="JavaScript" />
			
			<br /><br /> -->
			
			<input type="submit" value="Submit">
		</form:form>
	</body>
</html>