/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;
import abcweddinghallbooking_ms.DBConnection;
import abcweddinghallbooking_ms.Model.Hall;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author sanduni punchihewa
 */
public class HallDAOImp {
    
public void save(Hall hall) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO hall(hall_id, hall_name, hall_type, price_per_day, availability, photo) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, hall.getHallID());
        ps.setString(2, hall.getHallName());
        ps.setString(3, hall.getHallType());
        ps.setInt(4, hall.getPricePerDay());
        ps.setString(5, hall.getAvailablity());
        ps.setBlob(6, hall.getPhoto());

        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Saved!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void update(Hall hall) {
       
        
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE hall set hall_name=?,hall_type=?,price_per_day=?,availablity=?,photo=? where customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,hall.getHallName());
            
            
            ps.setString(2, hall.getHallType());
             ps.setInt(3,hall.getPricePerDay());
            ps.setString(4,hall.getAvailablity());
            
            ps.setBlob(5,hall.getPhoto());
           
            ps.setInt(6, hall.getHallID());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated!");
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   
    public void delete(Hall hall) {
            try{
            
           Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM hall WHERE hall_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, hall.getHallID()); 

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


    public Hall get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<Hall> list() {
        List<Hall> list = new ArrayList<Hall>();
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "select * from hall";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Hall hall = new Hall();
                hall.setHallID(rs.getInt("hall_id"));
                hall.setHallName(rs.getString("hall_name"));
                
                hall. setHallType(rs.getString("hall_type"));
                hall.setPricePerDay(rs.getInt("price_per_day"));
                hall.setAvailablity(rs.getString("availablity"));
                hall.setPhoto(rs.getBlob("photo"));
                
                
                list.add(hall);
            }
        }catch(Exception e){
    
            e.printStackTrace();
            
        }
    
        return list;
    }
    
    public List<Hall> list1(String availability) {
        List<Hall> list = new ArrayList<Hall>();
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "select * from hall where availablity = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, availability);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Hall hall = new Hall();
                hall.setHallID(rs.getInt("hall_id"));
                hall.setHallName(rs.getString("hall_name"));
                
                hall. setHallType(rs.getString("hall_type"));
                hall.setPricePerDay(rs.getInt("price_per_day"));
                hall.setAvailablity(rs.getString("availablity"));
                hall.setPhoto(rs.getBlob("photo"));
                
                
                list.add(hall);
            }
        }catch(Exception e){
    
            e.printStackTrace();
            
        }
    
        return list;
    }

    public void updateAvailability(int hallID, String available) {
        
        PreparedStatement statement = null;

        try {
            // Obtain a database connection
           Connection con = DBConnection.getConnection(); 

            // Prepare the SQL statement
            String sql = "UPDATE hall SET availablity = ? WHERE hall_id = ?";
            statement = con.prepareStatement(sql);

            // Set the parameters
            statement.setString(1, available);
            statement.setInt(2, hallID);

            // Execute the update statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        
        }

    }
    
    
}
