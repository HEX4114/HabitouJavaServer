/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author François
 */
public class Criterions {
    
    Boolean onCar;
    
    String atm;
    
    String supermarket;
    
    String transport;
    
    public Criterions(Boolean onCar, String atm, String supermarket, String transport) {
        
        this.onCar = onCar;
        this.atm = atm;
        this.supermarket = supermarket;
        this.transport = transport;
        
    }

    public Boolean isOnCar() {
        return onCar;
    }

    public void setOnCar(Boolean onCar) {
        this.onCar = onCar;
    }

    public String getAtm() {
        return atm;
    }

    public void setAtm(String atm) {
        this.atm = atm;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
    
    private Double getMin(List<Double> listDouble)
    {
        Double result = - 1.0;
        for(Double d : listDouble)
        {
            if(result == -1.0 || result > d)
            {
                result = d;
            }
        }
        return result;
    }
    
    public Double getScore(Square s, String typePlace)
    {
        
        
        Double maxDistance = 0.0;
        Double distanceOnFoot = 0.0;
        Double distanceOnCar = 0.0;
        
        if(typePlace.equals("Atm"))
        {
            maxDistance = Double.parseDouble(atm);
            distanceOnFoot = s.getNearestAtm().getDistanceOnFoot();
            distanceOnCar = s.getNearestAtm().getDistanceOnCar();
        }
        else if(typePlace.equals("Supermarket"))
        {
            maxDistance = Double.parseDouble(supermarket);
            distanceOnFoot = s.getNearestSupermarket().getDistanceOnFoot();
            distanceOnCar = s.getNearestSupermarket().getDistanceOnCar();
        }
        else if(typePlace.equals("Transport"))
        {
            maxDistance = Double.parseDouble(transport);
            distanceOnFoot = s.getNearestTransport().getDistanceOnFoot();
            distanceOnCar = s.getNearestTransport().getDistanceOnCar();
        }
        
        
        Double result;
        if(distanceOnFoot < maxDistance
           || (distanceOnCar < maxDistance && onCar))
        {
            result = 1.0;
        }
        else
        {
            List<Double> listDistance = new ArrayList<>(); listDistance.add(distanceOnFoot);
            if(onCar)
                listDistance.add(distanceOnCar);
            Double minDistance = getMin(listDistance);
            
            result = 1 - ((minDistance - maxDistance)/maxDistance);
            
            if(result < 0.0)
            {
                result = 0.0;
            }
            
        }
        return result;
    }
    
}
