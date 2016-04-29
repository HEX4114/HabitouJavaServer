/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Drive;

/**
 *
 * @author François
 */
public class DriveConverter {
    
    public static DBObject toDBObject(Drive d) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("name", d.getName())
                .append("lat", d.getLatitude())
                .append("long", d.getLongitude())
                .append("time", d.getTime())
                .append("distance", d.getDistance());

//        if (a.getId() != null) {
//            builder = builder.append("_id", new ObjectId(a.getId()));
//        }
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Drive toDrive(DBObject doc) {
        Drive d = new Drive((String) doc.get("name"), (Double) doc.get("lat"), (Double) doc.get("long"), (Double) doc.get("time"), (Double) doc.get("distance"));
        return d;
    }
}
