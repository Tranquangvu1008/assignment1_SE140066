/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import dtos.CartDTO;
import dtos.ItemDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class UpdateItemController extends HttpServlet {

    private static final String SUCCESS = "showcart.jsp";
    private static final String ERROR = "error.jsp";

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

            if (role.equals("Us")) {
                String itemID = request.getParameter("txtItemID");
                userID = request.getParameter("txtUserID");

                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                CartDTO cart = (CartDTO) session.getAttribute("CART");
                ItemDTO item = new ItemDTO();

                for (ItemDTO dto : cart.getCart().values()) {
                    if (dto.getItemID().equalsIgnoreCase(itemID)) {
                        item = new ItemDTO(userID, itemID, dto.getItemName(), dto.isStatus(), dto.getDescription(),
                                dto.getPicture(), dto.getPrice(), dto.getCreateDate(), dto.getCategory(), quantity);
                        break;
                    }
                }

                cart.update(item);
                session.setAttribute("CART", cart);
                session.setAttribute("CONFIRM", "");
                url = SUCCESS;
            } else{
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at UpdateTeaController: " + e.toString());
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
