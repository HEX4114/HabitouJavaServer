/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Walk;

/**
 *
 * @author François
 */
public class WalkConverter {
    
    public static DBObject toDBObject(Walk w) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("name", w.getName())
                .append("lat", w.getLatitude())
                .append("long", w.getLongitude())
                .append("time", w.getTime());

//        if (a.getId() != null) {
//            builder = builder.append("_id", new ObjectId(a.getId()));
//        }
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Walk toWalk(DBObject doc) {
        Walk w = new Walk((String) doc.get("name"), (Double) doc.get("lat"), (Double) doc.get("long"), (Double) doc.get("time"));
        return w;
    }
}
