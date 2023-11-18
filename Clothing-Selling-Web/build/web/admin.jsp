<%-- 
    Document   : admin
    Created on : Jun 20, 2023, 4:04:28 PM
    Author     : laptop
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.Product"%>
<%@page import="sample.shopping.ShoppingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Clicker+Script&family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="css/styleAd.css">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <div class="container">
            <div class="header">
                <div class="logo">
                    <img src="image/logo.png">
                </div>
                <div class="quotes">Hi, <%=loginUser.getName()%></div>
                <form action="MainController" method="POST">
                    <input class="button" type="submit" name="action" value="Logout">
                </form>
            </div>
        </div>
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        ShoppingDAO d = new ShoppingDAO();
                        List<Product> list = d.getlistProduct();
                        for (Product pro : list) {
                    %>
                <form action="MainController" method="POST">
                    <tr>
                        <td><%=count++%></td>
                        <td>
                            <input type="text" name="proID" value="<%=pro.getProID()%>" readonly=""/>
                        </td>
                        <td>
                            <input type="text" name="proName" value="<%=pro.getProName()%>" required=""/>
                        </td>
                        <td>
                            <input type="double" name="proPrice" value="<%=pro.getPrice()%>" required=""/>
                        </td>
                        <td>
                            <input type="number" name="quantity" value="<%=pro.getQuantity()%>" min="1" required=""/>
                        </td>
                        <td>
                            <input class="button1" type="submit" name="action" value="Update"/>
                        </td>
                        <td>
                            <input class="button1" type="submit" name="action" value="Delete"/>
                    </tr>
                    
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
                <%        String updateMsg = (String) request.getAttribute("UPDATE_MESSAGE");
                        if (updateMsg == null) {
                            updateMsg = "";
                        }
                    %>
                    <p class="msg"><%=updateMsg%></p>
                    <%  String deleteMsg = (String) request.getAttribute("DELETE_MESSAGE");
                        if (deleteMsg == null) {
                            deleteMsg = "";
                        }
                        //        request.setAttribute("UPDATE_MESSAGE","");
                        //        request.setAttribute("DELETE_MESSAGE","");

                    %>
                    <p class="msg"><%=deleteMsg%></p>
        </div>

    </body>
</html>
