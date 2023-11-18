<%-- 
    Document   : customer
    Created on : Jun 20, 2023, 4:04:56 PM
    Author     : laptop
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.shopping.ShoppingDAO"%>
<%@page import="sample.user.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Clicker+Script&family=Poppins:wght@400;700&family=Quicksand:wght@400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="css/styleCus.css">
    </head>
    <body>
    <c:if test="${sessionScope.LOGIN_USER == null||sessionScope.LOGIN_USER.roleID ne 'CUS'}">
        <c:redirect url="login.html">
        </c:redirect>
    </c:if>
    <div class="container">
        <div class="header">
            <div class="logo">
                <img src="image/logo.png">
            </div>
            <div class="quotes">Hi, ${sessionScope.LOGIN_USER.name}</div>
            <form action="MainController" method="POST">
                <input class="button" type="submit" name="action" value="Logout">
            </form>
        </div>
    </div>
    <div class="banner">
        <img src="image/COVER.png">
    </div>
    <h1>Products</h1>
    <div class="container">
        <div class="row menu">
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/d1.jpg">
                    <p>D1 - Dress - 200.000VNĐ</p>
                </div>

            </div>
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/a2.jpg">
                    <p>A1 - T-shirt - 100.000VNĐ</p>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/a1.jpg">
                    <p>A2 - Blouse - 100.000VNĐ</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/v1.jpg">
                    <p>V1 - Skirt - 150.000VNĐ</p>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/q1.jpg">
                    <p>Q1 - Shorts - 100.000VNĐ</p>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="item">
                    <img src="image/q2.jpg">
                    <p>Q2 - Pants - 200.000VNĐ</p>
                </div>
            </div>
        </div>
    </div>
    <h1>Order's information</h1>
    <div class="container">
        <form class="form-group" action="MainController" method="POST">
            <select class="form-control" name="cmbProduct">
                <%  ShoppingDAO d = new ShoppingDAO();
                        List<Product> list = d.getlistProduct();
                        for (Product pro : list) {
                            if (pro.getQuantity() > 0) {
                    %>
                    <option value=<%=pro.getProID()%>><%=pro.getProID()%>_<%=pro.getProName()%>_<%=pro.getPrice()%></option>
                    <%
                            }
                        }
                    %>
            </select>
            <select class="form-control" name="cmbQuantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="10">10</option>
            </select>
            <input class="form-control button" type="submit" name="action" value="Add">
            <input class="form-control button" type="submit" name="action" value="View">
            <p class="msg">${requestScope.MESSAGE}</p>
        </form>
    </div>
</body>
</html>