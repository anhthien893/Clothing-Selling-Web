/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.shopping.Product;
import sample.utils.DBUtils;

/**
 *
 * @author laptop
 */
public class UserDAO {

    private static String CHECK_ROLE = "SELECT name,roleID,email FROM [User] WHERE (userID=? or email=?) and password=?";
    private static String DUPLICATE = "SELECT userID FROM [User] WHERE userID=?";
    private static String INSERT = "INSERT INTO [User](userID,name,email,roleID,password) VALUES(?,?,?,?,?)";

    public UserDTO checkRole(String ID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_ROLE);
                ptm.setString(1, ID);
                ptm.setString(2, ID);
                ptm.setString(3, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleID=rs.getString("roleID");
                    String email=rs.getString("email");
                    user = new UserDTO(ID, name, roleID, password, email);
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
        return user;
    }

  

    public boolean insert(UserDTO user) throws ClassNotFoundException, SQLException {
        boolean check=false;
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(INSERT);
                ptm.setString(1, user.getID());
                ptm.setString(2, user.getName());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getRoleID());
                ptm.setString(5, user.getPassword());
                check=ptm.executeUpdate()>0?true:false;
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
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, userID);
                rs=ptm.executeQuery();
                if(rs.next()){
                    check=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm!=null){
                ptm.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
        return check;
    }

}
