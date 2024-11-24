/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package abcweddinghallbooking_ms.View;

import abcweddinghallbooking_ms.Controller.CustomerDAOImp;
import abcweddinghallbooking_ms.Controller.HallDAOImp;
import abcweddinghallbooking_ms.DBConnection;
import abcweddinghallbooking_ms.Model.Customer;
import abcweddinghallbooking_ms.Model.Hall;
import java.awt.BorderLayout;

import java.awt.geom.RoundRectangle2D;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author sanduni punchihewa
 */
public class AdminHome extends javax.swing.JFrame {

    /**
     * Creates new form AdminHome
     */
    public AdminHome() {
        initComponents();
        Load();
        Load1();
        setDatatoCards();
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),40,40));
        curDateTime();
        
        
    
        
    }
    
    
   
    
    
    
    
    private JTextArea textArea;

    public AdminHome(List<String> bookingDetails) {
        // Set the frame title
        setTitle("Booking Details");

        // Create a JTextArea
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        // Set the text content of the JTextArea
        StringBuilder content = new StringBuilder();
        for (String detail : bookingDetails) {
            content.append(detail).append("\n");
        }
        textArea.setText(content.toString());

        // Add the JTextArea to the frame
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    
    
    
    
    
    public List<String> getBookingDetails() {
    List<String> bookingDetails = new ArrayList<>();

    // Create a database connection
    
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        Connection connection = DBConnection.getConnection();

        // Prepare the SQL statement
        String sql = "SELECT hall_id, check_in, check_out FROM hall_book";
        statement = connection.prepareStatement(sql);

        // Execute the query
        resultSet = statement.executeQuery();

        // Iterate over the result set and add the details to the list
        while (resultSet.next()) {
            int hallID = resultSet.getInt("hall_id");
            Date checkInDate = resultSet.getDate("check_in");
            Date checkOutDate = resultSet.getDate("check_out");

            String bookingDetail = "Hall ID: " + hallID + ", Check-in Date: " + checkInDate + ", Check-out Date: " + checkOutDate;
            bookingDetails.add(bookingDetail);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    

    return bookingDetails;
}

    
    
    
        
    public int fetchNumberOfCustomersFromDatabase() {
    int numberOfCustomers = 0;

    try {
        // Establish the database connection
        Connection con = DBConnection.getConnection();

        // Create the SQL statement
        String query = "SELECT COUNT(*) FROM customer";

        // Create a prepared statement
        PreparedStatement statement = con.prepareStatement(query);

        // Execute the query and retrieve the result
        ResultSet resultSet = statement.executeQuery();

        // Extract the count value from the result
        if (resultSet.next()) {
            numberOfCustomers = resultSet.getInt(1);
        }

        // Close the resources
        resultSet.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return numberOfCustomers;
}
    
    public int fetchNumberOfHallsFromDatabase() {
    int numberOfHalls = 0;

    try {
        // Establish the database connection
        Connection con = DBConnection.getConnection();

        // Create the SQL statement
        String query = "SELECT COUNT(*) FROM hall";

        // Create a prepared statement
        PreparedStatement statement = con.prepareStatement(query);

        // Execute the query and retrieve the result
        ResultSet resultSet = statement.executeQuery();
        
        // Extract the count value from the result
        if (resultSet.next()) {
            numberOfHalls = resultSet.getInt(1);
        }

        // Close the resources
        resultSet.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return numberOfHalls;
}

    
    
    
    public int fetchNumberOfUsersFromDatabase() {
    int numberOfUsers = 0;

    try {
        // Establish the database connection
        Connection con = DBConnection.getConnection();

        // Create the SQL statement
        String query = "SELECT COUNT(*) FROM admin";

        // Create a prepared statement
        PreparedStatement statement = con.prepareStatement(query);

        // Execute the query and retrieve the result
        ResultSet resultSet = statement.executeQuery();

        // Extract the count value from the result
        if (resultSet.next()) {
            numberOfUsers = resultSet.getInt(1);
        }

        // Close the resources
        resultSet.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return numberOfUsers;
}
    
    
    public void displayBarGraph() {
    // Create the dataset
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    // Fetch the data from the database and add it to the dataset
    int numOfHalls = fetchNumberOfHallsFromDatabase();
    int numOfCustomers = fetchNumberOfCustomersFromDatabase();
    int numOfUsers = fetchNumberOfUsersFromDatabase();
    
    dataset.addValue(numOfHalls, "Halls", "Halls");
    dataset.addValue(numOfCustomers, "Customers", "Customers");
    dataset.addValue(numOfUsers, "Users", "Users");

    // Create the chart
    JFreeChart chart = ChartFactory.createBarChart(
        "Data Overview",
        "Data Type",
        "Count",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );
    
   // Display the chart in a JFrame or any other container
    ChartFrame chartFrame = new ChartFrame("Data Overview", chart);
    chartFrame.pack();
    chartFrame.setLocationRelativeTo(null); // Set the frame to open in the center of the screen
    chartFrame.setVisible(true);
}
    
    
    
    public void curDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd    HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        lblDate.setText(dtf.format(now));
    }
    
    
    public void setDatatoCards(){
        Statement st = null;
        ResultSet rst = null;
        
        long l = System.currentTimeMillis();
        Date toDaysDate = new Date();
        try{
            Connection con =  DBConnection.getConnection();
            st = con.createStatement();
            rst = st.executeQuery("select * from admin ");
            rst.last();
            
            lbladmiinNo.setText(Integer.toString(rst.getRow()));
        
        
        
        rst = st.executeQuery("select * from customer");
        rst.last();
        lblCustoerNo.setText(Integer.toString(rst.getRow()));
        
        
        rst = st.executeQuery("select * from hall");
        rst.last();
        lblHallNo.setText(Integer.toString(rst.getRow()));
        
        
        rst = st.executeQuery("select * from hall_book");
        rst.last();
        lblBookingNo.setText(Integer.toString(rst.getRow()));
        
        
        
    }catch(Exception ex){
    ex.printStackTrace();
}
    
    }
    

     public void Load1() {
        CustomerDAOImp dao = new CustomerDAOImp();
        List<Customer> list = dao.list();
        DefaultTableModel dft = (DefaultTableModel) tblCustomer.getModel();
        dft.setRowCount(0);
        for (Customer cs : list) {
            int id = cs.getCustomer_id();
            String name = cs.getName();
            Date date_of_birth = cs.getDate_of_birth();
            int age = cs.getAge();
            String gender = cs.getGender();
            String nic_no = cs.getNic_no();
            String contact_no = cs.getContact_no();
            String email = cs.getEmail();
            String address = cs.getAddress();

            dft.addRow(new Object[]{id, name, date_of_birth, age, gender, nic_no, contact_no, email, address});
        }
    }
    
    
    public void Load() {
        HallDAOImp dao = new HallDAOImp();
        List<Hall> list = dao.list();
        DefaultTableModel dft = (DefaultTableModel) tblHall.getModel();
        dft.setRowCount(0);
        for (Hall hall : list) {
            int id = hall.getHallID();
            String name = hall.getHallName();
           
            int ppd = hall.getPricePerDay();
            String type = hall.getHallType();
            String availability = hall.getAvailablity();
            Blob photo = hall.getPhoto();
           ;

            dft.addRow(new Object[]{id, name,  type,ppd, availability,  photo});
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel5 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover11 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover10 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover9 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover8 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover6 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover5 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover4 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover3 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        jPanel14 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        lbladmiinNo = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        lblCustoerNo = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        lblHallNo = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        lblBookingNo = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new rojeru_san.complementos.RSTableMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHall = new rojeru_san.complementos.RSTableMetro();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle4 = new necesario.RSMaterialButtonCircle();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rSButtonHover7 = new rojerusan.RSButtonHover();
        kGradientPanel8 = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        rSButtonHover12 = new rojerusan.RSButtonHover();
        kGradientPanel9 = new keeptoo.KGradientPanel();
        jLabel29 = new javax.swing.JLabel();
        rSButtonHover14 = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel5.setkEndColor(new java.awt.Color(0, 0, 0));
        kGradientPanel5.setkStartColor(new java.awt.Color(51, 102, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/menu (1).png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 51, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(2, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ABC COMPANY - RESOURCE ALLOCATION SYSTEM");
        jLabel4.setAlignmentY(0.0F);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/luxury.png"))); // NOI18N

        lblDate.setFont(new java.awt.Font("Lucida Console", 0, 13)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("2022.05.04   23:34:45");

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblDate))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 50));

        kGradientPanel6.setkEndColor(new java.awt.Color(0, 204, 51));
        kGradientPanel6.setkStartColor(new java.awt.Color(0, 51, 0));
        kGradientPanel6.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/home (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel4);
        jPanel4.setBounds(0, 0, 50, 40);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel5);
        jPanel5.setBounds(0, 50, 50, 40);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/municipal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel6);
        jPanel6.setBounds(0, 100, 50, 40);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel7);
        jPanel7.setBounds(0, 150, 50, 40);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customer.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel8);
        jPanel8.setBounds(0, 200, 50, 40);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/click.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel9);
        jPanel9.setBounds(0, 250, 50, 40);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/document.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel10);
        jPanel10.setBounds(0, 300, 50, 40);

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/online-payment.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel11);
        jPanel11.setBounds(0, 350, 50, 40);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exit.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel12);
        jPanel12.setBounds(0, 530, 50, 40);

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/no.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        kGradientPanel6.add(jPanel13);
        jPanel13.setBounds(0, 580, 50, 40);

        rSButtonHover1.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover1.setText("Exit");
        rSButtonHover1.setColorHover(java.awt.Color.red);
        rSButtonHover1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover1);
        rSButtonHover1.setBounds(50, 580, 180, 40);

        rSButtonHover11.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover11.setText("LogOut");
        rSButtonHover11.setColorHover(java.awt.Color.orange);
        rSButtonHover11.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover11ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover11);
        rSButtonHover11.setBounds(50, 530, 180, 40);

        rSButtonHover10.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover10.setText("Payment");
        rSButtonHover10.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover10.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover10.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover10ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover10);
        rSButtonHover10.setBounds(50, 350, 180, 40);

        rSButtonHover9.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover9.setText("Cancellation");
        rSButtonHover9.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover9.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover9.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover9ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover9);
        rSButtonHover9.setBounds(50, 300, 180, 40);

        rSButtonHover8.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover8.setText("Booking");
        rSButtonHover8.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover8.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover8.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover8ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover8);
        rSButtonHover8.setBounds(50, 250, 180, 40);

        rSButtonHover6.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover6.setText("Customer");
        rSButtonHover6.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover6.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover6.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover6ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover6);
        rSButtonHover6.setBounds(50, 200, 180, 40);

        rSButtonHover5.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover5.setText("Search Hall");
        rSButtonHover5.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover5.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover5ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover5);
        rSButtonHover5.setBounds(50, 150, 180, 40);

        rSButtonHover4.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover4.setText("Hall");
        rSButtonHover4.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover4.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover4.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover4);
        rSButtonHover4.setBounds(50, 100, 180, 40);

        rSButtonHover3.setBackground(new java.awt.Color(0, 102, 51));
        rSButtonHover3.setText("Admin");
        rSButtonHover3.setColorHover(new java.awt.Color(0, 255, 51));
        rSButtonHover3.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover3.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover3);
        rSButtonHover3.setBounds(50, 50, 180, 40);

        rSButtonHover2.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonHover2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(51, 255, 0)));
        rSButtonHover2.setText("Home");
        rSButtonHover2.setColorHover(new java.awt.Color(255, 255, 255));
        rSButtonHover2.setColorText(new java.awt.Color(0, 51, 0));
        rSButtonHover2.setColorTextHover(new java.awt.Color(0, 51, 0));
        rSButtonHover2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(rSButtonHover2);
        rSButtonHover2.setBounds(50, 0, 190, 40);

        getContentPane().add(kGradientPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 230, 630));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(null);

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 204, 0)));
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 204));
        kGradientPanel1.setkStartColor(new java.awt.Color(2, 56, 12));
        kGradientPanel1.setOpaque(false);
        kGradientPanel1.setLayout(null);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ADMINS");
        kGradientPanel1.add(jLabel17);
        jLabel17.setBounds(63, 13, 90, 24);

        lbladmiinNo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lbladmiinNo.setForeground(new java.awt.Color(255, 255, 255));
        lbladmiinNo.setText("10");
        kGradientPanel1.add(lbladmiinNo);
        lbladmiinNo.setBounds(104, 63, 40, 47);

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/group (1).png"))); // NOI18N
        kGradientPanel1.add(jLabel23);
        jLabel23.setBounds(22, 55, 64, 64);

        jPanel14.add(kGradientPanel1);
        kGradientPanel1.setBounds(20, 30, 180, 150);

        kGradientPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 204, 0)));
        kGradientPanel2.setkEndColor(new java.awt.Color(255, 255, 204));
        kGradientPanel2.setkStartColor(new java.awt.Color(14, 43, 4));
        kGradientPanel2.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CUSTOMERS");
        kGradientPanel2.add(jLabel15);
        jLabel15.setBounds(40, 13, 120, 24);

        lblCustoerNo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblCustoerNo.setForeground(new java.awt.Color(255, 255, 255));
        lblCustoerNo.setText("10");
        kGradientPanel2.add(lblCustoerNo);
        lblCustoerNo.setBounds(104, 63, 40, 47);

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customer-loyalty.png"))); // NOI18N
        kGradientPanel2.add(jLabel25);
        jLabel25.setBounds(22, 53, 64, 64);

        jPanel14.add(kGradientPanel2);
        kGradientPanel2.setBounds(210, 30, 190, 150);

        kGradientPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 204, 0)));
        kGradientPanel3.setkEndColor(new java.awt.Color(255, 255, 204));
        kGradientPanel3.setkStartColor(new java.awt.Color(4, 16, 3));
        kGradientPanel3.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Halls");
        kGradientPanel3.add(jLabel16);
        jLabel16.setBounds(76, 13, 60, 24);

        lblHallNo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblHallNo.setForeground(new java.awt.Color(255, 255, 255));
        lblHallNo.setText("10");
        kGradientPanel3.add(lblHallNo);
        lblHallNo.setBounds(104, 55, 40, 47);

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hall.png"))); // NOI18N
        kGradientPanel3.add(jLabel24);
        jLabel24.setBounds(22, 55, 64, 64);

        jPanel14.add(kGradientPanel3);
        kGradientPanel3.setBounds(410, 30, 180, 150);

        kGradientPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 204, 0)));
        kGradientPanel4.setkEndColor(new java.awt.Color(255, 204, 153));
        kGradientPanel4.setkStartColor(new java.awt.Color(0, 0, 0));
        kGradientPanel4.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("BOOKINGS");
        kGradientPanel4.add(jLabel18);
        jLabel18.setBounds(41, 13, 110, 24);

        lblBookingNo.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblBookingNo.setForeground(new java.awt.Color(255, 255, 255));
        lblBookingNo.setText("10");
        kGradientPanel4.add(lblBookingNo);
        lblBookingNo.setBounds(104, 64, 40, 47);

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/hotel-service.png"))); // NOI18N
        kGradientPanel4.add(jLabel27);
        jLabel27.setBounds(28, 64, 64, 64);

        jPanel14.add(kGradientPanel4);
        kGradientPanel4.setBounds(600, 30, 180, 150);

        tblCustomer.setBackground(new java.awt.Color(0, 51, 0));
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Name", "Date Of Birth", "Age", "Gender", "Nic No", "Contact No", "Email", "Address"
            }
        ));
        tblCustomer.setColorBackgoundHead(new java.awt.Color(0, 51, 0));
        tblCustomer.setColorFilasForeground1(new java.awt.Color(0, 102, 0));
        tblCustomer.setColorFilasForeground2(new java.awt.Color(0, 102, 0));
        tblCustomer.setColorSelBackgound(new java.awt.Color(0, 102, 0));
        tblCustomer.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCustomer.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblCustomer.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        jPanel14.add(jScrollPane1);
        jScrollPane1.setBounds(20, 260, 500, 160);

        tblHall.setBackground(new java.awt.Color(0, 51, 0));
        tblHall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hall ID", "Hall Name", "Hall Type", "Price per Day", "Availability", "Photo"
            }
        ));
        tblHall.setColorBackgoundHead(new java.awt.Color(0, 51, 0));
        tblHall.setColorFilasForeground1(new java.awt.Color(0, 102, 0));
        tblHall.setColorFilasForeground2(new java.awt.Color(0, 102, 0));
        tblHall.setColorSelBackgound(new java.awt.Color(0, 102, 0));
        tblHall.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblHall.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblHall.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblHall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHallMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHall);

        jPanel14.add(jScrollPane2);
        jScrollPane2.setBounds(20, 450, 500, 170);

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 0));
        jLabel19.setText("Halls");
        jPanel14.add(jLabel19);
        jLabel19.setBounds(240, 420, 110, 24);

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 0));
        jLabel20.setText("Customers");
        jPanel14.add(jLabel20);
        jLabel20.setBounds(230, 230, 110, 24);

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel14.add(rSMaterialButtonCircle1);
        rSMaterialButtonCircle1.setBounds(20, 10, 180, 190);

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(0, 102, 0));
        jPanel14.add(rSMaterialButtonCircle2);
        rSMaterialButtonCircle2.setBounds(600, 10, 180, 190);

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 102, 0));
        jPanel14.add(rSMaterialButtonCircle3);
        rSMaterialButtonCircle3.setBounds(410, 10, 180, 190);

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel14.add(rSMaterialButtonCircle4);
        rSMaterialButtonCircle4.setBounds(220, 10, 180, 190);

        kGradientPanel7.setkEndColor(new java.awt.Color(0, 51, 0));
        kGradientPanel7.setkStartColor(new java.awt.Color(51, 153, 0));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("If you want a quick Over view");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("of  Customers Halls and Admins");

        rSButtonHover7.setBackground(new java.awt.Color(0, 51, 0));
        rSButtonHover7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tap.png"))); // NOI18N
        rSButtonHover7.setText("CLICK HERE");
        rSButtonHover7.setColorHover(new java.awt.Color(51, 204, 0));
        rSButtonHover7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSButtonHover7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel14.add(kGradientPanel7);
        kGradientPanel7.setBounds(560, 510, 220, 94);

        kGradientPanel8.setkEndColor(new java.awt.Color(0, 51, 0));
        kGradientPanel8.setkStartColor(new java.awt.Color(51, 153, 0));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("If you want a quick Over view");

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Halls that unavailable");

        rSButtonHover12.setBackground(new java.awt.Color(0, 51, 0));
        rSButtonHover12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tap.png"))); // NOI18N
        rSButtonHover12.setText("CLICK HERE");
        rSButtonHover12.setColorHover(new java.awt.Color(51, 204, 0));
        rSButtonHover12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(47, 47, 47))
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel8Layout.createSequentialGroup()
                        .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(2, 2, 2)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel14.add(kGradientPanel8);
        kGradientPanel8.setBounds(560, 390, 220, 94);

        kGradientPanel9.setkEndColor(new java.awt.Color(0, 51, 0));
        kGradientPanel9.setkStartColor(new java.awt.Color(51, 153, 0));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Halls in Gallery view");

        rSButtonHover14.setBackground(new java.awt.Color(0, 51, 0));
        rSButtonHover14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tap.png"))); // NOI18N
        rSButtonHover14.setText("CLICK HERE");
        rSButtonHover14.setColorHover(new java.awt.Color(51, 204, 0));
        rSButtonHover14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                        .addComponent(rSButtonHover14, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonHover14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel14.add(kGradientPanel9);
        kGradientPanel9.setBounds(560, 270, 218, 100);

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 790, 630));

        setSize(new java.awt.Dimension(1018, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void rSButtonHover11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover11ActionPerformed
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover11ActionPerformed

    private void rSButtonHover10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover10ActionPerformed
        Apayment payment = new Apayment();
        payment.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover10ActionPerformed

    private void rSButtonHover9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover9ActionPerformed
        ACancellation cancellation = new ACancellation();
        cancellation.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover9ActionPerformed

    private void rSButtonHover8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover8ActionPerformed
        ABookingHall booking = new ABookingHall();
        booking.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover8ActionPerformed

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        ACustomer customer = new ACustomer();
        customer.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void rSButtonHover5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover5ActionPerformed
        AsearchHall asearch = new AsearchHall();
        asearch.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover5ActionPerformed

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        AHall ahall = new AHall();
        ahall.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        User user = new User();
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        AdminHome ahome = new AdminHome();
        ahome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked

       
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void rSButtonHover7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover7ActionPerformed
        displayBarGraph();
    }//GEN-LAST:event_rSButtonHover7ActionPerformed

    private void rSButtonHover12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover12ActionPerformed
       
          List<String> bookingDetails = getBookingDetails();
        AdminHome frame = new AdminHome(bookingDetails);
    }//GEN-LAST:event_rSButtonHover12ActionPerformed

    private void rSButtonHover14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover14ActionPerformed
       Gallery gallery = new Gallery();
       gallery.setVisible(true);
    }//GEN-LAST:event_rSButtonHover14ActionPerformed

    private void tblHallMouseClicked(java.awt.event.MouseEvent evt) {                                     

       
                                                
    }                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private keeptoo.KGradientPanel kGradientPanel8;
    private keeptoo.KGradientPanel kGradientPanel9;
    private javax.swing.JLabel lblBookingNo;
    private javax.swing.JLabel lblCustoerNo;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblHallNo;
    private javax.swing.JLabel lbladmiinNo;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover10;
    private rojeru_san.complementos.RSButtonHover rSButtonHover11;
    private rojerusan.RSButtonHover rSButtonHover12;
    private rojerusan.RSButtonHover rSButtonHover14;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover3;
    private rojeru_san.complementos.RSButtonHover rSButtonHover4;
    private rojeru_san.complementos.RSButtonHover rSButtonHover5;
    private rojeru_san.complementos.RSButtonHover rSButtonHover6;
    private rojerusan.RSButtonHover rSButtonHover7;
    private rojeru_san.complementos.RSButtonHover rSButtonHover8;
    private rojeru_san.complementos.RSButtonHover rSButtonHover9;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojeru_san.complementos.RSTableMetro tblCustomer;
    private rojeru_san.complementos.RSTableMetro tblHall;
    // End of variables declaration//GEN-END:variables
}
