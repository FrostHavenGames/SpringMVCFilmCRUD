<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Your New Film</title>
</head>
<body>
<%-- <%@ include file="result" %> --%>
<h1>Create Your New Film</h1>
<br>
<form action=createNewFilm.do method="POST">
 	Input Data to Create A New Film:
 	<br>
 	Film Title(String):
 	<input type="text" name="title"/><br>
 	Film Description(String):
 	<input type="text" name="description"/><br>
 	Film Release Year(Integer):
 	<input type="text" name="releaseYear"/><br>
 	Film Language Id(Integer):
 	<input type="text" name="languageId"/><br>
 	Film Rental Duration(Integer):
 	<input type="text" name="rentalDuration"/><br>
 	Film Rental Rate(Double):
 	<input type="text" name="rentalRate"/><br>
 	Film Length(Integer):
 	<input type="text" name="length"/><br>
 	Film Replacement Cost(Double):
 	<input type="text" name="replacementCost"/><br>
 	Film Rating(String):
 	<input type="text" name="rating"/><br>
 	Film Special Feature(String):
 	<input type="text" name="specialFeature"/><br>
 	<input type="submit" value="Create a  Film" />
 </form>
</body>
</html>