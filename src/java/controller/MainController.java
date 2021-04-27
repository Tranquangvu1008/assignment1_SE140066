/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SE140066
 */
public class MainController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String LOGIN = "LoginController";
    private final static String LOGIN_PAGE = "login.jsp";
    private final static String SEARCH = "SearchController";
    private final static String LOGOUT = "LogoutController";
    private final static String MENU = "MenuController";
    private final static String ORDER = "OrderController";
    private final static String DELETEITEM = "DeleteItemController";
    private final static String UPDATEITEM = "UpdateItemController";
    private final static String SHOWITEM = "ShowCartController";
    private final static String CONFIRM = "ConfirmController";
    private final static String HISTORY = "HistoryController";
    private final static String HISTORYAD = "HistoryAdController";
    private final static String UPDATE = "UpdateController";
    private final static String DELETE = "DeleteController";
    private final static String CREATE = "CreateController";
    private final static String ADD = "AddController";
    private final static String MANAGE = "ManageController";
    private final static String FIND = "FindController";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("btnAction");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("LoginPage".equals(action)) {
                url = LOGIN_PAGE;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Menu".equals(action)) {
                url = MENU;
            } else if ("Order".equals(action)) {
                url = ORDER;
            } else if ("Delete Item".equals(action)) {
                url = DELETEITEM;
            } else if ("Update Item".equals(action)) {
                url = UPDATEITEM;
            } else if ("Show".equals(action)) {
                url = SHOWITEM;
            } else if ("Confirm".equals(action)) {
                url = CONFIRM;
            } else if ("History".equals(action)) {
                url = HISTORY;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("Delete".equals(action)) {
                url = DELETE;
            } else if ("Create".equals(action)) {
                url = CREATE;
            } else if ("Add".equals(action)) {
                url = ADD;
            } else if ("Manage".equals(action)) {
                url = MANAGE;
            } else if ("Find".equals(action)) {
                url = FIND;
            }else if ("HistoryAd".equals(action)) {
                url = HISTORYAD;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
