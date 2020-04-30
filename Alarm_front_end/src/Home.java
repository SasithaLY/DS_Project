
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ishan
 */
public class Home extends javax.swing.JFrame {

    Timer timer = new Timer(); //Timer initialized in order to keep the program updated for every 5 seconds.
    public static Service service = null; //Reference to the RMI Server.
    public static String AdminToken = null; //AdminToken declaration.

    public Home() {
        initComponents();
        updateError.setVisible(false);
        errorInsert.setVisible(false);
        errorDelete.setVisible(false);
        this.setTitle("Admin Dashboard");
        try {
            getAlarmData();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date(); //This is to inform the user the application is updated in this particular time.
                    getAlarmData(); //This is called to get data from the RMI Server.
                    statusDisplayHome.setText("Refreshed! " + formatter.format(date));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0, 30000);

        jTable8.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                int co2 = (int) table.getModel().getValueAt(row, 5);
                int smoke = (int) table.getModel().getValueAt(row, 4);
                String status = (String) table.getModel().getValueAt(row, 1);

                if (smoke > 5 || co2 > 5) {

                    setBackground(new Color(255, 191, 191));
                    setForeground(Color.BLACK);
                } else {
                    setBackground(new Color(191, 255, 191));
                    setForeground(table.getForeground());
                }

                if (status == "Inactive") {
                    setBackground(new Color(226, 226, 226));
                    setForeground(Color.BLACK);
                }

                return c;
            }
        });
    }

    public void getAlarmData() throws Exception {

        boolean critical = false; //Used to take the decision whether to display the critical data information on display.

        StringBuffer response = service.returnAlarmDataFromApi();

        ArrayList<Alarm> alarm = new ArrayList<>();  //ArrayList initialization.
        alarm = getList(response.toString()); //Get all the Alarm details by calling getList method in to an ArrayList.

        DefaultTableModel model = (DefaultTableModel) jTable8.getModel(); //Accessing the Table in the UI.
        model.setRowCount(0);
        Object rowData[] = new Object[6]; //Initial step to add rows to the Table.

        ArrayList<String> criticalAlarmIds = new ArrayList<String>(); //This stores AlarmIDs which has a critical situation.

        //Here the JSON Array data will be added to the JTable within a for loop.
        for (int i = 0; i < alarm.size(); i++) {
            rowData[0] = alarm.get(i).alarmId;
            if (alarm.get(i).status == 1) {
                rowData[1] = "Active";
            } else {
                rowData[1] = "Inactive";
            }

            rowData[2] = alarm.get(i).floorNumber;
            rowData[3] = alarm.get(i).roomNumber;
            rowData[4] = alarm.get(i).smokeLevel;
            rowData[5] = alarm.get(i).co2Level;

            if (alarm.get(i).smokeLevel > 5 || alarm.get(i).co2Level > 5) {
                criticalAlarmIds.add(String.valueOf(alarm.get(i).alarmId));
                critical = true;
            } //Checks if there're alarms which are in critical situations and add those to the criticalAlarmIds ArrayList.

            if (critical == true) {
                alertDisplayHome.setText("Alert! Alarm ID " + criticalAlarmIds + " is/are in Critical Condition!");
            } else {

                alertDisplayHome.setText("");  //Set the alert to none if there's not alarm in critical condition.
            }

            model.addRow(rowData); //Add rows to the JTable.
        }

    }

