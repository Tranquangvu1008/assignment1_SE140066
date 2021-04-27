/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDAO;
import daos.itemDAO;
import dtos.CartDTO;
import dtos.ItemDTO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE140066
 */
public class OrderController extends HttpServlet {
    private final static String SUCCESS = "MenuController";
    private final static String ERROR = "login.jsp";

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

            String userID = (String) session.getAttribute("USERID");
            String role = ud.checkRole(userID);

            if (role.equals("Us")) {
                userID = request.getParameter("txtUserID");
                String itemName = request.getParameter("txtItemName");
                String itemID = dao.getInformation("itemID", itemName);
                String description = dao.getInformation("description", itemName);
                String picture = dao.getInformation("picture", itemName);
                String category = dao.getInformation("category", itemName);

                boolean status = dao.getStatus(itemName);
                float price = dao.getPrice(itemName);
                Date createDate = dao.getDate(itemName);

                if (userID.isEmpty()) {
                    url = ERROR;
                } else {
                    ItemDTO item = new ItemDTO(userID, itemID, itemName, status, description, picture, price, createDate, category, 1);

                    CartDTO cart = (CartDTO) session.getAttribute("CART");
                    String customerName = (String) session.getAttribute("FULLNAME");

                    if (cart == null) {
                        cart = new CartDTO(customerName, null);
                    }
                    cart.add(item);

                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                }
            } else{
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at LoginServlet : " + e.toString());
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
