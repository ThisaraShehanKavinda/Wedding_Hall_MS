/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.Model.Admin;
import java.util.List;

/**
 *
 * @author sanduni punchihewa
 */
public interface AdminDAO {
     public void save(Admin admin);
       public void update (Admin admin);
       public void delete (Admin admin);
       public Admin get(int id);
       public List<Admin> list();
}
