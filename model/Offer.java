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
public class Offer {

    private String id;

    private String address;

    private Double latitude;

    private Double longitude;

    private String type;

    private Double price;

    private String link;

    private Integer rooms;

    private Integer floor;

    private Double m2;

    public Offer(String address, Double latitude, Double longitude, String type, Double price, String link) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.price = price;
        this.link = link;
    }

    public Offer(String address, Double latitude, Double longitude, String type, Double price, String link, Integer rooms, Integer floor, Double m2) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.price = price;
        this.link = link;
        this.rooms = rooms;
        this.floor = floor;
        this.m2 = m2;
    }

    public Offer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    boolean toBuy() {
        return type.equals("vendre") || type.equals("buy");
    }

    boolean toRent() {
        return type.equals("louer") || type.equals("rent");
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", type=" + type + ", price=" + price + ", link=" + link 
                + ", floor=" + floor + ", rooms=" + rooms + ", price=" + price + '}';
    }

}