//This is used to get the JSON response and derive the JSON array in to separated pieces and add them to the ArrayList.
    public ArrayList<Alarm> getList(String json) {

        ArrayList<Alarm> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json); //Initialized JsonArray to get the JSONArray response.
            for (int count = 0; count < jsonArray.length(); count++) {
                Alarm alarmObject = new Alarm(); //Alarm Object initialzation.
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                alarmObject.setAlarmId(jsonObject.getInt("sensorId")); //Storing the data in to an object.
                alarmObject.setFloorNumber(jsonObject.getInt("floorNo"));
                alarmObject.setRoomNumber(jsonObject.getInt("roomNo"));
                alarmObject.setSmokeLevel(jsonObject.getInt("smoke"));
                alarmObject.setCo2Level(jsonObject.getInt("co2"));
                alarmObject.setStatus(jsonObject.getInt("active"));
                //Taking data from particular Key.

                arrayList.add(alarmObject); //Object added to the ArrayList
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        submitBtn = new java.awt.Button();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        roomNumberRegister = new java.awt.TextField();
        floorNumberRegister = new java.awt.TextField();
        label8 = new java.awt.Label();
        cmbInsertStatus = new javax.swing.JComboBox<>();
        errorInsert = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        updateBtn = new java.awt.Button();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        idUpdate = new java.awt.TextField();
        roomNumberUpdate = new java.awt.TextField();
        floorNumberUpdate = new java.awt.TextField();
        label9 = new java.awt.Label();
        cmbUpdateStatus = new javax.swing.JComboBox<>();
        updateError = new javax.swing.JLabel();
        errorDelete = new javax.swing.JLabel();
        btnDelete = new java.awt.Button();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        refreshBtn = new java.awt.Button();
        statusDisplayHome = new java.awt.Label();
        alertDisplayHome = new java.awt.Label();
        btnManageUsers = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Register a new Alarm Sensor");

        submitBtn.setLabel("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        label3.setText("Room Number:");

        label4.setText("Floor Number:");

        label8.setText("Status:");

        cmbInsertStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbInsertStatus.setSelectedIndex(1);

        errorInsert.setForeground(new java.awt.Color(255, 0, 0));
        errorInsert.setText("Error! Check Inputs.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(errorInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomNumberRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorNumberRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbInsertStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(errorInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomNumberRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(floorNumberRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInsertStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Edit an Alarm Sensor");

        updateBtn.setLabel("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        label5.setText("Room Number:");

        label6.setText("Floor Number:");

        label7.setText("ID:");

        idUpdate.setEditable(false);
        idUpdate.setEnabled(false);

        label9.setText("Status:");

        cmbUpdateStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inactive", "Active" }));
        cmbUpdateStatus.setSelectedIndex(1);

        updateError.setForeground(new java.awt.Color(255, 0, 0));
        updateError.setText("Error! Check Inputs.");

        errorDelete.setForeground(new java.awt.Color(255, 0, 0));
        errorDelete.setText("Please Select a Sensor");

        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(errorDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roomNumberUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorNumberUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateError))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roomNumberUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(floorNumberUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorDelete, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnDelete.getAccessibleContext().setAccessibleName("btnDelete");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor Id", "Status", "Floor Number", "Room Number", "Smoke Level", "CO2 Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(5).setHeaderValue("CO2 Level");
        }

        refreshBtn.setLabel("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        alertDisplayHome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        alertDisplayHome.setForeground(new java.awt.Color(255, 0, 0));

        btnManageUsers.setLabel("Manage Users");
        btnManageUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(alertDisplayHome, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                            .addComponent(statusDisplayHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnManageUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnManageUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(statusDisplayHome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alertDisplayHome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //This is to register an Alarm. Accessible only within Admin.
    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (validateInsertInputs()) {
            errorInsert.setVisible(false);

            String roomNumber = roomNumberRegister.getText(); //Getting the roomNumber from  the admin.
            String floorNumber = floorNumberRegister.getText(); //Getting the floorNumber from  the admin.   
            int status = cmbInsertStatus.getSelectedIndex(); //Getting the status from  the admin.

            String jsonString = ""; //Creating a method to make a JSONObject.
            try {
                jsonString = new JSONObject()
                        .put("floorNo", floorNumber)
                        .put("roomNo", roomNumber)
                        .put("active", status).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String[] response = new String[3];

            try {
                response = service.addAlarmSensor(jsonString, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();//This is used to identify Admin that a particular action happened on this time.

            if (response[0].equals("200")) {
                try {
                    service.getResponseFromApi();
                    getAlarmData();
                } catch (RemoteException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                statusDisplayHome.setText("Sensor Successfully Registered! " + formatter.format(date));  //Getting the POST response and display success.
                roomNumberRegister.setText(""); //Setting blanks to the inputs.
                floorNumberRegister.setText(""); //Setting blanks to the inputs.
                cmbInsertStatus.setSelectedIndex(1); //set the default status

                try {
                    this.getAlarmData();
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (!response[0].equals("200")) {
                statusDisplayHome.setText("Error in Sensor Register! " + formatter.format(date)); //Show this if JSON responded an error.
            }
        } else {
            errorInsert.setVisible(true);
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    //This method is to perform the Alarm update task.
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (validateUpdateInputs()) {
            updateError.setVisible(false);

            String id = idUpdate.getText().trim(); //Getting the id from  the admin.
            String roomNumber = roomNumberUpdate.getText().trim(); //Getting the roomNumber from  the admin.
            String floorNumber = floorNumberUpdate.getText().trim(); //Getting the floorNumber from  the admin.
            //String status = statusEdit.getText().trim(); //Getting the status from  the admin.
            int status = cmbUpdateStatus.getSelectedIndex();

            String jsonString = ""; //As mentioned earlier this is used to create a JSON Object.
            try {
                jsonString = new JSONObject()
                        .put("sensorId", id)
                        .put("floorNo", floorNumber)
                        .put("roomNo", roomNumber)
                        .put("active", status).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            String[] response = new String[3];

            try {
                response = service.updateAlarmSensor(jsonString, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response[0].equals("200")) {
                statusDisplayHome.setText("Sensor Successfully Updated! " + formatter.format(date)); //Display Success Message.
                idUpdate.setText(""); //Clean the inputs
                roomNumberUpdate.setText(""); //Clean the inputs
                floorNumberUpdate.setText(""); //Clean the inputs
                cmbUpdateStatus.setSelectedIndex(1); //Clean the inputs

                try {
                    service.getResponseFromApi();
                    getAlarmData();
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (!response[0].equals("200")) {
                statusDisplayHome.setText("Error in Sensor Update! " + formatter.format(date));
            }
        } else {
            updateError.setVisible(true);
        }


    }//GEN-LAST:event_updateBtnActionPerformed

    public boolean validateUpdateInputs() {

        int count = 0;

        if (idUpdate.getText().toString().isEmpty()) {
            count++;
        }
        if ((roomNumberUpdate.getText().toString().isEmpty()) || (!roomNumberUpdate.getText().matches("[0-9]+"))) {
            count++;
        }
        if ((floorNumberUpdate.getText().toString().isEmpty()) || (!floorNumberUpdate.getText().matches("[0-9]+"))) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateInsertInputs() {

        int count = 0;

        if ((roomNumberRegister.getText().toString().isEmpty()) || (!roomNumberRegister.getText().matches("[0-9]+"))) {
            count++;
        }
        if ((floorNumberRegister.getText().toString().isEmpty()) || (!floorNumberRegister.getText().matches("[0-9]+"))) {
            count++;
        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    //This method is to manually refresh the feed. Other than the 30 seconds automatic refresh.
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
            model.setRowCount(0); //Do clean the JTable
            service.getResponseFromApi();
            getAlarmData(); //Getting the Alarm details to the JTable.
            statusDisplayHome.setText("Refreshed! " + formatter.format(date));
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked

        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();

        int rowIndex = jTable8.getSelectedRow();

        idUpdate.setText(model.getValueAt(rowIndex, 0).toString());
        if (model.getValueAt(rowIndex, 1).toString() == "Active") {
            cmbUpdateStatus.setSelectedIndex(1);
        } else {
            cmbUpdateStatus.setSelectedIndex(0);
        }
        //cmbUpdateStatus.setSelectedIndex(Integer.parseInt(model.getValueAt(rowIndex, 1).toString()));
        floorNumberUpdate.setText(model.getValueAt(rowIndex, 2).toString());
        roomNumberUpdate.setText(model.getValueAt(rowIndex, 3).toString());


    }//GEN-LAST:event_jTable8MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = idUpdate.getText().toString();

        if (!id.isEmpty()) {
            errorDelete.setVisible(false);
            String[] response = new String[3];

            try {
                response = service.deleteAlarmSensor(id, AdminToken);
            } catch (RemoteException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();//This is used to identify Admin that a particular action happened on this time.

            if (response[0].equals("200")) {
                try {
                    service.getResponseFromApi();
                    getAlarmData();
                } catch (RemoteException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
                statusDisplayHome.setText("Sensor Deleted Successfully! " + formatter.format(date));  //Getting the POST response and display success.
                idUpdate.setText("");
                roomNumberUpdate.setText(""); //Setting blanks to the inputs.
                floorNumberUpdate.setText(""); //Setting blanks to the inputs.
                cmbUpdateStatus.setSelectedIndex(1); //set the default status

                try {
                    this.getAlarmData();
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (!response[0].equals("200")) {
                statusDisplayHome.setText("Error in Sensor Register! " + formatter.format(date)); //Show this if JSON responded an error.
            }
        }else{
            errorDelete.setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnManageUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUsersActionPerformed
        // TODO add your handling code here:
        String srgs[] = new String[2];
        srgs[0] = AdminToken;
        User.main(srgs);
    }//GEN-LAST:event_btnManageUsersActionPerformed

    public static void main(String args[]) {

        AdminToken = args[0];
        System.out.println("main called");
        System.setProperty("java.security.policy", "file:allowall.policy");

        try {
            service = (Service) Naming.lookup("//localhost/AlarmService");

            service.returnAlarmDataFromApi();

        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label alertDisplayHome;
    private java.awt.Button btnDelete;
    private java.awt.Button btnManageUsers;
    private javax.swing.JComboBox<String> cmbInsertStatus;
    private javax.swing.JComboBox<String> cmbUpdateStatus;
    private javax.swing.JLabel errorDelete;
    private javax.swing.JLabel errorInsert;
    private java.awt.TextField floorNumberRegister;
    private java.awt.TextField floorNumberUpdate;
    private java.awt.TextField idUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable8;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.Button refreshBtn;
    private java.awt.TextField roomNumberRegister;
    private java.awt.TextField roomNumberUpdate;
    private java.awt.Label statusDisplayHome;
    private java.awt.Button submitBtn;
    private java.awt.Button updateBtn;
    private javax.swing.JLabel updateError;
    // End of variables declaration//GEN-END:variables
}
