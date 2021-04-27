/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CartDTO;
import dtos.HistoryAdmin;
import dtos.HistoryDTO;
import dtos.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author SE140066
 */
public class itemDAO {

    public List<ItemDTO> getListItem(String userID, String search, float minMoney, float maxMoney, String category) {
        List<ItemDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT itemID, itemName, status, description, picture, price, createDate, category, quantity "
                    + "FROM tblItems "
                    + "WHERE itemName LIKE ? AND ? <= price AND price <= ? AND category LIKE ? AND status = '1' AND quantity > 0"
                    + "ORDER BY createDate DESC;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            stm.setFloat(2, minMoney);
            stm.setFloat(3, maxMoney);
            stm.setString(4, "%" + category + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString(1);
                String itemName = rs.getString(2);
                boolean status = rs.getBoolean(3);
                String description = rs.getString(4);
                String picture = rs.getString(5);
                float price = rs.getFloat(6);
                Date createDate = rs.getDate(7);
                category = rs.getString(8);
                int quantity = rs.getInt(9);

                if (list == null) {
                    list = new ArrayList<ItemDTO>();
                }
                list.add(new ItemDTO(userID, itemID, itemName, status, description, picture, price, createDate, category, quantity));
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<String> getListCategory() {
        List<String> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT DISTINCT category FROM tblItems";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);

                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(category);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public List<String> getListItemID() {
        List<String> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT itemID FROM tblItems WHERE status = '1';";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString(1);

                if (list == null) {
                    list = new ArrayList<String>();
                }
                list.add(itemID);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public float getMinPrice() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float price = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT MIN(price)FROM tblItems WHERE status = '1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                price = rs.getFloat(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return price;
    }

    public float getMaxPrice() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float price = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT MAX(price)FROM tblItems WHERE status = '1'";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                price = rs.getFloat(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return price;
    }

    public String getInformation(String infor, String itemName) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String data = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT " + infor + " FROM tblItems WHERE itemName = ? AND status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            rs = stm.executeQuery();
            if (rs.next()) {
                data = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public Boolean getStatus(String itemName) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Boolean data = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT status FROM tblItems WHERE itemName = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            rs = stm.executeQuery();
            if (rs.next()) {
                data = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public float getPrice(String itemName) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float data = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT price FROM tblItems WHERE itemName = ? AND status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            rs = stm.executeQuery();
            if (rs.next()) {
                data = rs.getFloat(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public Date getDate(String itemName) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Date data = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT createDate FROM tblItems WHERE itemName = ? AND status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            rs = stm.executeQuery();
            if (rs.next()) {
                data = rs.getDate(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    /*-------------Confirm Order--------------*/
    public static int checkQuantityOfItem(String itemID) {
        //Kiem tra so luong phong con trong
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT quantity FROM tblItems WHERE itemID = ? AND status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemID);
            rs = stm.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return quantity;
    }

    public boolean createOrder(CartDTO cart) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        float totalPrice = 0;
        Date orderDate = Date.valueOf(LocalDate.now());

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "INSERT INTO tblOrders (userID, totalPrice, date) VALUES(?,?,?)";
                String orderID[] = {"orderID"};
                for (ItemDTO dto : cart.getCart().values()) {
                    totalPrice += dto.getQuantity() * dto.getPrice();
                }
                stm = conn.prepareStatement(sql, orderID);
                stm.setString(1, cart.getCustomName());
                stm.setFloat(2, totalPrice);
                stm.setDate(3, orderDate);

                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    String orderDetailSql = "INSERT INTO tblOrderDetail (orderID, itemID, quantity, price, date) VALUES (?,?,?,?,?)";
                    for (ItemDTO dto : cart.getCart().values()) {
                        stm = conn.prepareStatement(orderDetailSql);
                        stm.setInt(1, rs.getInt(1));
                        stm.setString(2, dto.getItemID());
                        stm.setInt(3, dto.getQuantity());
                        stm.setFloat(4, dto.getQuantity() * dto.getPrice());
                        stm.setDate(5, orderDate);
                        stm.executeUpdate();
                    }
                    result = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public static void editQuantityOfItem(int number, String itemID, int quantity) {
        Connection conn = null;
        PreparedStatement stm = null;

        int amount = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblItems SET quantity = ? WHERE itemID = ?;";
            stm = conn.prepareStatement(sql);
            amount = quantity - number;

            stm.setInt(1, amount);
            stm.setString(2, itemID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*-----------------History--------------*/
    public String getItemName(String itemID) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String itemName = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT itemName FROM tblItems WHERE itemID = ? AND status = '1'";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemID);
            rs = stm.executeQuery();
            if (rs.next()) {
                itemName = rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return itemName;
    }

    public List<HistoryDTO> getListOrder(String userID, String search) {
        List<HistoryDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT detailID, tblOrderDetail.orderID, itemID, tblOrderDetail.quantity, tblOrderDetail.price, tblOrderDetail.date\n"
                    + "FROM tblOrderDetail\n"
                    + "INNER JOIN tblOrders ON tblOrderDetail.orderID = tblOrders.orderID\n"
                    + "WHERE tblOrders.userID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int detailID = rs.getInt(1);
                int orderID = rs.getInt(2);
                String itemID = rs.getString(3);
                String itemName = getItemName(itemID);
                int quantity = rs.getInt(4);
                float price = rs.getFloat(5);
                Date date = rs.getDate(6);

                if (list == null) {
                    list = new ArrayList<HistoryDTO>();
                }

                if (search.equals("")) {
                    list.add(new HistoryDTO(detailID, orderID, itemID, itemName, quantity, price, date));
                }
                if (itemName.equalsIgnoreCase(search)) {
                    list.add(new HistoryDTO(detailID, orderID, itemID, itemName, quantity, price, date));
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    /*--------------Admin Function--------------*/
    public List<ItemDTO> getListItem(String userID) {
        List<ItemDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT itemID, itemName, status, description, picture, price, createDate, category, quantity "
                    + "FROM tblItems WHERE status = '1' ORDER BY createDate DESC;";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString(1);
                String itemName = rs.getString(2);
                boolean status = rs.getBoolean(3);
                String description = rs.getString(4);
                String picture = rs.getString(5);
                float price = rs.getFloat(6);
                Date createDate = rs.getDate(7);
                String category = rs.getString(8);
                int quantity = rs.getInt(9);

                if (list == null) {
                    list = new ArrayList<ItemDTO>();
                }
                list.add(new ItemDTO(userID, itemID, itemName, status, description, picture, price, createDate, category, quantity));

            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public List<ItemDTO> getListItemByItem(String userID, String itemName) {
        List<ItemDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT itemID, itemName, status, description, picture, price, createDate, category, quantity "
                    + "FROM tblItems WHERE status = '1' AND itemName = ? ORDER BY createDate DESC;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            rs = stm.executeQuery();
            while (rs.next()) {
                String itemID = rs.getString(1);
                itemName = rs.getString(2);
                boolean status = rs.getBoolean(3);
                String description = rs.getString(4);
                String picture = rs.getString(5);
                float price = rs.getFloat(6);
                Date createDate = rs.getDate(7);
                String category = rs.getString(8);
                int quantity = rs.getInt(9);

                if (list == null) {
                    list = new ArrayList<ItemDTO>();
                }
                list.add(new ItemDTO(userID, itemID, itemName, status, description, picture, price, createDate, category, quantity));

            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public static void updateItem(ItemDTO dto) throws SQLException, ParseException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblItems SET itemName = ?, description = ?, price = ?, createDate = ?, category = ?, quantity = ?"
                    + " WHERE itemID = ?;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getItemName());
            stm.setString(2, dto.getDescription());
            stm.setFloat(3, dto.getPrice());
            stm.setDate(4, (Date) dto.getCreateDate());
            stm.setString(5, dto.getCategory());
            stm.setInt(6, dto.getQuantity());
            stm.setString(7, dto.getItemID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void deleteItem(String itemName) throws SQLException, ParseException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblItems SET status = '0'"
                    + " WHERE itemName = ?;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, itemName);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*-------------Count Page--------------*/
    public int getNumberPage() {
        int total = 0;
        int countPage = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT COUNT(itemID) FROM tblItems "
                        + "WHERE status = 1 AND quantity > 0";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    total = rs.getInt(1); //20
                    int size = 8;
                    countPage = total / size; // 20 : 5

                    if (total % size != 0) {
                        countPage++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return countPage;
    }

    public ArrayList<ItemDTO> getPaging(int index, String userID, String search, float minMoney, float maxMoney, String category) {
        ArrayList<ItemDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getCon();
            if (con != null) {
                String sql = "SELECT itemID, itemName, description, picture, price, createDate, category, quantity "
                        + "from tblItems "
                        + "WHERE itemName LIKE ? AND ? <= price AND price <= ? AND category LIKE ? AND status = '1' AND quantity > 0"
                        + "order by createDate "
                        + "offset ? rows "
                        + "fetch first 8 rows only";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setFloat(2, minMoney);
                stm.setFloat(3, maxMoney);
                stm.setString(4, "%" + category + "%");
                stm.setInt(5, (index - 1) * 8);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String itemID = rs.getString(1);
                    String itemName = rs.getString(2);
                    String description = rs.getString(3);
                    String picture = rs.getString(4);
                    float price = rs.getFloat(5);
                    Date dayCreate = rs.getDate(6);
                    category = rs.getString(7);
                    int quantity = rs.getInt(8);

                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(new ItemDTO(userID, itemID, itemName, true, description, picture, price, dayCreate, category, quantity));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(itemDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    /*------------Create Item---------------*/
    public void createItem(ItemDTO dto) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblItems (itemID, itemName, status, description, picture, price, createDate, category, quantity)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getItemID());
            stm.setString(2, dto.getItemName());
            stm.setString(3, "1");
            stm.setString(4, dto.getDescription());
            stm.setString(5, dto.getPicture());
            stm.setFloat(6, dto.getPrice());
            stm.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            stm.setString(8, dto.getCategory());
            stm.setInt(9, dto.getQuantity());
            stm.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean checkItemID(String itemID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "Select itemID FROM tblItems WHERE itemID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, itemID);
                rs = stm.executeQuery();
                result = rs.next();
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        return result;
    }
    
    public void getHistoryAdmin(String content, String userID) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblUpdate (updateDate, content, userID)"
                    + "OUTPUT inserted.updateID"
                    + " VALUES(?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            stm.setString(2, content);
            stm.setString(3, userID);
            stm.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public List<HistoryAdmin> getListHistory(String userID) {
        List<HistoryAdmin> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT updateDate, content, userID "
                    + "FROM tblUpdate WHERE userID = ? "
                    + "ORDER BY updateDate DESC;";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate(1);
                String content = rs.getString(2);
                userID = rs.getString(3);

                if (list == null) {
                    list = new ArrayList<HistoryAdmin>();
                }
                list.add(new HistoryAdmin(date, content, userID));

            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    /*------------Create Shipping Details---------------*/
    public void setShippingDetails(String userID, String userName, String email, String phone, String address, int orderID) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblShippingDetails (ShippingUser, ShippingEmail, ShippingPhone, ShippingAddress, userID, orderID)"
                    + " VALUES(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, email);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, userID);
            stm.setInt(6, orderID);
            stm.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public int getOrderID(String userID) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int orderID = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "SELECT TOP(1) orderID FROM tblOrders Where userID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(itemDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderID;
    }
    
}
