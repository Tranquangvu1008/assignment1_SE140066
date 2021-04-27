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
import java.util.ArrayList;
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
public class MenuController extends HttpServlet {

    private static final String SUCCESS = "menu.jsp";
    private static final String ERROR = "menu.jsp";
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
        itemDAO dao = new itemDAO();

        try {
            List<String> listCategory = dao.getListCategory();
            request.setAttribute("LIST_CATEGORY", listCategory);

            String userID = request.getParameter("txtUserID");
            String search = request.getParameter("txtSearch");
            String minPrice = request.getParameter("txtMinPrice");
            String maxPrice = request.getParameter("txtMaxPrice");
            String category = request.getParameter("txtCategory");
            String index = request.getParameter("index");
            int indexPage = 0;
            int numberPage = 0;
            List<ItemDTO> list = new ArrayList<>();

            float min = 0;
            float max = 0;
            if (minPrice == null || minPrice == "") {
                min = dao.getMinPrice();
            } else {
                min = Float.parseFloat(minPrice);
            }

            if (maxPrice == null || maxPrice == "") {
                max = dao.getMaxPrice();
            } else if (maxPrice.equals("+")) {
                max = dao.getMaxPrice();
            } else {
                max = Float.parseFloat(maxPrice);
            }

            if (search == null) {
                search = "";
            }

            if (category == null) {
                category = "";
                if (index != null) {
                    indexPage = Integer.parseInt(index);
                    numberPage = dao.getNumberPage();
                    list = dao.getPaging(indexPage, userID, search, min, max, category);
                } else if (indexPage == 0) {
                    indexPage = 1;
                    numberPage = dao.getNumberPage();
                    list = dao.getPaging(indexPage, userID, search, min, max, category);
                }
            } else {
                list = dao.getListItem(userID, search, min, max, category);
            }

            if (list != null) {
                request.setAttribute("LIST", list);
                session.setAttribute("SEARCH", search);
                session.setAttribute("CATEGORY", category);
                request.setAttribute("NUMBER_PAGE", numberPage);
                request.setAttribute("CURRENT_PAGE", indexPage);
                session.setAttribute("MESSAGE", "");
                url = SUCCESS;

            }

        } catch (Exception e) {
            log("Error at SearchServlet : " + e.toString());
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
