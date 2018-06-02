<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>
			Costumer confirmation
		</title>
	</head>
	<body>
		The costumer order is confirmed ${customer.firstName} ${customer.lastName}
		<br /><br />
		Country: ${customer.country}
		<br /><br />
		Free passes: ${customer.freePasses}
		<br /><br />
		Postal code: ${customer.postalCode}
		<br /><br />
		Course code: ${customer.courseCode}
	</body>
</html>
