/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package abcweddinghallbooking_ms.View;

import abcweddinghallbooking_ms.DBConnection;

import abcweddinghallbooking_ms.Model.Booking;
import java.awt.geom.RoundRectangle2D;


import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;


/**
 *
 * @author sanduni punchihewa
 */



public class CardPayment extends javax.swing.JFrame {

    /**
     * Creates new form CardPayment
     */
    public CardPayment() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),40,40));
        
        cardPanel.setVisible(false);
        lblBookingError.setText("");
        lblTotalAmount.setText("________");
        
        
       
        
    }
    
    
    
    
     // Update total amount,payment type and payment status for cash payment
public void updatePaymentDetailsForCard(int hallID, int customerID, int totalAmount) {
    
    PreparedStatement statement = null;
    try {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE hall_book SET total_amount =? ,payment_type = 'Card', payment = 'Done' WHERE hall_id = ? AND customer_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, totalAmount);
        statement.setInt(2, hallID);
        statement.setInt(3, customerID);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
}

    
    
    
    
    // Update total amount,payment type and payment status for cash payment
public void updatePaymentDetailsForCash(int hallID, int customerID,int totalAmount) {
    
    PreparedStatement statement = null;
    try {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE hall_book SET total_amount =?,payment_type = 'Cash', payment = 'Done' WHERE hall_id = ? AND customer_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, totalAmount);
        statement.setInt(2, hallID);
        statement.setInt(3, customerID);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } 
}


// Update hall availability status
public void updateHallAvailability(int hallID) {
    
    PreparedStatement statement = null;
    try {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE hall SET availablity = 'Unavailable' WHERE hall_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, hallID);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } 
} 
    
    // Method to search for booking details based on customer ID and hall ID
public Booking searchBooking(int customerID, int hallID) {
    
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
        // Establish database connection
        Connection  connection = DBConnection.getConnection(); // Replace with your database connection method

        // Prepare the SQL query
        String query = "SELECT * FROM hall_book WHERE customer_id = ? AND hall_id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, customerID);
        statement.setInt(2, hallID);

        // Execute the query and retrieve the result set
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Booking record found, extract the details
            Booking booking = new Booking();
            booking.setBooking_id(resultSet.getInt("booking_id"));
            booking.setCustomer_id(resultSet.getInt("customer_id"));
            booking.setHall_id(resultSet.getInt("hall_id"));
            booking.setCheck_in(resultSet.getDate("check_in"));
            booking.setCheck_out(resultSet.getDate("check_out"));
            booking.setPrice_per_day(resultSet.getInt("price_per_day"));
            // Set other booking details as needed
            return booking;
        } else {
            // No booking found with the specified IDs
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle or log the exception as needed
        return null;
    } 
}
    
    
// Method to convert java.sql.Date to java.time.LocalDate
public LocalDate convertToLocalDate(Date date) {
    if (date != null) {
        return date.toLocalDate();
    }
    return null;
}


    
// Method to calculate the number of booking days
public long calculateNumberOfDays(Date checkInDate, Date checkOutDate) {
    // Convert the java.sql.Date objects to java.time.LocalDate objects
    LocalDate checkInLocalDate = checkInDate.toLocalDate();
    LocalDate checkOutLocalDate = checkOutDate.toLocalDate();

    // Calculate the number of days between check-in and check-out
    long numberOfDays = ChronoUnit.DAYS.between(checkInLocalDate, checkOutLocalDate);

    return numberOfDays;
}

