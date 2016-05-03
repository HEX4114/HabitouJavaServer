/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 *
 * @author François
 */
public class Square {
    
    OkHttpClient client = new OkHttpClient();
    
    private String id;
 
    private Double latitude;
 
    private Double longitude;
    
    private Atm nearestAtm;
    
    private Supermarket nearestSupermarket;
    
    private Adress adress;

    private Doctor nearestDoctor;
    
    private Kindergarten nearestKindergarten;
    
    private Pollution pollution;

    public Square() {
    }


    public Square(Double latitude, Double longitude, Atm nearestAtm, Supermarket nearestSupermarket, Adress adress, Doctor nearestDoctor, Kindergarten nearestKindergarten, Pollution pollution) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.nearestAtm = nearestAtm;
        this.nearestSupermarket = nearestSupermarket;
        this.adress = adress;
        this.nearestDoctor = nearestDoctor;
        this.nearestKindergarten = nearestKindergarten;
        this.pollution = pollution;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Atm getNearestAtm() {
        return nearestAtm;
    }

    public void setNearestAtm(Atm nearestAtm) {
        this.nearestAtm = nearestAtm;
    }

    public Supermarket getNearestSupermarket() {
        return nearestSupermarket;
    }

    public void setNearestSupermarket(Supermarket nearestSupermarket) {
        this.nearestSupermarket = nearestSupermarket;
    }
    
    public Adress getAdress() {
        return this.adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
    
    public void loadAdressInformations(Adress adressLocation, Boolean onCar) {
        String port;
        String origin;
        String destination;
        String requestResponse;
        double distance;
        double time;
        String name;
        this.adress = adressLocation;
        
        Walk walk = this.adress.getWalk();
        Drive drive = this.adress.getDrive();
        
        double lat = walk.getLatitude();
        double lng = walk.getLongitude();
        
        origin = this.longitude + "," + this.latitude;
        destination = lng + "," + lat;
        
        // Walk
        port = "5001";
        requestResponse = getAdressRequest(port, origin, destination);
        distance = getInfosFromJsonResponse(requestResponse, "distance");
        time = getInfosFromJsonResponse(requestResponse, "duration");
        walk.setDistance(distance);
        walk.setTime(time);
        
        if(time > 3*60 && onCar) {
            // Drive
            port = "5000";
            requestResponse = getAdressRequest(port, origin, destination);
            distance = getInfosFromJsonResponse(requestResponse, "distance");
            time = getInfosFromJsonResponse(requestResponse, "duration");
        }
        drive.setDistance(distance);
        drive.setTime(time);
        
        this.adress.setDrive(drive);
        this.adress.setWalk(walk);
    }
    
    private String getAdressRequest(String port, String origin, String destination) {
        String urlRequest = "http://192.168.1.2:" + port + "/route/v1/walking/" + origin + ";" + destination;
        Request request = new Request.Builder().url(urlRequest).build();
        //OkHttpClient client = new OkHttpClient();
        String result = "";

        System.out.println("eeeeeeeeeeet Requete Adrien !)");
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println("Error : IOException in getAdressRequest (requete à adrien)");
        }
        
        return result;
    }
    
    public double getInfosFromJsonResponse(String responseServer, String info)
    {
        String responsePart = "";
        String[] responseParsed = responseServer.split(",");
        for(int i = 0; i < responseParsed.length; i++)
        {
            if(responseParsed[i].contains("\""+info+"\":"))
            {
                responsePart = responseParsed[i].split("\""+info+"\":")[1];
                if(info == "distance") {
                    responsePart = responsePart.substring(0, responsePart.length()-2);
                }
                return Double.parseDouble(responsePart);
            }
        }
        return 0.0;
    }

    public Doctor getNearestDoctor() {
        return nearestDoctor;
    }

    public void setNearestDoctor(Doctor nearestDoctor) {
        this.nearestDoctor = nearestDoctor;
    }

    public Kindergarten getNearestKindergarten() {
        return nearestKindergarten;
    }

    public void setNearestKindergarten(Kindergarten nearestKindergarten) {
        this.nearestKindergarten = nearestKindergarten;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    
    public Double getAtmScore(SquareCriteria c)
    {
        if(c.getAtm().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Atm");
    }
    
    public Double getSupermarketScore(SquareCriteria c)
    {
        if(c.getSupermarket().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Supermarket");
    }

    public Double getDoctorScore(SquareCriteria c)
    {
        if(c.getDoctor().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Doctor");
    }
    
    public Double getAdressScore(SquareCriteria c)
    {
        if(c.getAdress().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Adress");
    }
    
    public Double getKindergartenScore(SquareCriteria c)
    {
        if(c.getKindergarten().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Kindergarten");
    }
    
    public Double getPollutionScorre(SquareCriteria c){
        if(c.getPollution().equals("null"))
        {
            return -1.0;
        }
        return c.getPollutionScore(this);
    }
    
    @Override
    public String toString() {
        return latitude + " " + longitude + " " + nearestAtm.toString() + " "+ nearestSupermarket.toString()+ " " + nearestDoctor.toString() + " "+ nearestKindergarten.toString();
    }
    
}
