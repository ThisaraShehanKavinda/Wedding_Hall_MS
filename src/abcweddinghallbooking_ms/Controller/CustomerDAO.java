/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abcweddinghallbooking_ms.Controller;

import abcweddinghallbooking_ms.Model.Customer;
import java.util.List;

/**
 *
 * @author sanduni punchihewa
 */
public interface CustomerDAO {
      public void save(Customer customers);
       public void update (Customer customers);
       public void delete (Customer customers);
       public Customer get(int id);
       public List<Customer> list();
}
