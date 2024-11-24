/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import abcweddinghallbooking_ms.DBConnection;
import abcweddinghallbooking_ms.Model.Customer;
/**
 *
 * @author sanduni punchihewa
 */
public class CustomerDAOImp implements CustomerDAO{
    @Override
    public void save(Customer customers) {
        
        java.util.Date dateOfBirth = customers.getDate_of_birth();

        
        try{
            
            
            Connection con =  DBConnection.getConnection();
            String sql = "INSERT into customer(name,date_of_birth,age,gender,nic_no,contact_no,email,address) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customers.getName());
            ps.setDate(2, new java.sql.Date(dateOfBirth.getTime()));
            ps.setInt(3, customers.getAge());
            ps.setString(4, customers.getGender());
            ps.setString(5,customers.getNic_no());
            ps.setString(6,customers.getContact_no());
            ps.setString(7,customers.getEmail());
            ps.setString(8,customers.getAddress());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved!");
                    
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
 
    @Override
    public void update(Customer customers) {
        
        java.util.Date dateOfBirth = customers.getDate_of_birth();
        
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE customer set name=?,date_of_birth=?,age=?,gender=?,nic_no=?,contact_no=?,email=?,address=? where customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customers.getName());
            ps.setDate(2, new java.sql.Date(dateOfBirth.getTime()));
            ps.setInt(3, customers.getAge());
            ps.setString(4, customers.getGender());
            ps.setString(5,customers.getNic_no());
            ps.setString(6,customers.getContact_no());
            ps.setString(7,customers.getEmail());
            ps.setString(8,customers.getAddress());
            ps.setInt(9, customers.getCustomer_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated!");
            
            
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customers) {
        try{
            
           Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM customer WHERE customer_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, customers.getCustomer_id()); // Replace 'customerId' with the actual value you want to delete

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

    @Override
    public Customer get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Customer> list() {
    
        List<Customer> list = new ArrayList<Customer>();
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "select * from customer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Customer cs = new Customer();
                cs.setCustomer_id(rs.getInt("customer_id"));
                cs.setName(rs.getString("name"));
                cs.setDate_of_birth(rs.getDate("date_of_birth"));
                cs.setAge(rs.getInt("age"));
                cs.setGender(rs.getString("gender"));
                cs.setNic_no(rs.getString("nic_no"));
                cs.setContact_no(rs.getString("contact_no"));
                cs.setEmail(rs.getString("email"));
                cs.setAddress(rs.getString("address"));
                
                list.add(cs);
            }
        }catch(Exception e){
    
            e.printStackTrace();
            
        }
    
        return list;
        
    }
}
