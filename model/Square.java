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
public class Square {
    
    private String id;
 
    private Integer latitude;
 
    private Integer longitude;
    
    private Atm nearestAtm;
    
    private Supermarket nearestSupermarket;
    
    private Transport nearestTransport;
    
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
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

    public Transport getNearestTransport() {
        return nearestTransport;
    }

    public void setNearestTransport(Transport nearestTransport) {
        this.nearestTransport = nearestTransport;
    }
    
}
