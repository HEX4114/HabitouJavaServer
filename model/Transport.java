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



public class Transport extends Place {
    
    private TypeTransport typeTransport;

    public Transport(Double latitude, Double longitude, Double distancef, Double distancec) {
        super(latitude, longitude, distancef, distancec);
    }

    public Transport() {
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }
    
    @Override
    public String toString() {
        return "Transport: " + super.toString();
    }
    
    
    
}
