/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.squareup.okhttp.OkHttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
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

    public Square() {
    }

    public Square(Double latitude, Double longitude, Atm nearestAtm, Supermarket nearestSupermarket, Adress adress) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nearestAtm = nearestAtm;
        this.nearestSupermarket = nearestSupermarket;
        this.adress = adress;
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
    
    public void loadAdressInformations(Adress adressLocation) {
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
        port = "5002";
        requestResponse = getAdressRequest(port, origin, destination);
        distance = getInfosFromJsonResponse(requestResponse, "distance") / 2;
        time = getInfosFromJsonResponse(requestResponse, "duration") / 2;
        walk.setDistance(distance);
        walk.setTime(time);
        
        if(time > 3*60) {
            // Drive
            port = "5003";
            requestResponse = getAdressRequest(port, origin, destination);
            distance = getInfosFromJsonResponse(requestResponse, "distance") / 2;
            time = getInfosFromJsonResponse(requestResponse, "duration") / 2;
        }
        drive.setDistance(distance);
        drive.setTime(time);
        
        this.adress.setDrive(drive);
        this.adress.setWalk(walk);
    }
    
    private String getAdressRequest(String port, String origin, String destination) {
        String urlRequest = "http://176.135.252.82:" + port + "/trip/v1/walking/" + origin + ";" + destination;
        Request request = new Request.Builder().url(urlRequest).build();
        //OkHttpClient client = new OkHttpClient();
        String result = "";

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println("Error : IOException in getAdressRequest");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.toString());
        }
        
        return result;
    }
    
    public double getInfosFromJsonResponse(String responseServer, String info)
    {
        String responsePart = "";
        String[] responseParsed = responseServer.split(",");
        for(int i = 0; i < responseParsed.length; i++)
        {
            if(responseParsed[i].contains("\""+info+"\" :"))
            {
                responsePart = responseParsed[i].split(" : ")[1];
                if(info == "distance") {
                    responsePart = responsePart.substring(0, responsePart.length()-2);
                }
                return Double.parseDouble(responsePart);
            }
        }
        return 0.0;
    }
    
    public Double getAtmScore(Criterions c)
    {
        if(c.getAtm().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Atm");
    }
    
    public Double getSupermarketScore(Criterions c)
    {
        if(c.getSupermarket().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Supermarket");
    }
    
    public Double getAdressScore(Criterions c)
    {
        if(c.getAdress().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Adress");
    }
    
    @Override
    public String toString() {
        return latitude + " " + longitude + " " + nearestAtm.toString() + " "+ nearestSupermarket.toString();
    }
    
}
