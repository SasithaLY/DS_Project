/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.json.JSONObject;
import org.json.JSONArray;

public interface Service extends Remote {

    // References to the implementations
    public StringBuffer returnAlarmDataFromApi() throws RemoteException;

    public String[] loginAdmin(String userName, String password) throws RemoteException;

    public String[] addAlarmSensor(String jsonDetails, String token) throws RemoteException;
    
    public String[] updateAlarmSensor(String jsonDetails, String token) throws RemoteException;
    
    public String[] deleteAlarmSensor(String jsonDetails, String token) throws RemoteException;
    
    public void getResponseFromApi()throws RemoteException;

    public String[] sendSMS(String message) throws RemoteException;

    public String[] sendEmail(String message) throws RemoteException;
    
    public StringBuffer getUserDataFromApi() throws RemoteException;
    
    public String[] addUser(String jsonDetails, String token) throws RemoteException;
    
    public String[] deleteUser(String jsonDetails, String token) throws RemoteException;
    
    public String[] updateUser(String jsonDetails, String token) throws RemoteException;
}
