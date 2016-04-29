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

    public OfferInformation(Offer of) {
        this.id = of.getId();
        this.latitude = of.getLatitude();
        this.longitude = of.getLongitude();
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

    public static List<OfferInformation> convertOffers(List<Offer> offers) {
        List<OfferInformation> result = new ArrayList<>();

        for (Offer offer : offers) {
            OfferInformation of = new OfferInformation(offer);
            result.add(of);
        }

        return result;
    }

}
