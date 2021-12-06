<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film</title>
</head>
<body>
<%-- <%@ include file="result" %> --%>
<h1>Create Your New Film</h1>
<br>
<form action=createNewFilm.do method="POST">
 	Input Data to Update The Film:
 	<br>
 	Film Title(String):
 	<input type="text" name="title" value=${film.title}/><br>
 	Film Description(String):
 	<input type="text" name="description" value=${film.description}/><br>
 	Film Release Year(Integer):
 	<input type="text" name="releaseYear" value=${film.releaseYear}/><br>
 	Film Language Id(Integer):
 	<input type="text" name="languageId" value=${film.languageId}/><br>
 	Film Rental Duration(Integer):
 	<input type="text" name="rentalDuration" value=${film.rentalDuration}/><br>
 	Film Rental Rate(Double):
 	<input type="text" name="rentalRate" value=${film.rentalRate}/><br>
 	Film Length(Integer):
 	<input type="text" name="length" value=${film.length}/><br>
 	Film Replacement Cost(Double):
 	<input type="text" name="replacementCost" value=${film.replacementCost}/><br>
 	Film Rating(String):
 	<input type="text" name="rating" value=${film.rating}/><br>
 	Film Special Feature(String):
 	<input type="text" name="specialFeature" value=${film.specialFeature}/><br>
 	<input type="submit" value="Update a Film" />
 </form>
</body>
</html>