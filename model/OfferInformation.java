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
 * @author Sylwia
 */
public class OfferInformation {

    private String id;

    private Double latitude;

    private Double longitude;

    private String type;

    public OfferInformation(Offer of) {
        this.id = of.getId();
        this.latitude = of.getLatitude();
        this.longitude = of.getLongitude();
        this.type = of.getType();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<OfferInformation> convertOffers(List<Offer> offers) {
        List<OfferInformation> result = new ArrayList<>();

        for (Offer offer : offers) {
            OfferInformation of = new OfferInformation(offer);
            result.add(of);
        }

        return result;
    }

    public static List<OfferInformation> convertOffers(List<Offer> offers, OfferCriteria criteria) {
        List<OfferInformation> result = new ArrayList<>();

        for (Offer offer : offers) {
            
            if(criteria.getToBuy()==true && criteria.getToRent()==false && offer.toRent()) {
                continue;
            } else if(criteria.getToBuy()==false && criteria.getToRent()==true && offer.toBuy()) {
                continue;
            } else if(criteria.getToBuy()==false && criteria.getToRent()==false) {
                continue;
            }else if(criteria.getRooms()!=null && criteria.getRooms()<offer.getRooms()) {
                continue;
            } else if(criteria.getFloor()!=null && criteria.getFloor()<offer.getFloor()) {
                continue;
            } else if(criteria.getMaxPrice()!=null && criteria.getMaxPrice()<offer.getPrice()) {
                continue;
            }
            OfferInformation of = new OfferInformation(offer);
            result.add(of);
        }

        return result;
    }

}
