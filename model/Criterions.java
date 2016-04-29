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
    
    private Boolean onCar;
    
    private String atm;
    
    private String supermarket;
   
    
    public Criterions(){
        
    }
    
    public Criterions(Boolean onCar, String atm, String supermarket) {
        
        this.onCar = onCar;
        this.atm = atm;
        this.supermarket = supermarket;
        
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
            distanceOnFoot = s.getNearestAtm().getWalk().getTime();
            distanceOnCar = s.getNearestAtm().getDrive().getTime();
        }
        else if(typePlace.equals("Supermarket"))
        {
            maxDistance = Double.parseDouble(supermarket);
            distanceOnFoot = s.getNearestSupermarket().getWalk().getTime();
            distanceOnCar = s.getNearestSupermarket().getDrive().getTime();
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
