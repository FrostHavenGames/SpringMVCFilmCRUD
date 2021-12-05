<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<title>Delete A Film You Don't Like</title>
</head>
<body>

<h1>Delete A Film You Don't Like</h1>

<br>
<form action=deleteFilm.do method="GET">
 	give the film Id to delete:
 	<input type="text" name="filmId"/>
 	<input type="submit" value="Delete Film Data " />
 </form>
</body>
</html>