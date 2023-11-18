<%-- 
    Document   : create
    Created on : Jun 29, 2023, 10:48:57 AM
    Author     : laptop
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="all" href="css/styleCreate.css">
        <title>Sign up</title>
    </head>
    <body>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <div class="container">
            <div class="logo">
                <img src="image/logo.png">
            </div>
            <h1>Sign up</h1>
            <form action="MainController" method="POST">
                <div class="form-group">
                    <input type="text" name ="userID" required="" class="form-control" placeholder="UserID"/></br><%=userError.getUserIDError()%></br>
                    <input type="text" name ="fullName" required="" class="form-control" placeholder="Full Name"/></br><%=userError.getFullNameError()%></br>
                    <input type="password" name ="password" required="" class="form-control" placeholder="Password"/></br><%=userError.getPasswordError()%></br>
                    <input type="password" name ="confirm" required="" class="form-control" placeholder="Confirm"/></br><%=userError.getConfirm()%></br>
                    <input type="hidden" name ="roleID" value="CUS" readonly=""/></br>
                    <%=userError.getError()%>
                    <input type="submit" name ="action" value="Create" class="button"/>
                    <input type="reset" value="Reset" class="button"/>
                </div>
            </form>
        </div>
    </body>
</html>
