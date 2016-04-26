/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Atm;
import model.Square;
import model.Supermarket;
import model.Transport;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class SquareConverter {
    
    // convert Square Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Square s) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("latitude", s.getLatitude()).append("longitude", s.getLongitude())
                .append("nearestAtm", s.getNearestAtm())
                .append("nearestSupermarket", s.getNearestSupermarket())
                .append("nearestTransport", s.getNearestTransport());
                
        if (s.getId() != null)
            builder = builder.append("_id", new ObjectId(s.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Square toSquare(DBObject doc) {
        Square s = new Square();
        s.setLatitude((Integer) doc.get("latitude"));
        s.setLongitude((Integer) doc.get("longitude"));
        s.setNearestAtm((Atm) doc.get("nearestAtm"));
        s.setNearestTransport((Transport) doc.get("nearestAtm"));
        s.setNearestSupermarket((Supermarket) doc.get("nearestAtm"));
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
 
    }
    
}
