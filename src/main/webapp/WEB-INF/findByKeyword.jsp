<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find By Film Keyword</title>
</head>
<body>
<h1>Find By Film Keyword</h1>

<br>
<form action=findByKeyword.do method="GET">
 	FilmKeyword:
 	<input type="text" name="filmkeyword"/>
 	<input type="submit" value="Get Film Data " />
 </form>
</body>
</html>