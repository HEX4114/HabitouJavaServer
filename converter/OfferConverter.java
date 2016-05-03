/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Offer;
import org.bson.types.ObjectId;

/**
 *
 * @author Sylwia
 */
public class OfferConverter {

    public static DBObject toDBObject(Offer of) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("address", of.getAddress())
                .append("lat", of.getLatitude())
                .append("long", of.getLongitude())
                .append("type", of.getType())
                .append("price", of.getPrice())
                .append("link", of.getLink())
                .append("rooms", of.getRooms())
                .append("floor", of.getFloor())
                .append("m2", of.getM2());

        if (of.getId() != null) {
            builder = builder.append("_id", new ObjectId(of.getId()));
        }
        return builder.get();
    }

    // convert DBObject Object to Offer
    // take special note of converting ObjectId to String
    public static Offer toOffer(DBObject doc) {
        Offer of = new Offer();
        of.setAddress((String) doc.get("address"));
        of.setLatitude((Double) doc.get("lat"));
        of.setLongitude((Double) doc.get("long"));
        of.setType((String) doc.get("type"));
        of.setPrice((Double) doc.get("price"));
        of.setLink((String) doc.get("link"));
        of.setRooms((Integer) doc.get("rooms"));
        of.setFloor((Integer) doc.get("floor"));
        of.setM2((Double) doc.get("m2"));
        ObjectId id = (ObjectId) doc.get("_id");
        of.setId(id.toString());
        return of;

    }

}
