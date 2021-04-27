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
import java.util.List;
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
public class UpdateController extends HttpServlet {
    private static final String SUCCESS = "ManageController";
    private static final String ERROR = "manage.jsp";
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
                float price = Float.parseFloat(request.getParameter("txtPrice"));
                String createDate = request.getParameter("txtCreateDate");
                String category = request.getParameter("txtCategory");
                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                itemDAO dao = new itemDAO();
                ItemDTO dtos = new ItemDTO();
                validation.Validation val = new Validation();
                List<ItemDTO> list = dao.getListItem(userID);

                String noti = val.checkValidation(itemName, description, price, createDate, category, quantity);
                if (noti == null) {
                    noti = "";
                }

                for (ItemDTO dto : list) {
                    if (noti.equals("")) {
                        if (dto.getItemID().equalsIgnoreCase(itemID)) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            format.setLenient(false);
                            Date parsed = format.parse(createDate);
                            java.sql.Date dateS = new java.sql.Date(parsed.getTime());

                            dao.updateItem(new ItemDTO(userID, itemID, itemName, dto.isStatus(), description, dto.getPicture(), price, dateS, category, quantity));
                            dao.getHistoryAdmin("Update item", userID);
                            break;
                        }
                    } else {
                        request.setAttribute("NOTI", noti);
                        break;
                    }
                }
                url = SUCCESS;
            } else{
                url = RETURN;
            }

            

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