// Method to calculate the total price
public int calculateTotalPrice(int pricePerDay, long numberOfDays) {
    int totalPrice = pricePerDay * (int) numberOfDays;
    return totalPrice;
}
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        checkBoxCash = new javax.swing.JCheckBox();
        checkBoxCard = new javax.swing.JCheckBox();
        jLabel40 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        lblRS = new javax.swing.JLabel();
        txtCustomerID = new rojerusan.RSMetroTextPlaceHolder();
        txtHallID = new rojerusan.RSMetroTextPlaceHolder();
        btnCashPay = new rojerusan.RSMaterialButtonRectangle();
        btnSearch = new rojerusan.RSMaterialButtonRectangle();
        lblBookingError = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblFullName = new rojerusan.RSMetroTextPlaceHolder();
        lblCountry = new rojerusan.RSMetroTextPlaceHolder();
        lblPostcode = new rojerusan.RSMetroTextPlaceHolder();
        lbldd = new rojerusan.RSMetroTextPlaceHolder();
        lblmm = new rojerusan.RSMetroTextPlaceHolder();
        lblcode = new rojerusan.RSMetroTextPlaceHolder();
        lblCardNumber = new rojerusan.RSMetroTextPlaceHolder();
        btnCardPay = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        checkBoxCash.setBackground(new java.awt.Color(204, 204, 204));
        checkBoxCash.setForeground(new java.awt.Color(0, 51, 0));
        checkBoxCash.setText("Cash");
        checkBoxCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxCashActionPerformed(evt);
            }
        });
        jPanel2.add(checkBoxCash);
        checkBoxCash.setBounds(147, 294, 60, 23);

        checkBoxCard.setBackground(new java.awt.Color(204, 204, 204));
        checkBoxCard.setForeground(new java.awt.Color(0, 51, 0));
        checkBoxCard.setText("Card");
        checkBoxCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxCardActionPerformed(evt);
            }
        });
        jPanel2.add(checkBoxCard);
        checkBoxCard.setBounds(147, 253, 60, 23);

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 51, 0));
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/degree.png"))); // NOI18N
        jLabel40.setText("Payment Type :");
        jPanel2.add(jLabel40);
        jLabel40.setBounds(15, 252, 114, 24);

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 0));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/degree.png"))); // NOI18N
        jLabel36.setText("Customer ID :");
        jPanel2.add(jLabel36);
        jLabel36.setBounds(15, 16, 110, 24);

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 0));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/degree.png"))); // NOI18N
        jLabel37.setText("Hall ID  :");
        jPanel2.add(jLabel37);
        jLabel37.setBounds(15, 68, 105, 24);

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 0));
        jLabel8.setText("Total Amount :");
        jLabel8.setAlignmentY(0.0F);
        jPanel2.add(jLabel8);
        jLabel8.setBounds(15, 194, 141, 26);

        lblTotalAmount.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalAmount.setForeground(new java.awt.Color(0, 51, 0));
        lblTotalAmount.setText("000000.00");
        lblTotalAmount.setAlignmentY(0.0F);
        jPanel2.add(lblTotalAmount);
        lblTotalAmount.setBounds(240, 190, 110, 40);

        lblRS.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblRS.setForeground(new java.awt.Color(0, 51, 0));
        lblRS.setText("RS.");
        lblRS.setAlignmentY(0.0F);
        jPanel2.add(lblRS);
        lblRS.setBounds(186, 194, 40, 26);

        txtCustomerID.setBackground(new java.awt.Color(204, 255, 255));
        txtCustomerID.setForeground(new java.awt.Color(0, 51, 0));
        txtCustomerID.setBorderColor(new java.awt.Color(204, 0, 0));
        txtCustomerID.setBotonColor(new java.awt.Color(0, 51, 0));
        txtCustomerID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(txtCustomerID);
        txtCustomerID.setBounds(132, 16, 215, 40);

        txtHallID.setBackground(new java.awt.Color(204, 255, 255));
        txtHallID.setForeground(new java.awt.Color(0, 51, 0));
        txtHallID.setBorderColor(new java.awt.Color(204, 0, 0));
        txtHallID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(txtHallID);
        txtHallID.setBounds(132, 63, 215, 40);

        btnCashPay.setBackground(new java.awt.Color(0, 51, 0));
        btnCashPay.setText("Pay");
        btnCashPay.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnCashPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashPayActionPerformed(evt);
            }
        });
        jPanel2.add(btnCashPay);
        btnCashPay.setBounds(138, 344, 121, 47);

        btnSearch.setBackground(new java.awt.Color(0, 102, 51));
        btnSearch.setText("Search");
        btnSearch.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch);
        btnSearch.setBounds(150, 140, 121, 40);

        lblBookingError.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblBookingError.setForeground(new java.awt.Color(153, 0, 0));
        lblBookingError.setText("Booking Not Found");
        lblBookingError.setAlignmentY(0.0F);
        jPanel2.add(lblBookingError);
        lblBookingError.setBounds(141, 113, 185, 18);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 130, 370, 400);

        kGradientPanel6.setkEndColor(new java.awt.Color(0, 0, 0));
        kGradientPanel6.setkStartColor(new java.awt.Color(51, 102, 0));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ABC COMPANY - RESOURCE ALLOCATION SYSTEM");
        jLabel5.setAlignmentY(0.0F);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/luxury.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/close.png"))); // NOI18N
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(16, 16, 16))
        );

        jPanel1.add(kGradientPanel6);
        kGradientPanel6.setBounds(0, 0, 861, 58);

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 0));
        jLabel6.setText(" PAYMENT");
        jLabel6.setAlignmentY(0.0F);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(390, 90, 154, 26);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/payment.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(320, 60, 64, 64);

        cardPanel.setBackground(new java.awt.Color(204, 204, 204));
        cardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 0, 0));
        jLabel10.setText("Payment Information");
        jLabel10.setAlignmentY(0.0F);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/credit-card (1).png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/rsz_3payment-methods.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 0));
        jLabel41.setText("Expiration Date(DD/MM)");

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 0, 0));
        jLabel42.setText("/");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 0, 0));
        jLabel43.setText("*");

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 0));
        jLabel44.setText("Security Code");

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 0, 0));
        jLabel45.setText("*");

        lblFullName.setBackground(new java.awt.Color(204, 255, 255));
        lblFullName.setForeground(new java.awt.Color(0, 51, 0));
        lblFullName.setBorderColor(new java.awt.Color(204, 0, 0));
        lblFullName.setBotonColor(new java.awt.Color(0, 51, 0));
        lblFullName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFullName.setPhColor(new java.awt.Color(0, 51, 51));
        lblFullName.setPlaceholder("* Full Name");

        lblCountry.setBackground(new java.awt.Color(204, 255, 255));
        lblCountry.setForeground(new java.awt.Color(0, 51, 0));
        lblCountry.setBorderColor(new java.awt.Color(204, 0, 0));
        lblCountry.setBotonColor(new java.awt.Color(0, 51, 0));
        lblCountry.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCountry.setPhColor(new java.awt.Color(0, 51, 0));
        lblCountry.setPlaceholder("* Country");

        lblPostcode.setBackground(new java.awt.Color(204, 255, 255));
        lblPostcode.setForeground(new java.awt.Color(0, 51, 0));
        lblPostcode.setBorderColor(new java.awt.Color(204, 0, 0));
        lblPostcode.setBotonColor(new java.awt.Color(0, 51, 0));
        lblPostcode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPostcode.setPhColor(new java.awt.Color(0, 51, 0));
        lblPostcode.setPlaceholder("* Postal Code");

        lbldd.setBackground(new java.awt.Color(204, 255, 255));
        lbldd.setForeground(new java.awt.Color(0, 51, 0));
        lbldd.setBorderColor(new java.awt.Color(204, 0, 0));
        lbldd.setBotonColor(new java.awt.Color(0, 51, 0));
        lbldd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbldd.setPhColor(new java.awt.Color(0, 51, 0));
        lbldd.setPlaceholder("DD");

        lblmm.setBackground(new java.awt.Color(204, 255, 255));
        lblmm.setForeground(new java.awt.Color(0, 51, 0));
        lblmm.setBorderColor(new java.awt.Color(204, 0, 0));
        lblmm.setBotonColor(new java.awt.Color(0, 51, 0));
        lblmm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblmm.setPhColor(new java.awt.Color(0, 51, 0));
        lblmm.setPlaceholder("MM");

        lblcode.setBackground(new java.awt.Color(204, 255, 255));
        lblcode.setForeground(new java.awt.Color(0, 51, 0));
        lblcode.setBorderColor(new java.awt.Color(204, 0, 0));
        lblcode.setBotonColor(new java.awt.Color(0, 51, 0));
        lblcode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblcode.setPhColor(new java.awt.Color(0, 51, 0));
        lblcode.setPlaceholder("Code");
        lblcode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblcodeFocusLost(evt);
            }
        });

        lblCardNumber.setBackground(new java.awt.Color(204, 255, 255));
        lblCardNumber.setForeground(new java.awt.Color(0, 51, 0));
        lblCardNumber.setBorderColor(new java.awt.Color(204, 0, 0));
        lblCardNumber.setBotonColor(new java.awt.Color(0, 51, 0));
        lblCardNumber.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCardNumber.setPhColor(new java.awt.Color(0, 51, 0));
        lblCardNumber.setPlaceholder("* Card Number");

        btnCardPay.setBackground(new java.awt.Color(0, 51, 0));
        btnCardPay.setText("Pay");
        btnCardPay.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        btnCardPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPanelLayout = new javax.swing.GroupLayout(cardPanel);
        cardPanel.setLayout(cardPanelLayout);
        cardPanelLayout.setHorizontalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPanelLayout.createSequentialGroup()
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardPanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addGroup(cardPanelLayout.createSequentialGroup()
                                        .addComponent(lbldd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(lblmm, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(72, 72, 72)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44)
                                    .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(cardPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCardNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cardPanelLayout.createSequentialGroup()
                                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPanelLayout.createSequentialGroup()
                        .addComponent(btnCardPay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(30, 30, 30))))
        );
        cardPanelLayout.setVerticalGroup(
            cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCardNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(lbldd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel43)
                    .addGroup(cardPanelLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel45))
                .addGap(28, 28, 28)
                .addComponent(btnCardPay, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(cardPanel);
        cardPanel.setBounds(390, 130, 456, 400);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 860, 560);

        setSize(new java.awt.Dimension(858, 557));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void checkBoxCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCashActionPerformed
        if(checkBoxCash.isSelected()){
            btnCashPay.setVisible(true);
            checkBoxCard.setSelected(false);
            cardPanel.setVisible(false);
        }else
        {
            checkBoxCard.setSelected(true);
        }
    }//GEN-LAST:event_checkBoxCashActionPerformed

    private void checkBoxCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCardActionPerformed
        if(checkBoxCard.isSelected()){
            btnCashPay.setVisible(false);
            checkBoxCash.setSelected(false);
            cardPanel.setVisible(true);

        }else
        {
            checkBoxCash.setSelected(true);
        }
    }//GEN-LAST:event_checkBoxCardActionPerformed

    private void btnCardPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardPayActionPerformed

        if(lblCardNumber.getText().isEmpty() || lblCountry.getText().isEmpty() || lblFullName.getText().isEmpty() || lblPostcode.getText().isEmpty() || lblcode.getText().isEmpty() || lblmm.getText().isEmpty() || lbldd.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Field is Empty");
        }
        else{
              try{          int hallID = Integer.parseInt(txtHallID.getText());
        int customerID = Integer.parseInt(txtCustomerID.getText());
        int totalPrice = Integer.parseInt(lblTotalAmount.getText());

        // Update booking table with payment details
        updatePaymentDetailsForCard(hallID, customerID,totalPrice);

        // Update hall table with availability status
        updateHallAvailability(hallID);

        // Update lblTotalPrice in the UI
        lblTotalAmount.setText(String.valueOf(totalPrice));
        txtCustomerID.setText("");
        txtHallID.setText("");
        lblBookingError.setText("");
        lblTotalAmount.setText("_________");
        lblBookingError.setText("");
        checkBoxCard.setSelected(false);
        checkBoxCash.setSelected(false);
        cardPanel.setVisible(false);
        
        // ...
        JOptionPane.showMessageDialog(this, "successfull Payment");
        // Clear the text fields or perform any other necessary actions
        // ...
}catch(Exception e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Unsuccessfull Payment");
}
        }
        
        
      
    }//GEN-LAST:event_btnCardPayActionPerformed

    private void btnCashPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashPayActionPerformed
