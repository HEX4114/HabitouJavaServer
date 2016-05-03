/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Kindergarten;

/**
 *
 * @author François
 */
public class KindergartenConverter {

    // convert Square Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Kindergarten k) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("walk", WalkConverter.toDBObject(k.getWalk()))
                .append("drive", DriveConverter.toDBObject(k.getDrive()));

        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Kindergarten toKindergarten(DBObject doc) {
        Kindergarten k = new Kindergarten(WalkConverter.toWalk((DBObject) doc.get("walk")), DriveConverter.toDrive((DBObject) doc.get("drive")));
        return k;
    }

}
