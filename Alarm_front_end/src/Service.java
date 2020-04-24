/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.json.JSONObject;

public interface Service extends Remote {

    // References to the implementations
    public StringBuffer returnAlarmDataFromApi() throws RemoteException;

    public String[] loginAdmin(String userName, String password) throws RemoteException;

    public String[] addAlarmSensor(String jsonDetails, String token) throws RemoteException;
    
    public String[] updateAlarmSensor(String jsonDetails, String token) throws RemoteException;
    
    public void getResponseFromApi()throws RemoteException;
}
