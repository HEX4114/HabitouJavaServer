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
    
    public static DBObject toDBObject(Supermarket a) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("latitude", a.getLatitude()).append("longitude", a.getLongitude())
                .append("distance", a.getDistance());
                
        if (a.getId() != null)
            builder = builder.append("_id", new ObjectId(a.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Supermarket toSupermarket(DBObject doc) {
        Supermarket s = new Supermarket();
        s.setLatitude((Integer) doc.get("latitude"));
        s.setLongitude((Integer) doc.get("longitude"));
        s.setName((String) doc.get("name"));
        
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
    }
    
}
