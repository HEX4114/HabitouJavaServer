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
 
    private Double latitude;
 
    private Double longitude;
    
    private Atm nearestAtm;
    
    private Supermarket nearestSupermarket;
    
    private Doctor nearestDoctor;
    
    private Kindergarten nearestKindergarten;
    
    private Pollution pollution;

    public Square() {
    }

    public Square(Double latitude, Double longitude, Atm nearestAtm, Supermarket nearestSupermarket, Doctor nearestDoctor, Kindergarten nearestKindergarten, Pollution pollution) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nearestAtm = nearestAtm;
        this.nearestSupermarket = nearestSupermarket;
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
    
    public Double getKindergartenScore(SquareCriteria c)
    {
        if(c.getKindergarten().equals("null"))
        {
            return -1.0;
        }
        return c.getScore(this, "Kindergarten");
    }
    
    
    @Override
    public String toString() {
        return latitude + " " + longitude + " " + nearestAtm.toString() + " "+ nearestSupermarket.toString()+ " " + nearestDoctor.toString() + " "+ nearestKindergarten.toString();
    }
    
}
