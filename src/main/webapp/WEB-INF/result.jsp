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
				<li>Title: ${Film.title}</li>
				<br>
				<li>Description: ${Film.description}</li>
				<br>
				<li>Release Year: ${Film.releaseYear}</li>
				<br>
				<li>Language Id: ${Film.languageId}</li>
				<br>
				<li>Rental Duration: ${Film.rentalDuration}</li>
				<br>
				<li>Rental Rate: ${Film.rentalRate}</li>
				<br>
				<li>Film Length: ${Film.length}</li>
				<br>
				<li>Replacement Cost: ${Film.replacementCost}</li>
				<br>
				<li>Film Rating: ${Film.rating}</li>
				<br>
				<li>Special Feature: ${Film.specialFeature}</li>
				<br>
				<li>Category Name: ${Film.categoryName}</li>
				<br>
				<c:forEach var="x" items="${Film.actors}">
					<li>Actor Name: ${x.firstName} ${x.lastName}</li>
					<br>
				</c:forEach>
			</ul>
			<form action=deleteFilm.do method="GET">
			 	<input type="submit" value="Delete Film Data" />
			</form>
		</c:when>
		<c:otherwise>
			<p>No film found,you have successfully deleted the film</p>
		</c:otherwise>
	</c:choose>
</body>
</html>