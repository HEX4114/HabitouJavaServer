/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Square;
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
                .append("nearestAtm", AtmConverter.toDBObject(s.getNearestAtm()))
                .append("nearestSupermarket", SupermarketConverter.toDBObject(s.getNearestSupermarket()))
                .append("nearestTransport", TransportConverter.toDBObject(s.getNearestTransport()));
                
        if (s.getId() != null)
            builder = builder.append("_id", new ObjectId(s.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Square toSquare(DBObject doc) {
        Square s = new Square();
        s.setLatitude((Double) doc.get("latitude"));
        s.setLongitude((Double) doc.get("longitude"));
        s.setNearestAtm(AtmConverter.toAtm((DBObject) doc.get("nearestAtm")));
        s.setNearestTransport(TransportConverter.toTransport((DBObject) doc.get("nearestTransport")));
        s.setNearestSupermarket(SupermarketConverter.toSupermarket((DBObject) doc.get("nearestSupermarket")));
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
 
    }
    
}
