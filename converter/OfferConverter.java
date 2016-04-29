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
    
        public static DBObject toDBObject(Offer s) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("address", s.getAddress()).append("lat", s.getLatitude()).append("long", s.getLongitude())
                .append("type", s.getType())
                .append("price", s.getPrice())
                .append("link", s.getLink());
                
        if (s.getId() != null)
            builder = builder.append("_id", new ObjectId(s.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Offer
    // take special note of converting ObjectId to String
    public static Offer toOffer(DBObject doc) {
        Offer s = new Offer();
        s.setAddress((String) doc.get("address"));
        s.setLatitude((Double) doc.get("lat"));
        s.setLongitude((Double) doc.get("long"));
        s.setType((String) doc.get("type"));
        s.setPrice((Double) doc.get("price"));
        s.setLink((String) doc.get("link"));
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
 
    }
    
}
