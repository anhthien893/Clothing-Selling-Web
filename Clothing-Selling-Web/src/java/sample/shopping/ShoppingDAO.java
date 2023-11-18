/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author laptop
 */
public class ShoppingDAO {

    private static String LIST_PRODUCT = "SELECT proID,proName,quantity,price FROM Product";
    private static String DTB_BUY = "UPDATE Product SET quantity=? WHERE proID=?";
    private static String UPDATE = "UPDATE Product SET proName=?,price=?, quantity=? WHERE proID=?";
    private static String DELETE = "DELETE Product WHERE proID=?";
    private static String INSERT_ORD="INSERT INTO [Order] (userID,[date],total) VALUES(?,getdate(),?)";
    private static String INSERT_DETAIL="INSERT INTO [OrdDetail] (ordID,productID,price, quantity) VALUES((SELECT MAX(ordID) FROM [Order]),?,?,?)";
    public List<Product> getlistProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String proID = rs.getString("proID").trim();
                    String proName = rs.getString("proName").trim();
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    Product pro = new Product(proID, proName, quantity, price,true);
                    list.add(pro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public void buy(Product bpro, int quan) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        List<Product> list = getlistProduct();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DTB_BUY);
                ptm.setInt(1, quan);
                ptm.setString(2, bpro.getProID());
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean update(String proID, int quantity, String name, double price) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, name);
                ptm.setDouble(2, price);
                ptm.setInt(3, quantity);
                ptm.setString(4, proID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean delete(String proID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, proID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public void ord(int total, String userID) throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(INSERT_ORD);
                ptm.setString(1, userID);
                ptm.setInt(2, total);
                ptm.executeUpdate();
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            if(ptm!=null){
                ptm.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }

    public void ordDetail(Cart cart) throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn=DBUtils.getConnection();
            if(conn!=null){
                for (Product pro : cart.getCart().values()) {
                    ptm=conn.prepareStatement(INSERT_DETAIL);
                    ptm.setString(1, pro.getProID());
                    ptm.setDouble(2, pro.getPrice());
                    ptm.setInt(3, pro.getQuantity());
                    ptm.executeUpdate();
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            if(ptm!=null){
                ptm.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }

}
