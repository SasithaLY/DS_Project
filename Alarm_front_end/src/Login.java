
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author ishan
 */
public class Login extends javax.swing.JFrame {

    Timer timer = new Timer(); //Explained in Home.java, Code segments are the same.
    public static Service service = null;

    public Login() throws Exception {
        initComponents();
//        getAlarmData();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    getAlarmData();
                    System.out.println("called");
                    labelLogin.setText("Refreshed! " + formatter.format(date));
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 5000);
    }

    public void getAlarmData() throws Exception {//Explained in Home.java. Code segments are the same.

        boolean critical = false;

        StringBuffer response = service.returnAlarmDataFromApi();

        ArrayList<Alarm> alarm = new ArrayList<>();
        alarm = getList(response.toString());

        DefaultTableModel model = (DefaultTableModel) jTableLogin.getModel();
        model.setRowCount(0);
        Object rowData[] = new Object[6];

        ArrayList<String> criticalAlarmIds = new ArrayList<String>();

        for (int i = 0; i < alarm.size(); i++) {
            rowData[0] = alarm.get(i).alarmId;
            rowData[1] = alarm.get(i).status;
            rowData[2] = alarm.get(i).floorNumber;
            rowData[3] = alarm.get(i).roomNumber;
            rowData[4] = alarm.get(i).smokeLevel;
            rowData[5] = alarm.get(i).co2Level;

            if (alarm.get(i).smokeLevel > 5 || alarm.get(i).co2Level > 5) {
                criticalAlarmIds.add(String.valueOf(alarm.get(i).alarmId));
                critical = true;
            }

            if (critical == true) {
                alertDisplayLogin.setText("Alert! Alarm ID " + criticalAlarmIds + " is/are in Critical Condition!");
            } else {

                alertDisplayLogin.setText("");
            }

            model.addRow(rowData);
        }

    }

    public ArrayList<Alarm> getList(String json) {

        ArrayList<Alarm> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int count = 0; count < jsonArray.length(); count++) {
                Alarm alarmObject = new Alarm();
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                alarmObject.setAlarmId(jsonObject.getInt("sensorId"));
                alarmObject.setFloorNumber(jsonObject.getInt("floorNo"));
                alarmObject.setRoomNumber(jsonObject.getInt("roomNo"));
                alarmObject.setSmokeLevel(jsonObject.getInt("smoke"));
                alarmObject.setCo2Level(jsonObject.getInt("co2"));
                alarmObject.setStatus(jsonObject.getInt("active"));

                arrayList.add(alarmObject);
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
        usernameInput = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        passwordInput = new javax.swing.JPasswordField();
        submit = new java.awt.Button();
        statusDisplayLogin = new java.awt.Label();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableLogin = new javax.swing.JTable();
        labelLogin = new java.awt.Label();
        alertDisplayLogin = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        label1.setForeground(new java.awt.Color(0, 0, 0));
        label1.setName(""); // NOI18N
        label1.setText("Username:");

        label2.setForeground(new java.awt.Color(0, 0, 0));
        label2.setText("Password:");

        submit.setBackground(new java.awt.Color(255, 196, 9));
        submit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        submit.setLabel("Login");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        statusDisplayLogin.setBackground(new java.awt.Color(255, 255, 255));
        statusDisplayLogin.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        statusDisplayLogin.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(statusDisplayLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusDisplayLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordInput)
                        .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        submit.getAccessibleContext().setAccessibleName("");

        jTableLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alarm Id", "Status", "Floor Number", "Room Number", "Smoke Level", "CO2 Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableLogin);

        alertDisplayLogin.setAlignment(java.awt.Label.RIGHT);
        alertDisplayLogin.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        alertDisplayLogin.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 193, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alertDisplayLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alertDisplayLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(56, 128, 255));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Sensors");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        String userName = usernameInput.getText().trim();
        String password = passwordInput.getText().trim();
        String response[] = null;

        statusDisplayLogin.setText("");

        try {
            response = service.loginAdmin(userName, password);

            if (response[0].equals("Authorized!")) {
                this.setVisible(false);
                timer.cancel();
                String srgs[] = new String[2];
                srgs[0] = response[1];
                System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" + srgs[0]);
//                new Home().setVisible(true);
                Home.main(srgs);
            } else if (response[0].equals("Unauthorized!")){
                statusDisplayLogin.setText("Unauthorized!");
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_submitActionPerformed

    public static void main(String args[]) {

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
                try {
                    new Login().setVisible(true);

                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label alertDisplayLogin;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTableLogin;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label labelLogin;
    private javax.swing.JPasswordField passwordInput;
    private java.awt.Label statusDisplayLogin;
    private java.awt.Button submit;
    private java.awt.TextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
