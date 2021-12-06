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
 	<input type="text" name="Film Title"/><br>
 	Film Description(String):
 	<input type="text" name="Film Description"/><br>
 	Film Release Year(Integer):
 	<input type="text" name="Film Release Year"/><br>
 	Film Language Id(Integer):
 	<input type="text" name="Film Language Id"/><br>
 	Film Rental Duration(Integer):
 	<input type="text" name="Film Rental Duration"/><br>
 	Film Rental Rate(Double):
 	<input type="text" name="Film Rental Rate"/><br>
 	Film Length(Integer):
 	<input type="text" name="Film Length"/><br>
 	Film Replacement Cost(Double):
 	<input type="text" name="Film Replacement Cost"/><br>
 	Film Rating(String):
 	<input type="text" name="Film Rating"/><br>
 	Film Special Feature(String):
 	<input type="text" name="Film Special Feature"/><br>
 	<input type="submit" value="Create a  Film" />
 </form>
</body>
</html>