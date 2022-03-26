<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Teachers</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body style="background-image: url('css/background.jpg');">
	<div id="page">
		<jsp:include page="menuoptions.jsp" />


		<div id="wrapper">

			<div id="header">
				<h3>Teachers</h3>
			</div>
		</div>


		<div id="container">

			<div id="content">

				<table>

					<tr>
						<th>Teacher ID</th>
						<th>First Name</th>
						<th>Last Name</th>

					</tr>

					<c:forEach var="tempTeacher" items="${TEACHERS_LIST }">
						<tr>

							<td>${tempTeacher.teacherID}</td>
							<td>${tempTeacher.firstName}</td>
							<td>${tempTeacher.lastName}</td>



						</tr>


					</c:forEach>

				</table>
			</div>
		</div>
	</div>

</body>
</html>