/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Transport;

/**
 *
 * @author François
 */
public class TransportConverter {

    public static DBObject toDBObject(Transport t) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("walk", WalkConverter.toDBObject(t.getWalk()))
                .append("drive", DriveConverter.toDBObject(t.getDrive()));
        
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Transport toTransport(DBObject doc) {
        Transport t = new Transport(WalkConverter.toWalk((DBObject) doc.get("walk")), DriveConverter.toDrive((DBObject) doc.get("drive")));
        return t;
    }

}
