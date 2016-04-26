/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Transport;
import model.TypeTransport;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class TransportConverter {
    
    public static DBObject toDBObject(Transport t) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("latitude", t.getLatitude()).append("longitude", t.getLongitude())
                .append("distance", t.getDistance())
                .append("type", t.getTypeTransport());
                
        if (t.getId() != null)
            builder = builder.append("_id", new ObjectId(t.getId()));
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Transport toTransport(DBObject doc) {
        Transport t = new Transport();
        t.setLatitude((Double) doc.get("latitude"));
        t.setLongitude((Double) doc.get("longitude"));
        t.setDistance((Double) doc.get("distance"));
        //t.setTypeTransport((TypeTransport) doc.get("type"));
        
        //ObjectId id = (ObjectId) doc.get("_id");
        //t.setId(id.toString());
        return t;
    }
    
}
