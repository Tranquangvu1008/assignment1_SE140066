/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import daos.itemDAO;
import dtos.ItemDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import validation.Validation;

/**
 *
 * @author SE140066
 */
public class AddController extends HttpServlet {

    private static final String SUCCESS = "ManageController";
    private static final String ERROR = "createitem.jsp";
    private static final String RETURN = "login.jsp";

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
        String url = ERROR;
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();

        try {
            String userID = (String) session.getAttribute("USERID");
            String role = ud.checkRole(userID);

            if (role.equals("Ad")) {
                String itemID = request.getParameter("txtItemID");
                String itemName = request.getParameter("txtItemName");
                String description = request.getParameter("txtDescription");
                String picture = request.getParameter("txtPicture");
                String category = request.getParameter("txtCategory");
                Float price = Float.parseFloat(request.getParameter("txtPrice"));
                String createDate = request.getParameter("txtCreateDate");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(createDate);
                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                itemDAO dao = new itemDAO();
                validation.Validation val = new Validation();
                boolean check = true;

                boolean checkID = dao.checkItemID(itemID);

                if (checkID) {
                    check = false;
                    session.setAttribute("ERROR", "Duplicate ID");
                }
                if (check) {
                    ItemDTO item = new ItemDTO(userID, itemID, itemName, true, description, picture, price, date, category, quantity);

                    String noti = val.checkItem(item);
                    if (noti == null) {
                        noti = "";
                    }

                    if (noti.equals("")) {
                        dao.createItem(item);
                        dao.getHistoryAdmin("Create new item", userID);
                        session.setAttribute("ERROR", null);
                        url = SUCCESS;
                    } else {
                        session.setAttribute("ERROR", noti);
                        url = ERROR;
                    }
                }
            } else{
                url = RETURN;
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
