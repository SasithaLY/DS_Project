
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ishan
 */
public class Server extends UnicastRemoteObject implements Service {

    public static String TOKEN_BEARER; // Stores the admin token for future purposes.
    StringBuffer response = new StringBuffer(); // StringBuffer initialization. Stores the response from API Server.
    StringBuffer responseUsers = new StringBuffer(); 
    public static ArrayList<String> criticalAlarmIds = new ArrayList<String>();
    public static ArrayList<String> criticalAlarmIdsTemp = new ArrayList<String>();

    public Server() throws RemoteException {
        super();
        Timer timer = new Timer(); // Timer initialized in order to keep the program updated for every 15 seconds.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    boolean critical = false;

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    System.out.println("from server main");
                    getResponseFromApi(); // This method is called every 15 seconds to take response from API server.

                    ArrayList<Alarm> alarm = new ArrayList<>(); // ArrayList initialization.
                    alarm = getList(response.toString()); // Get all the Alarm details by calling getList method in to
                    // an ArrayList.

                    for (int i = 0; i < alarm.size(); i++) {

                        if (alarm.get(i).smokeLevel > 5 || alarm.get(i).co2Level > 5) {
                            if (!criticalAlarmIds.contains(String.valueOf(alarm.get(i).alarmId))) {
                                criticalAlarmIds.add(String.valueOf(alarm.get(i).alarmId));
                                criticalAlarmIdsTemp = criticalAlarmIds;
                                critical = true;
                            }

                        } // Checks if there're alarms which are in critical situations and add those to

                    }

                    //send sms and email if the alarms are in critical condition
                    if ((critical == true) && (criticalAlarmIds == criticalAlarmIdsTemp)) {
                        System.out.println("SMS Sent");
                        System.out.println("Email Sent");
                        sendSMS("Alert! Alarm ID " + criticalAlarmIds.toString() + " is/are in Critical Condition!");
                        sendEmail("Alert! Alarm ID " + criticalAlarmIds.toString() + " is/are in Critical Condition!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 0, 15000); // Timer set to 15 seconds

        Timer timer2 = new Timer();
        TimerTask hourlyJob = new TimerTask() {
            @Override
            public void run() {
                criticalAlarmIdsTemp.clear();
                criticalAlarmIds.clear();
            }
        };

        // run the resetting critical ids every hour
        timer2.schedule(hourlyJob, 0l, 1000 * 60 * 60);
    }

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "file:allowall.policy"); // Security Permissions.

        try {

            Server svr = new Server(); // Creating the server.

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("AlarmService", svr); // Bind service to the Registry.

            System.out.println("Service started....");
        } catch (RemoteException re) { // Catches exceptions.
            System.err.println(re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.err.println(abe.getMessage());
        }

    }

    // This method is to get Alarm data from the API Server. This is called every 15
    // seconds.
    @Override
    public void getResponseFromApi() {
        String url = "http://localhost:8080/api/getSensors"; // The GET request URL.

        try {
            URL obj = new URL(url); // URL initialization.
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // HttpURLConnection opens a connection.

            con.setRequestMethod("GET"); // sets the Method
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode(); // Retrieves the status CODE such as, 200 or 404.

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // Initialized the
            // BufferReader.
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                this.response = null;
                this.response = new StringBuffer();
                this.response.append(inputLine); // Append the string to the response.
            }
            in.close(); // Buffer is closed.

        } catch (Exception e) {
            System.out.println("Make sure you've run the API Server!");
        }
    }

    // This method does return gathered response from API Server, to the RMI Client.
    @Override
    public StringBuffer returnAlarmDataFromApi() throws RemoteException {

        return response; // Return data.
    }

