/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Atm;

/**
 *
 * @author François
 */
public class AtmConverter {

    // convert Square Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Atm a) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("walk", WalkConverter.toDBObject(a.getWalk()))
                .append("drive", DriveConverter.toDBObject(a.getDrive()));

        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Atm toAtm(DBObject doc) {
        Atm a = new Atm(WalkConverter.toWalk((DBObject) doc.get("walk")), DriveConverter.toDrive((DBObject) doc.get("drive")));
        return a;
    }

}
