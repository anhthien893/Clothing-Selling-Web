<%-- 
    Document   : viewCart
    Created on : Jun 20, 2023, 9:59:00 PM
    Author     : laptop
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.ShoppingDAO"%>
<%@page import="sample.shopping.Product"%>
<%@page import="sample.shopping.Cart"%>
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
        <link rel="stylesheet" type="text/css" media="all" href="css/styleCart.css">
        <title>View Cart</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"CUS".equals(loginUser.getRoleID())) {
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
            </div>
        </div>
        <h1>Your Cart</h1>
        <div class="container">
            <%
                String sta = (String) request.getAttribute("BUY_STA");
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null && sta == null) {
                    if (cart.getCart().size() > 0) {
            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Edit</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        int count = 1;
                        double total = 0;
                        for (Product pro : cart.getCart().values()) {
                            total += pro.getQuantity() * pro.getPrice();
                    %>
                <form action="MainController" method="POST">
                    <tr>
                        <td><%=count%></td>
                        <td>
                            <input type="text" name="proID" value="<%=pro.getProID().trim()%>" readonly=""/>
                        </td>
                        <td>
                            <%=pro.getProName()%>
                        </td>
                        <td>
                            <%=pro.getPrice()%>
                        </td>
                        <td>
                            <input type="number" name="proQuan" value="<%=pro.getQuantity()%>" required="" min="1"/>
                        </td>
                        <td><%=pro.getPrice() * pro.getQuantity()%></td>
                        <td>
                            <input class="button1" type="submit" name="action" value="Edit"/>
                            <%--Delete nè--%>
                        </td>
                        <td>
                            <input class="button1" type="submit" name="action" value="Remove"/>
                            <input type="hidden" name="proID" value="<%=pro.getProID()%>"/>

                        </td>
                        <%
                            ShoppingDAO d = new ShoppingDAO();
                            List<Product> list = d.getlistProduct();
                            int tmpQuan = 0;
                            if (!pro.isStatus()) {
                                for (Product spro : list) {
                                    if (spro.getProID().equals(pro.getProID())) {
                                        tmpQuan = spro.getQuantity();
                                        break;
                                    }
                                }

                        %>
                        Please buy at most <%=tmpQuan%> <%=pro.getProName()%></br>
                        <%
                                request.removeAttribute("BUY_STA");
                            }

                        %>
                        <%                        count++;
                        %>
                    </tr>
                </form>

                </tbody>


                <%
                            }
                        }
                    }
                %>

                <%
                    if (sta == null) {
                        sta = "";

                    } else {
                        session.removeAttribute("CART");
                        request.removeAttribute("BUY_STA");

                    }
                %>
            </table>
            <form action="MainController" method="POST">
                <input class="form-control" type="submit" name="action" value="Back">
                <input class="form-control" type="submit" name="action" value="Buy">
            </form>
            <p class="msg"><%=sta%></p>
        </div>
    </body>
</html>
