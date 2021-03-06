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
public class SquareCriteria {
    
    private Boolean onCar;
    
    private String atm;
    
    private String supermarket;
    
    private String adress;
    
    private Adress adressLocation;
    
    private String doctor;
    
    private String kindergarten;

    private String pollution;
    
    public SquareCriteria(){
        
    }
    
    public SquareCriteria(Boolean onCar, String atm, String supermarket, String adress, Adress adressLocation, String doctor, String kindergarten, String pollution) {
        this.onCar = onCar;
        this.atm = atm;
        this.supermarket = supermarket;
        this.adress = adress;
        this.adressLocation = adressLocation;
        this.doctor = doctor;
        this.kindergarten = kindergarten;
        this.pollution = pollution;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    public Adress getAdressLocation() {
        return adressLocation;
    }

    public void setAdressLocation(Adress adressLocation) {
        this.adressLocation = adressLocation;
    }
    
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getKindergarten() {
        return kindergarten;
    }

    public void setKindergarten(String kindergarten) {
        this.kindergarten = kindergarten;
    }
        
    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
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
        else if(typePlace.equals("Adress"))
        {
            s.loadAdressInformations(this.adressLocation, this.onCar);
            maxDistance = Double.parseDouble(adress);
            distanceOnFoot = s.getAdress().getWalk().getTime();
            distanceOnCar = s.getAdress().getDrive().getTime();
        }
        else if(typePlace.equals("Doctor"))
        {
            maxDistance = Double.parseDouble(doctor);
            distanceOnFoot = s.getNearestDoctor().getWalk().getTime();
            distanceOnCar = s.getNearestDoctor().getDrive().getTime();
        }
        else if(typePlace.equals("Kindergarten"))
        {
            maxDistance = Double.parseDouble(kindergarten);
            distanceOnFoot = s.getNearestKindergarten().getWalk().getTime();
            distanceOnCar = s.getNearestKindergarten().getDrive().getTime();
        }
        
        Double result;
        if(distanceOnFoot <= maxDistance
           || (distanceOnCar <= maxDistance && onCar))
        {
            result = 1.0;
        }
        else
        {
            List<Double> listDistance = new ArrayList<>(); 
            listDistance.add(distanceOnFoot);
            if(onCar)
                listDistance.add(distanceOnCar);
            Double minDistance = getMin(listDistance);
            
            result = 1 - ((minDistance - maxDistance)/maxDistance);
            
            if(result < 0.0)
            {
                result = 0.00000000001;
            }
            
        }
        return result;
    }
    
    public Double getPollutionScore(Square s){
        
        if(!pollution.equals("null")) {
            Double maxRate = Double.parseDouble(pollution);
            Double squarePollutionRate = s.getPollution().getRate();
            Double result;

            if(squarePollutionRate <= maxRate){
                result = 1.0;
            }
            else{
                result = 1 - ((squarePollutionRate - maxRate)/maxRate);
                if(result < 0.0)
                {
                    result = 0.00000000001;
                }
            }
            return result;
        }
        
        return -1.0;
        
    }
    
}