    // This method handle the Login of an Admin in a Secure Manner.
    @Override
    public String[] loginAdmin(String userName, String password) throws RemoteException {

        String jsonString = ""; // Creating the JSON Object to pass in the future.
        try {
            jsonString = new JSONObject().put("username", userName).put("password", password).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8080/api/auth/signin") // Http POST request
                    // to the API
                    // Server.
                    .header("Content-Type", "application/json").body(jsonString) // Passing the JSON data to the API
                    // Server.
                    .asString();

            JSONObject myResponse = new JSONObject(response.getBody().toString()); // Creating an JAONObject to collect
            // response.

            if (response.getStatus() == 200) { // Investigate the response status.
                Server.TOKEN_BEARER = myResponse.getString("accessToken"); // Retreiving the Admin Token.

                String[] values = new String[2]; // Creation of String array to be sent to the RMI Client.
                values[0] = "Authorized!";
                values[1] = myResponse.getString("accessToken");
                return values;
            } else {
                String[] values = new String[2];
                values[0] = "Unauthorized!";
                return values; // Returns "Unauthorized!".
            }
        } catch (UnirestException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    // This method is to Register a new alarm.
    @Override
    public String[] addAlarmSensor(String jsonDetails, String token) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.post("http://localhost:8080/api/admin/addSensor") // POST
                    // Request.
                    .header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
                    .body(jsonDetails) // Passing the JSON data to the API Server.
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    // This method habdle thi Update process of an Alarm.
    @Override
    public String[] updateAlarmSensor(String jsonDetails, String token) throws RemoteException {

        String[] values = new String[2];
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.put("http://localhost:8080/api/admin/updateSensor") // Perform the
                    // PUT Action.
                    .header("Authorization", "Bearer " + token) // Passing the Admin Token to get the permission to the
                    // update process.
                    .header("Content-Type", "application/json").body(jsonDetails) // Pass the created JSON Data to the
                    // PUT request.
                    .asString();

            if (response.getStatus() == 200) {
                values[0] = String.valueOf(response.getStatus());
            } else {
                values[0] = String.valueOf(response.getStatus());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return values;
    }

    // This method is to Send SMS in critical condition.
    @Override
    public String[] sendSMS(String message) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.post("http://localhost:8080/api/sendSMS") // POST Request.
                    .header("Content-Type", "application/json").body(message) // Passing the JSON data to the API
                    // Server.
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    // This method is to Send Email in critical condition.
    @Override
    public String[] sendEmail(String message) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.post("http://localhost:8080/api/sendEmail") // POST Request.
                    .header("Content-Type", "application/json").body(message) // Passing the JSON data to the API
                    // Server.
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    public ArrayList<Alarm> getList(String json) {

        ArrayList<Alarm> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json); // Initialized JsonArray to get the JSONArray response.
            for (int count = 0; count < jsonArray.length(); count++) {
                Alarm alarmObject = new Alarm(); // Alarm Object initialzation.
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                alarmObject.setAlarmId(jsonObject.getInt("sensorId")); // Storing the data in to an object.
                alarmObject.setFloorNumber(jsonObject.getInt("floorNo"));
                alarmObject.setRoomNumber(jsonObject.getInt("roomNo"));
                alarmObject.setSmokeLevel(jsonObject.getInt("smoke"));
                alarmObject.setCo2Level(jsonObject.getInt("co2"));
                alarmObject.setStatus(jsonObject.getInt("active"));
                // Taking data from particular Key.

                arrayList.add(alarmObject); // Object added to the ArrayList
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public String[] deleteAlarmSensor(String id, String token) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.delete("http://localhost:8080/api/admin/deleteSensor/"+id) // POST
                    // Request.
                    .header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    @Override
    public StringBuffer getUserDataFromApi() throws RemoteException {
        String url = "http://localhost:8080/api/admin/getAllUsers"; // The GET request URL.

        try {
            URL obj = new URL(url); // URL initialization.
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // HttpURLConnection opens a connection.

            con.setRequestMethod("GET"); // sets the Method
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Authorization", "Bearer " + TOKEN_BEARER);

            int responseCode = con.getResponseCode(); // Retrieves the status CODE such as, 200 or 404.

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // Initialized the
            // BufferReader.
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                this.responseUsers = null;
                this.responseUsers = new StringBuffer();
                this.responseUsers.append(inputLine); // Append the string to the response.
            }
            in.close(); // Buffer is closed.

        } catch (Exception e) {
            System.out.println("Make sure you've run the API Server!");
        }
        
        return responseUsers;
    }

    @Override
    public String[] addUser(String jsonDetails, String token) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.post("http://localhost:8080/api/auth/signup") // POST
                    // Request.
                    .header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
                    .body(jsonDetails) // Passing the JSON data to the API Server.
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    @Override
    public String[] deleteUser(String id, String token) throws RemoteException {
        String[] response = new String[1]; // Creation of String array to be sent to the RMI Client.
        try {

            Unirest.setTimeouts(0, 0); // Time out from UNIREST.
            HttpResponse<String> responseGet = Unirest.delete("http://localhost:8080/api/admin/deleteUser/"+id) // POST
                    // Request.
                    .header("Authorization", "Bearer " + token).header("Content-Type", "application/json")
                    .asString();

            if (responseGet.getStatus() == 200) { // Investigate the response status.
                response[0] = String.valueOf(responseGet.getStatus());
                getResponseFromApi();

            } else {
                response[0] = String.valueOf(responseGet.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response; // Return the response.
    }

    @Override
    public String[] updateUser(String jsonDetails, String token) throws RemoteException {
        String[] values = new String[2];
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.put("http://localhost:8080/api/admin/updateUser") // Perform the
                    // PUT Action.
                    .header("Authorization", "Bearer " + token) // Passing the Admin Token to get the permission to the
                    // update process.
                    .header("Content-Type", "application/json").body(jsonDetails) // Pass the created JSON Data to the
                    // PUT request.
                    .asString();

            if (response.getStatus() == 200) {
                values[0] = String.valueOf(response.getStatus());
            } else {
                values[0] = String.valueOf(response.getStatus());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return values;
    }

    

 

    
}
