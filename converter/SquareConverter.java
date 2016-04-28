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
                .append("lat", s.getLatitude()).append("long", s.getLongitude())
                .append("atm", AtmConverter.toDBObject(s.getNearestAtm()))
                .append("supermarket", SupermarketConverter.toDBObject(s.getNearestSupermarket()));
                
        if (s.getId() != null)
            builder = builder.append("_id", new ObjectId(s.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Square toSquare(DBObject doc) {
        Square s = new Square();
        s.setLatitude((Double) doc.get("lat"));
        s.setLongitude((Double) doc.get("long"));
        s.setNearestAtm(AtmConverter.toAtm((DBObject) doc.get("atm")));
        s.setNearestSupermarket(SupermarketConverter.toSupermarket((DBObject) doc.get("supermarket")));
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
 
    }
    
}