try{        
    int hallID = Integer.parseInt(txtHallID.getText());
        int customerID = Integer.parseInt(txtCustomerID.getText());
        int totalPrice = Integer.parseInt(lblTotalAmount.getText());

        // Update booking table with payment details
        updatePaymentDetailsForCash(hallID, customerID,totalPrice);

        // Update hall table with availability status
        updateHallAvailability(hallID);

        // Update lblTotalPrice in the UI
        lblTotalAmount.setText(String.valueOf(totalPrice));

        // Clear the text fields or perform any other necessary actions
        
        txtCustomerID.setText("");
        txtHallID.setText("");
        lblBookingError.setText("");
        lblTotalAmount.setText("____");
        lblBookingError.setText("");
        checkBoxCard.setSelected(false);
        checkBoxCash.setSelected(false);
        cardPanel.setVisible(false);
        
        // ...
        JOptionPane.showMessageDialog(this, "successfull Payment");
        
        
}catch(Exception e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Unsuccessfull Payment");
}

    }//GEN-LAST:event_btnCashPayActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if(txtCustomerID.getText().isEmpty() || txtHallID.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Field is Empty");
        }
        else{
            int customerID = Integer.parseInt(txtCustomerID.getText());
        int hallID = Integer.parseInt(txtHallID.getText());

        // Search for booking details based on customer ID and hall ID
        Booking booking = searchBooking(customerID, hallID);

        if (booking != null) {
            // Retrieve check-in date, check-out date, and price per day
            Date checkInDate = (Date) booking.getCheck_in();
            Date checkOutDate = (Date) booking.getCheck_out();
            int pricePerDay = booking.getPrice_per_day();

            // Calculate number of booking days
            long numberOfDays = calculateNumberOfDays(checkInDate, checkOutDate);

            // Calculate total price
            int totalPrice = calculateTotalPrice(pricePerDay, numberOfDays);

            // Set the total price in the JLabel
            lblTotalAmount.setText(String.valueOf(totalPrice));
            lblBookingError.setText("");
        } else {
            // Handle the case when no booking is found
            lblBookingError.setText("Booking not found");
        }
        }
        
    
    }//GEN-LAST:event_btnSearchActionPerformed

    private void lblcodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblcodeFocusLost
       // Get the input from the txtCode textbox
