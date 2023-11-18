/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author laptop
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String SUCCESS="login.html";
    private static String ERROR="create.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError=new UserError();
        boolean checkValidation = true;
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password=request.getParameter("password");
            String confirm=request.getParameter("confirm");
            
            if(userID.length()<2||userID.length()>10){
                userError.setUserIDError("User ID must be in [2,10]");
                checkValidation=false;
            }
            boolean checkDuplicate=dao.checkDuplicate(userID);
            if(checkDuplicate){
                userError.setUserIDError("User ID existed!");
                checkValidation=false;
            }
            if(fullName.length()<5||fullName.length()>20){
                userError.setFullNameError("fullName must be in [5,20]");
                checkValidation=false;
            }
            if(!password.equals(confirm)){
                userError.setConfirm("Those password does not match");
                checkValidation=false;
            }
            if(checkValidation){
                if(email==null){
                    email="";
                }
                UserDTO user = new UserDTO(userID, fullName, "CUS", password,email);
                boolean checkInsert=dao.insert(user);
                if(checkInsert){
                    url=SUCCESS;
                } else{
                    userError.setError("Unknow error");
                    request.setAttribute("USER_ERROR", userError);
                }
            } else{
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e){
            e.printStackTrace();
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
