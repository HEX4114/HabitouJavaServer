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
    
    public SquareInformation() {
    }

    public SquareInformation(Square s, Criterions c) {
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
        this.colorScore = colorScore;
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
    
    private List<Double> makeScoreList(Square s, Criterions c)
    {
        List<Double> scores = new ArrayList<>();
        if(c.getAtm() != null)
        {
            scores.add(c.getScore(s, "Atm"));
        }
        if(c.getSupermarket() != null)
        {
            scores.add(c.getScore(s, "Supermarket"));
        }
        if(c.getTransport() != null)
        {
            scores.add(c.getScore(s, "Transport"));
        }
        
        
        return scores;
    }
    
    
    private Double getSquareColor(Square s, Criterions c){
        
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
    
    public static List<SquareInformation> convertSquares(List<Square> squares, Criterions c)
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
    
    
     public static void main(String[] args) {
       List<Square> squares = new ArrayList<>();
       squares.add(new Square(1.0, 2.0, new Atm(1.0, 3.0, 5.0, 3.0), new Supermarket(1.0, 3.0, 5.0, 3.0), new Transport(1.0, 3.0, 15.0, 12.0)));
       
       Criterions  c = new Criterions(true, null, null, "10");
       
       List<SquareInformation> squaresInfo = SquareInformation.convertSquares(squares, c);
       
       System.out.println(squaresInfo);
       
       
       
    }
    
    
}
