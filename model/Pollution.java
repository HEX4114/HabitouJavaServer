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
public class Pollution {
    
    private String name;
    
    private Double rate;
    
    private Double latitude;
    
    private Double longitude;
    
    private Double distance;
    
    public Pollution(){
        
    }
    
    public Pollution(String name, Double rate, Double latitude, Double longitude, Double distance){
        this.name = name;
        this.rate = rate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
}
