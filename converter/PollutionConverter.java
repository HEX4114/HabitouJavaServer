/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author François
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Pollution;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class PollutionConverter {
    
    // convert Square Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Pollution p) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("name", p.getName())
                .append("rate", p.getRate())
                .append("lat", p.getLatitude())
                .append("long", p.getLongitude())
                .append("distance", p.getDistance());
                
        return builder.get();
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Pollution toSquare(DBObject doc) {
        Pollution p = new Pollution((String) doc.get("name"), (Double) doc.get("rate"), (Double) doc.get("lat"), (Double) doc.get("long"), (Double) doc.get("distance"));
     
        return p;
 
    }
    
}
