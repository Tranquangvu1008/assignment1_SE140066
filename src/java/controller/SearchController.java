/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import daos.itemDAO;
import dtos.HistoryDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "history.jsp";
    private static final String ERROR = "login.jsp";
    private static final String RETURN = "MenuController";

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

        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        itemDAO dao = new itemDAO();

        try {
            String search = request.getParameter("txtSearchOrder");
            request.setAttribute("MESSAGE", "");
            String userID = (String) session.getAttribute("USERID");
            String role = ud.checkRole(userID);

            if (role.equals("Us")) {
                if (userID.equals("")) {
                    userID = null;
                }
                if (search == null) {
                    search = "";
                }

                if (userID != null) {
                    List<HistoryDTO> list = dao.getListOrder(userID, search);
                    if (list != null) {
                        session.setAttribute("LISTITEM", list);
                        url = SUCCESS;
                    } else {
                        url = RETURN;
                    }
                }
            } else{
                url = RETURN;
            }

        } catch (Exception e) {
            log("Error at DeleteTeaController: " + e.toString());
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