String code = lblcode.getText();

// Define the regex pattern for four integers
String integerPattern = "^[0-9]{4}$";

// Validate the input against the pattern
if (code.matches(integerPattern)) {
  
} else {
    JOptionPane.showMessageDialog(this, "Invalid Security Code");
}

    }//GEN-LAST:event_lblcodeFocusLost

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
            java.util.logging.Logger.getLogger(CardPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CardPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CardPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CardPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CardPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnCardPay;
    private rojerusan.RSMaterialButtonRectangle btnCashPay;
    private rojerusan.RSMaterialButtonRectangle btnSearch;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JCheckBox checkBoxCard;
    private javax.swing.JCheckBox checkBoxCash;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private keeptoo.KGradientPanel kGradientPanel6;
    private javax.swing.JLabel lblBookingError;
    private rojerusan.RSMetroTextPlaceHolder lblCardNumber;
    private rojerusan.RSMetroTextPlaceHolder lblCountry;
    private rojerusan.RSMetroTextPlaceHolder lblFullName;
    private rojerusan.RSMetroTextPlaceHolder lblPostcode;
    private javax.swing.JLabel lblRS;
    private javax.swing.JLabel lblTotalAmount;
    private rojerusan.RSMetroTextPlaceHolder lblcode;
    private rojerusan.RSMetroTextPlaceHolder lbldd;
    private rojerusan.RSMetroTextPlaceHolder lblmm;
    private rojerusan.RSMetroTextPlaceHolder txtCustomerID;
    private rojerusan.RSMetroTextPlaceHolder txtHallID;
    // End of variables declaration//GEN-END:variables
}
