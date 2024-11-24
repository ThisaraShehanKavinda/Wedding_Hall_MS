/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.Model.Hall;
import java.util.List;

/**
 *
 * @author sanduni punchihewa
 */
public interface HallDAO {
       public void save(Hall hall);
       public void update (Hall hall);
       public void delete (Hall hall);
       public Hall get(int id);
       public List<Hall> list();
}
