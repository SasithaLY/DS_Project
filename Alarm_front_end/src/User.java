
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sasitha
 */
public class User extends javax.swing.JFrame {

    public static Service service = null; //Reference to the RMI Server.
    public static String AdminToken = null; //AdminToken declaration.

    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        lblresponse.setText("");
        errorInsert.setVisible(false);
        errorUpdate.setVisible(false);
        errorDelete.setVisible(false);
        try {
            getUserData();
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblresponse = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        usernameInsert = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        passwordInsert = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        phoneInsert = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        emailInsert = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbRoleInsert = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbStatusInsert = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        errorInsert = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        usernameUpdate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        passwordUpdate = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        phoneUpdate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        emailUpdate = new javax.swing.JTextField();
        cmbStatusUpdate = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbRoleUpdate = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        errorUpdate = new javax.swing.JLabel();
        errorDelete = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        userIdUpdate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Users");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "Username", "Phone", "Email", "Role", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        lblresponse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblresponse, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblresponse, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Insert New User");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Username :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Password :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Phone :");

        phoneInsert.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Email :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Role :");

        cmbRoleInsert.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Status :");

        cmbStatusInsert.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbStatusInsert.setSelectedIndex(1);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        errorInsert.setForeground(new java.awt.Color(255, 0, 0));
        errorInsert.setText("Error! Check Input Fields");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addComponent(errorInsert)
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordInsert)
                            .addComponent(usernameInsert)
                            .addComponent(phoneInsert)
                            .addComponent(emailInsert)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbStatusInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbRoleInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(errorInsert))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(phoneInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(emailInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbRoleInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbStatusInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Update User");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Username :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Password :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Phone :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Email :");

        cmbStatusUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbStatusUpdate.setSelectedIndex(1);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Role :");

        cmbRoleUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Status :");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        errorUpdate.setForeground(new java.awt.Color(255, 0, 0));
        errorUpdate.setText("Error! Check Input Fields");

        errorDelete.setForeground(new java.awt.Color(255, 0, 0));
        errorDelete.setText("Please Select an User");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("User ID :");

        userIdUpdate.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(errorDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(51, 51, 51)
                        .addComponent(errorUpdate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(37, 37, 37)
                                .addComponent(emailUpdate))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(29, 29, 29)
                                .addComponent(phoneUpdate))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(13, 13, 13)
                                .addComponent(passwordUpdate))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cmbRoleUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbStatusUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel15))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(userIdUpdate)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(errorUpdate))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(userIdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(usernameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(passwordUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(phoneUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(emailUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbRoleUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbStatusUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(errorDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.getAccessibleContext().setAccessibleName("ID :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (validateInsert()) {
            errorInsert.setVisible(false);

            String username = usernameInsert.getText();
            String password = passwordInsert.getPassword().toString();
            String phone = phoneInsert.getText();
            String email = emailInsert.getText();
            String role = cmbRoleInsert.getSelectedItem().toString();
            int active = cmbStatusInsert.getSelectedIndex();

            String jsonString = ""; //Creating a method to make a JSONObject.
            try {
                jsonString = new JSONObject()
                        .put("username", username)
                        .put("password", password)
                        .put("phone", phone)
                        .put("email", email)
                        .put("role", role)
                        .put("active", active).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String[] response = new String[3];

            try {
                response = service.addUser(jsonString, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response[0].equals("200")) {
                try {
                    getUserData();
                } catch (RemoteException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblresponse.setText("User Successfully Registered!");  //Getting the POST response and display success.
                passwordInsert.setText(""); //Setting blanks to the inputs.
                usernameInsert.setText(""); //Setting blanks to the inputs.
                phoneInsert.setText(""); //Setting blanks to the inputs.
                emailInsert.setText(""); //Setting blanks to the inputs.
                cmbRoleInsert.setSelectedIndex(0); //set the default Role
                cmbStatusInsert.setSelectedIndex(1); //set the default status

            } else if (!response[0].equals("200")) {
                lblresponse.setText("Error in User Register!"); //Show this if JSON responded an error.
            }
        } else {
            errorInsert.setVisible(true);
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int rowIndex = jTable1.getSelectedRow();

        userIdUpdate.setText(model.getValueAt(rowIndex, 0).toString());
        usernameUpdate.setText(model.getValueAt(rowIndex, 1).toString());
        phoneUpdate.setText(model.getValueAt(rowIndex, 2).toString());
        emailUpdate.setText(model.getValueAt(rowIndex, 3).toString());
        cmbRoleUpdate.setSelectedItem(model.getValueAt(rowIndex, 4).toString());
        cmbStatusUpdate.setSelectedItem(model.getValueAt(rowIndex, 5).toString());

        //cmbUpdateStatus.setSelectedIndex(Integer.parseInt(model.getValueAt(rowIndex, 1).toString()));

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        String id = userIdUpdate.getText().toString();

        if (!id.isEmpty()) {
            errorDelete.setVisible(false);
            String[] response = new String[3];

            try {
                response = service.deleteUser(id, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response[0].equals("200")) {
                try {
                    getUserData();

                } catch (RemoteException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblresponse.setText("User Deleted Successfully!");  //Getting the POST response and display success.
                userIdUpdate.setText(""); //Setting blanks to the inputs.
                passwordUpdate.setText(""); //Setting blanks to the inputs.
                usernameUpdate.setText(""); //Setting blanks to the inputs.
                phoneUpdate.setText(""); //Setting blanks to the inputs.
                emailUpdate.setText(""); //Setting blanks to the inputs.
                cmbRoleUpdate.setSelectedIndex(0); //set the default Role
                cmbStatusUpdate.setSelectedIndex(1); //set the default status

            } else if (!response[0].equals("200")) {
                lblresponse.setText("Error in User Delete!"); //Show this if JSON responded an error.
            }
        } else {
            errorDelete.setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        if (validateUpdate()) {
            errorUpdate.setVisible(false);

            String id = userIdUpdate.getText();
            String username = usernameUpdate.getText();
            String password = passwordUpdate.getPassword().toString();
            String phone = phoneUpdate.getText();
            String email = emailUpdate.getText();
            String role = cmbRoleUpdate.getSelectedItem().toString();
            int active = cmbStatusUpdate.getSelectedIndex();

            String jsonString = ""; //As mentioned earlier this is used to create a JSON Object.
            try {
                jsonString = new JSONObject()
                        .put("id", id)
                        .put("username", username)
                        .put("password", password)
                        .put("phone", phone)
                        .put("email", email)
                        .put("role", role)
                        .put("active", active).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String[] response = new String[3];

            try {
                response = service.updateUser(jsonString, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response[0].equals("200")) {
                try {
                    getUserData();
                } catch (RemoteException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                lblresponse.setText("User Successfully Updated!");  //Getting the POST response and display success.
                userIdUpdate.setText(""); //Setting blanks to the inputs.
                passwordUpdate.setText(""); //Setting blanks to the inputs.
                usernameUpdate.setText(""); //Setting blanks to the inputs.
                phoneUpdate.setText(""); //Setting blanks to the inputs.
                emailUpdate.setText(""); //Setting blanks to the inputs.
                cmbRoleUpdate.setSelectedIndex(0); //set the default Role
                cmbStatusUpdate.setSelectedIndex(1); //set the default status

            } else if (!response[0].equals("200")) {
                lblresponse.setText("Error in User Update!");
            }
        } else {
            errorUpdate.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    public Boolean validateInsert() {
        int count = 0;

        if (usernameInsert.getText().isEmpty()) {
            count++;
        }
        if (passwordInsert.getPassword().equals("")) {
            count++;
        }
        if (phoneInsert.getText().isEmpty()) {
            count++;
        }
        if (emailInsert.getText().isEmpty()) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean validateUpdate() {
        int count = 0;

        if (userIdUpdate.getText().isEmpty()) {
            count++;
        }
        if (usernameUpdate.getText().isEmpty()) {
            count++;
        }
        if (passwordUpdate.getText().isEmpty()) {
            count++;
        }
        if ((phoneUpdate.getText().isEmpty())) {
            count++;
        }
        if (emailUpdate.getText().isEmpty()) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        AdminToken = args[0];
        System.out.println("main called");
        System.setProperty("java.security.policy", "file:allowall.policy");

        try {
            service = (Service) Naming.lookup("//localhost/AlarmService");

            //service.returnAlarmDataFromApi();
        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    public void getUserData() throws Exception {
        StringBuffer response = service.getUserDataFromApi();

        JSONArray jsonArray = new JSONArray(response.toString());

        setUserData(jsonArray);

    }

    public void setUserData(JSONArray jsonArray) throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel(); //Accessing the Table in the UI.
        model.setRowCount(0);
        Object rowData[] = new Object[6]; //Initial step to add rows to the Table.

        for (int count = 0; count < jsonArray.length(); count++) {
            JSONObject jsonObject = jsonArray.getJSONObject(count);

            rowData[0] = jsonObject.getInt("id");
            rowData[1] = jsonObject.getString("username");
            rowData[2] = jsonObject.getString("phone");
            rowData[3] = jsonObject.getString("email");
            rowData[4] = jsonObject.getString("role");
            if (jsonObject.getBoolean("active") == true) {
                rowData[5] = "Active";
            } else {
                rowData[5] = "Inactive";
            }

            model.addRow(rowData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbRoleInsert;
    private javax.swing.JComboBox<String> cmbRoleUpdate;
    private javax.swing.JComboBox<String> cmbStatusInsert;
    private javax.swing.JComboBox<String> cmbStatusUpdate;
    private javax.swing.JTextField emailInsert;
    private javax.swing.JTextField emailUpdate;
    private javax.swing.JLabel errorDelete;
    private javax.swing.JLabel errorInsert;
    private javax.swing.JLabel errorUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblresponse;
    private javax.swing.JPasswordField passwordInsert;
    private javax.swing.JPasswordField passwordUpdate;
    private javax.swing.JTextField phoneInsert;
    private javax.swing.JTextField phoneUpdate;
    private javax.swing.JTextField userIdUpdate;
    private javax.swing.JTextField usernameInsert;
    private javax.swing.JTextField usernameUpdate;
    // End of variables declaration//GEN-END:variables
}