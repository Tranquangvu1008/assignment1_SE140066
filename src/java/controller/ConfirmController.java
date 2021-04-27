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
public class ConfirmController extends HttpServlet {

    private final static String SUCCESS = "confirmsuccess.jsp";
    private final static String ERROR = "showcart.jsp";
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
            boolean check = true;
            String userID = (String) session.getAttribute("USERID");
            String role = ud.checkRole(userID);
            String userName = request.getParameter("UserName");
            String email = request.getParameter("Email");
            String phone = request.getParameter("Phone");
            String address = request.getParameter("Address");

            if (!email.trim().equals("")) {
                if (!email.matches("(\\w*\\d*)+@(\\w+\\.\\w+){1}(\\.\\w+)?")) {
                    request.setAttribute("CONFIRM", "Wrong format Email!");
                    check = false;
                }
            }
            if (!phone.trim().equals("")) {
                if (phone.length() > 16) {
                    request.setAttribute("CONFIRM", "Wrong format Phone (Max length < 16)!");
                    check = false;
                }
            }

            if (role.equals("Us")) {
                if (check) {
                    CartDTO cart = (CartDTO) session.getAttribute("CART"); //lay gio hang 
                    itemDAO dao = new itemDAO();

                    if (checkQuantity(request, response)) {
                        dao.createOrder(cart);
                        int orderID = dao.getOrderID(userID);
                        dao.setShippingDetails(userID, userName, email, phone, address, orderID);
                        for (ItemDTO dto : cart.getCart().values()) {
                            int quantity = dao.checkQuantityOfItem(dto.getItemID());
                            dao.editQuantityOfItem(dto.getQuantity(), dto.getItemID(), quantity);

                            url = SUCCESS;
                        }
                        List<String> list = dao.getListItemID();
                        for (int i = 0; i < list.size(); i++) {
                            cart.delete(list.get(i));
                        }

                        request.setAttribute("CONFIRM", null);

                    } else {
                        for (ItemDTO dto : cart.getCart().values()) {
                            int quantity = dao.checkQuantityOfItem(dto.getItemID());

                            if (dto.getQuantity() > quantity) {
                                request.setAttribute("CONFIRM", dto.getItemName() + " has only " + quantity + " products left. Not enough for your request!");
                            }
                        }
                        url = ERROR;
                    }
                }
            } else {
                url = RETURN;
            }

        } catch (Exception e) {
            log("Error at AcceptController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static boolean checkQuantity(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");

            itemDAO dao = new itemDAO();

            for (ItemDTO dto : cart.getCart().values()) {
                if (dto.getQuantity() > dao.checkQuantityOfItem(dto.getItemID())) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
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
