package com.example.demo.UI;

public class temp {
//
//
//    Sensor sensor = new Sensor();
//
//    URL obj = new URL("http://localhost:8080/SensorController/getSensorDetailsAccordingToID/" + sensorId);
//
//    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json; utf-8");
//        con.setRequestProperty("Accept", "application/json");
//    int responseCode = con.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) { // success
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        if ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        String result = response.toString();
//
//        JSONObject jobj = new JSONObject(result);
//        sensor.setCo2Level(jobj.getInt("co2Level"));
//        sensor.setFloorNumber(jobj.getInt("floorNumber"));
//        sensor.setRoomNumber(jobj.getInt("roomNumber"));
//        sensor.setSensorId(String.valueOf(jobj.getString("sensorId")));
//        sensor.setSmokeLevel(jobj.getInt("smokeLevel"));
//        sensor.setStatus(String.valueOf(jobj.getString("status")));
//
//        System.out.println("GG "+sensor.getStatus());
//
//
//    } else {
//        System.out.println("Error Occured");
//    }
//        return sensor;
//
//
//
//        ===============================
//
//
//
//    @Override
//    public boolean addSensor(Sensor s) throws Exception {
//        JSONObject sensorDetails = new JSONObject();
//        sensorDetails.put("sensorId", s.getSensorId());
//        sensorDetails.put("floorNumber", s.getFloorNumber());
//        sensorDetails.put("roomNumber", s.getRoomNumber());
//        sensorDetails.put("smokeLevel", s.getSmokeLevel());
//        sensorDetails.put("status", s.getStatus());
//        JSONObject sensorObject = new JSONObject();
//        sensorObject.put("Sensor", sensorDetails);
//        URL url = new URL("http://localhost:8080/SensorController/addSensor");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json; utf-8");
//        con.setRequestProperty("Accept", "application/json");
//        con.setDoOutput(true);
//        String jsonInputString = sensorDetails.toString();
//        try (OutputStream os = con.getOutputStream()) {
//            byte[] input = jsonInputString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            System.out.println(response.toString());
//        }
//        con.disconnect();
//        return true;
//    }
}
