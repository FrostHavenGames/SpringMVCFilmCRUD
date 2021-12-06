<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" content="text/html; charset=UTF-8">
<title>Update A Film You Want</title>
</head>
<body>

<h1>Update A Film You Want</h1>

<br>
<form action=updateFilm.do method="GET">
 	give the film Id to update:
 	<input type="text" name="filmId"/>
 	<input type="submit" value="Update Film Data " />
 </form>
</body>
</html>