/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author laptop
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "Login";
    private static final String LOGIN_PAGE = "login.html";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";

    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewCart.jsp";

    private static final String BACK = "Back";
    private static final String BACK_PAGE = "customer.jsp";

    private static final String BUY = "Buy";
    private static final String BUY_CONTROLLER = "BuyController";

    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";

    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";

    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";

    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String CREATE_PAGE = "CreatePage";
    private static final String CREATE_PAGE_VIEW = "create.html";

    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else if (action.equals(LOGIN)) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals(ADD)) {
                url = ADD_CONTROLLER;
            } else if (action.equals(VIEW)) {
                url = VIEW_PAGE;
            } else if (action.equals(BACK)) {
                url = BACK_PAGE;
            } else if (action.equals(BUY)) {
                url = BUY_CONTROLLER;
            } else if (action.equals(UPDATE)) {
                url = UPDATE_CONTROLLER;
            } else if (action.equals(DELETE)) {
                url = DELETE_CONTROLLER;
            } else if (action.equals(EDIT)) {
                url = EDIT_CONTROLLER;
            } else if (action.equals(REMOVE)) {
                url = REMOVE_CONTROLLER;
            } else if (action.equals(LOGOUT)) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals(CREATE_PAGE)) {
                url = CREATE_PAGE_VIEW;
            } else if (action.equals(CREATE)) {
                url = CREATE_CONTROLLER;
            }

        } catch (Exception e) {
            log(e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
