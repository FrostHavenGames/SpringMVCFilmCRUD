<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty Film}">
			<ul>

				<c:forEach var="x" items="${Film}">
					<li>${x.title}</li>
					<li>${x.description}</li>
					<li>${x.releaseYear}</li>
					<li>${x.languageId}</li>
					<li>${x.rentalDuration}</li>
					<li>${x.rentalRate}</li>
					<li>${x.length}</li>
					<li>${x.replacementCost}</li>
					<li>${x.rating}</li>
					<li>${x.replacementCost}</li>
					<li>${x.specialFeature}</li>
					<li>${x.categoryName}</li>
					<br>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>