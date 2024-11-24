/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.Model.Booking;
import java.util.List;

/**
 *
 * @author sanduni punchihewa
 */
public interface BookingDAO {
    public void save(Booking booking);
       public void update (Booking booking);
       public void delete (Booking booking);
       public Booking get(int id);
       public List<Booking> list();
}
