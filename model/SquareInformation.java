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
public class SquareInformation {
    
    
    private String id;
    
    private Double colorScore;
    
    private Double latitude;
    
    private Double longitude;
    
    public SquareInformation(Square s, SquareCriteria c) {
        this.id = s.getId();
        this.latitude = s.getLatitude();
        this.longitude = s.getLongitude();
        this.colorScore = getSquareColor(s,c);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getColorScore() {
        return colorScore;
    }

    public void setColor(Double color) {
        this.colorScore = color;
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
    
    private List<Double> makeScoreList(Square s, SquareCriteria c)
    {
        List<Double> scores = new ArrayList<>();
        if(c.getAtm() != null)
        {
            if(!c.getAtm().equals("null"))
                scores.add(c.getScore(s, "Atm"));
        }
        if(c.getSupermarket() != null)
        {
            if(!c.getSupermarket().equals("null"))
                scores.add(c.getScore(s, "Supermarket"));
        }
        if(c.getPollution() != null)
        {
            if(!c.getPollution().equals("null"))
                scores.add(c.getPollutionScore(s));
        }
        if(!c.getDoctor().equals("null"))
        {
            scores.add(c.getScore(s, "Doctor"));
        }
        if(!c.getKindergarten().equals("null"))
        {
            scores.add(c.getScore(s, "Kindergarten"));
        }
        
        return scores;
    }
    
    
    private Double getSquareColor(Square s, SquareCriteria c){
        
        List<Double> scores = makeScoreList(s,c);
        
        Double resultScoreNumerator = 0.0;
        Double resultScoreDenominator = 0.0;
        
        for(Double d : scores)
        {
            resultScoreNumerator += d;
            resultScoreDenominator += 1;
        }
        
        Double finalScoreResult;
        
        if(resultScoreDenominator != 0)
        {
            finalScoreResult = resultScoreNumerator/resultScoreDenominator;
        }
        else
        {
            finalScoreResult = 1.0;
        }
 
        return finalScoreResult;
    }
    
    public static List<SquareInformation> convertSquares(List<Square> squares, SquareCriteria c)
    {
        List<SquareInformation> result = new ArrayList<>();
        
        for(Square s : squares)
        {
            SquareInformation si = new SquareInformation(s, c);
            result.add(si);
        }
        
        return result;
    }

    @Override
    public String toString() {
        return "SquareInformation{" + "id=" + id + ", colorScore=" + colorScore + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
    
}
