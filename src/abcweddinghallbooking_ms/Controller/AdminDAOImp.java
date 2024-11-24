/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;
import abcweddinghallbooking_ms.DBConnection;
import abcweddinghallbooking_ms.Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author sanduni punchihewa
 */
public class AdminDAOImp implements AdminDAO {

   public void save(Admin admin) {
         java.util.Date dateOfBirth = admin.getDateOfBirth();

        
        try{
            
            
            Connection con =  DBConnection.getConnection();
            String sql = "INSERT into admin(name,date_of_birth,age,gender,address,email,telephone_no,username,Upassword) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getName());
            ps.setDate(2, new java.sql.Date(dateOfBirth.getTime()));
            ps.setInt(3, admin.getAge());
            ps.setString(4, admin.getGender());
            ps.setString(5,admin.getAddress());
            ps.setString(6,admin.getEmail());
            ps.setString(7,admin.getTelephoneNo());
            ps.setString(8,admin.getUserName());
            ps.setString(9,admin.getPassword());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registered!");
                    
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    public void update(Admin admin) {
        
        
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE admin set name=?,username=?,Upassword=? where admin_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getUserName());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getAdminID());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated!");
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Admin admin) {
     try{
            
           Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM admin WHERE admin_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, admin.getName()); 

    int affectedRows = ps.executeUpdate();
    if (affectedRows > 0) {
        JOptionPane.showMessageDialog(null, "Delete successful!");
    } else {
        JOptionPane.showMessageDialog(null, "No rows deleted!");
    }
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public Admin get(int id) {
        
    Admin adm = new Admin();
    
    try{
        Connection con = DBConnection.getConnection();
        String sql = "select * from admin where admin_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
          adm.setAdminID(rs.getInt("admin_id"));
                adm.setName(rs.getString("name"));
                 adm.setUserName(rs.getString("username"));
                adm.setPassword(rs.getString("Upassword"));
                
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    
    return adm;
    
    }

    public List<Admin> list() {
         List<Admin> list = new ArrayList<Admin>();
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "select * from admin";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Admin adm = new Admin();
                adm.setAdminID(rs.getInt("admin_id"));
                adm.setName(rs.getString("name"));
                adm.setDateOfBirth(rs.getDate("date_of_birth"));
                adm.setAge(rs.getInt("age"));
                adm.setGender(rs.getString("gender"));
                adm.setAddress(rs.getString("address"));
                adm.setEmail(rs.getString("email"));
                adm.setTelephoneNo(rs.getString("telephone_no"));
                adm.setUserName(rs.getString("username"));
                adm.setPassword(rs.getString("Upassword"));
                
                list.add(adm);
            }
        }catch(Exception e){
    
            e.printStackTrace();
            
        }
    
        return list;
        
    }
    
     public boolean login(String username, String password) {
        try {
           Connection con = DBConnection.getConnection();

            // Create SQL query
            String query = "SELECT * FROM admin WHERE username = ? AND Upassword = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching record exists
            boolean loggedIn = resultSet.next();

            // Close resources
            resultSet.close();
            statement.close();
            //con.close();

            return loggedIn;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
