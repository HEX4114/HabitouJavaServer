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
public class Supermarket extends Place {
    
    private String name;

    public Supermarket(Double latitude, Double longitude, Double distance) {
        super(latitude, longitude, distance);
    }

    public Supermarket() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Supermarket: " + super.toString();
    }
    
    
    
}
