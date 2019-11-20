<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | TDT Mobile Store System Management</title>
        <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="../static/images/icons/favicon.ico" th:href="@{/images/icons/favicon.ico}"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../static/vendor/bootstrap/css/bootstrap.min.css" th:href="@{/vendor/bootstrap/css/bootstrap.min.css}">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../static/fonts/font-awesome-4.7.0/css/font-awesome.min.css" th:href="@{/fonts/font-awesome-4.7.0/css/font-awesome.min.css}">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../static/vendor/animate/animate.css" th:href="@{/vendor/animate/animate.css"}">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="../static/vendor/css-hamburgers/hamburgers.min.css" th:href="@{/vendor/css-hamburgers/hamburgers.min.css}">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../static/vendor/select2/select2.min.css" th:href="@{/vendor/select2/select2.min.css}">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../static/css/util.css" th:href="@{/css/util.css}">
	<link rel="stylesheet" type="text/css" href="../static/css/main.css" th:href="@{/css/main.css}">
<!--===============================================================================================-->
    </head>

    <body>
        <h1>TDT Mobile Store System Management</h1>
        <form action="${pageContext.request.contextPath}/LoginController" method = "POST">
            Username: <input type = "text" name = "tbUsername" value = "" /><br/>
            Password: <input type = "password" name = "tbPassword" value = "" /><br/>
            <input type = "submit" value = "LogIn" name = "action" />
        </form>
    </body>
</html>
