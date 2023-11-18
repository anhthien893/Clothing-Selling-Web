<%-- 
    Document   : login
    Created on : Jun 20, 2023, 4:02:05 PM
    Author     : laptop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="all" href="css/styleLogin.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div class="container">
            <div class="logo">
                <img src="image/logo.png">
            </div>
            <form class="form-group" action="MainController" method="POST">
                <h1>Sign in</h1>
                <input type="text" name="ID" class="form-control" placeholder="ID or Email" required=""/></br>
                <input type="password" name="password" class="form-control" placeholder="Password" required=""/></br>
                <input type="submit" name="action" class="button" value="Login"/>
                <input type="reset" value="Reset" class="button"/></br>
                <p><a class="link" href="MainController?action=CreatePage">Sign up?</a></p></br>
                ${sessionScope.LOGIN_ERROR}
            </form>
        </div>
    </body>
</html>
