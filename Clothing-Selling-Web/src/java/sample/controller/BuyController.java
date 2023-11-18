/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.Product;
import sample.shopping.ShoppingDAO;
import sample.user.UserDTO;

/**
 *
 * @author laptop
 */
@WebServlet(name = "BuyController_1", urlPatterns = {"/BuyController_1"})
public class BuyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "viewCart.jsp";
        try {
            ShoppingDAO d = new ShoppingDAO();
            List<Product> list = d.getlistProduct(); //số lượng trong kho chứa vào list
            boolean check = false;
            HttpSession session = request.getSession();
            Cart cart = new Cart();
            if (session != null) {
                cart = (Cart) session.getAttribute("CART"); //số lượng trong giỏ hàng
                if (cart == null) {
                    cart = new Cart();
                }
            }
            request.setAttribute("BUY_STA", "Successfully!");
            for (Product bpro : cart.getCart().values()) { //đi từng product trong giỏ hàng
                bpro.setStatus(false);
                for (Product spro : list) { //đi từng sp trong list
                    if (spro.getProID().equals(bpro.getProID()) && spro.getQuantity() - bpro.getQuantity() >= 0) {
                        bpro.setStatus(true);
                        break;
                    }
                }
            }
            for (Product bpro : cart.getCart().values()) {
                if (!bpro.isStatus()) {
                    request.removeAttribute("BUY_STA");
                    break;
                }
            }
            String sta = (String) request.getAttribute("BUY_STA");
            int total = 0;
            if (sta != null) {
                for (Product bpro : cart.getCart().values()) {
                    for (Product spro : list) {
                        if (bpro.getProID().equals(spro.getProID())) {
                            d.buy(bpro, spro.getQuantity() - bpro.getQuantity());
                            break;
                        }
                    } total += bpro.getQuantity() * bpro.getPrice();
                  
                }
            }
            if (sta != null) {
                UserDTO user=(UserDTO)session.getAttribute("LOGIN_USER");
                String userID = user.getID();
                d.ord(total, userID);
                d.ordDetail(cart);
            }
        } catch (Exception e) {

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(BuyController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(BuyController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
