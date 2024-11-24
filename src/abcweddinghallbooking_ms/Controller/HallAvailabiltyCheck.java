/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.DBConnection;
import java.time.LocalDate;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author sanduni punchihewa
 */
public class HallAvailabiltyCheck {
    public boolean isbooked(int hallID, LocalDate startDate, LocalDate endDate){
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from hall_book where hall_id=? and ((check_in <=?and check_out>=?) or (check_in<=? and check_out>=?)or (check_in>=? and check_out <=?))";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hallID);
            ps.setString(2, startDate.toString());
            ps.setString(3, startDate.toString());
            ps.setString(4, endDate.toString());
            ps.setString(5, endDate.toString());
            ps.setString(6, startDate.toString());
            ps.setString(7, endDate.toString());
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    JOptionPane.showMessageDialog(null, "Hall is Unavailable");
                    return true;
                }
            }
            
            JOptionPane.showMessageDialog(null, "Hall is available");
            return false;
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occured while checking availablity");
            return false;
        }
    }
}
