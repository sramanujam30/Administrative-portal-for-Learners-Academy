<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Classes</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body style="background-image: url('css/background.jpg');">
	<div id="page">
		<jsp:include page="menuoptions.jsp" />


		<div id="wrapper">

			<div id="header">
				<h3>Classes</h3>
			</div>
		</div>


		<div id="container">

			<div id="content">

				<table>

					<tr>

						<th>Section</th>
						<th>Subject</th>
						<th>Teacher</th>
						<th>Time</th>
						<th>List of Students</th>

					</tr>

					<c:forEach var="tempClass" items="${CLASSES_LIST }">
						<tr>

							<c:url var="tempLink" value="AdminControllerServlet">
								<c:param name="choice" value="ST_LIST" />
								<c:param name="classId" value="${tempClass.classID }" />
								<c:param name="section" value="${tempClass.sectionID }" />
								<c:param name="subject" value="${tempClass.subjectName }" />
							</c:url>

							<td>${tempClass.sectionID}</td>
							<td>${tempClass.subjectName}</td>
							<td>${tempClass.teacherName}</td>
							<td>${tempClass.classTime}</td>
							<td><a href="${tempLink }">List of Students</a></td>




						</tr>


					</c:forEach>

				</table>
			</div>
		</div>
	</div>

</body>
</html>