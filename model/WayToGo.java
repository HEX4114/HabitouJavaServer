/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author François
 */
public abstract class WayToGo {
    
    private Double latitude;
 
    private Double longitude;
    
    private String name;
    
    private Double time;
    
    private Double distance;
    
    public WayToGo(String name,Double lat, Double lon, Double time, Double distance){
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
        this.time = time;
        this.distance = distance;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
    
    
}
