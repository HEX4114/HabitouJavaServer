/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sylwia
 */
public class OfferCriteria {
    
    private Boolean toBuy;
    
    private Boolean toRent;
    
    private Integer rooms;
    
    private Integer floor;
    
    private Integer maxPrice;

    public OfferCriteria() {
    }

    public OfferCriteria(Boolean toBuy, Boolean toRent, Integer rooms, Integer floor, Integer maxPrice) {
        this.toBuy = toBuy;
        this.toRent = toRent;
        this.rooms = rooms;
        this.floor = floor;
        this.maxPrice = maxPrice;
    }

    public Boolean getToBuy() {
        return toBuy;
    }

    public void setToBuy(Boolean toBuy) {
        this.toBuy = toBuy;
    }

    public Boolean getToRent() {
        return toRent;
    }

    public void setToRent(Boolean toRent) {
        this.toRent = toRent;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    
    
}
