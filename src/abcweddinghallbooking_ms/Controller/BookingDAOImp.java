/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.DBConnection;
import abcweddinghallbooking_ms.Model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;


/**
/**
 *
 * @author sanduni punchihewa
 */
public class BookingDAOImp implements BookingDAO {

    /**
     *
     * @param booking
     */
    @Override
   
public void save(Booking booking) {
    java.util.Date checkin = booking.getCheck_in();
    java.util.Date checkout = booking.getCheck_out();

    try {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT into hall_book(customer_id,hall_id,price_per_day,check_in,check_out,total_amount,payment_type,payment) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, booking.getCustomer_id());
        ps.setInt(2, booking.getHall_id());
        ps.setInt(3, booking.getPrice_per_day());
        ps.setDate(4, new java.sql.Date(checkin.getTime()));
        ps.setDate(5, new java.sql.Date(checkout.getTime()));
        ps.setInt(6, booking.getTotal_amount());
        ps.setString(7, booking.getPayment_type());
        ps.setString(8, booking.getPayment());

        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Saved!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
    @Override
    public void update(Booking booking) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void delete(Booking booking) {
       try{
            
           Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM hall_book WHERE booking_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, booking.getBooking_id()); 

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
    public Booking get(int id) {
         Booking adm = new Booking();
    
    try{
        Connection con = DBConnection.getConnection();
        String sql = "select * from hall_book where booking_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
          adm.setCustomer_id(rs.getInt("customer_id"));
                adm.setHall_id(rs.getInt("hall_id"));
                adm.setPayment(rs.getString("payment"));
                
                
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    
    return adm;
    }

    
    @Override
    public List<Booking> list() {
        List<Booking> list = new ArrayList<Booking>();
        try{
            
            Connection con = DBConnection.getConnection();
            String sql = "select * from hall_book";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Booking book = new Booking();
                book.setBooking_id(rs.getInt("booking_id"));
                book.setCustomer_id(rs.getInt("customer_id"));
                book.setHall_id(rs.getInt("hall_id"));
                book.setPrice_per_day(rs.getInt("price_per_day"));
                book.setCheck_in(rs.getDate("check_in"));
                book.setCheck_out(rs.getDate("check_out"));
                book.setTotal_amount(rs.getInt("total_amount"));
                book.setPayment_type(rs.getString("payment_type"));
                book.setPayment(rs.getString("payment"));
                
                
                list.add(book);
            }
        }catch(Exception e){
    
            e.printStackTrace();
            
        }
    
        return list;
    }
    
}
