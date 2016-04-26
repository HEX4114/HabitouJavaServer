/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Supermarket;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class SupermarketConverter {
    
    public static DBObject toDBObject(Supermarket s) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("latitude", s.getLatitude()).append("longitude", s.getLongitude())
                .append("distance", s.getDistance())
                .append("name", s.getName());
                
        if (s.getId() != null)
            builder = builder.append("_id", new ObjectId(s.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Supermarket toSupermarket(DBObject doc) {
        Supermarket s = new Supermarket((String) doc.get("name"),(Double) doc.get("latitude"),(Double) doc.get("longitude"),(Double) doc.get("distance"));
        
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
    }
    
}
