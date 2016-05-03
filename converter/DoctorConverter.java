/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Doctor;

/**
 *
 * @author François
 */
public class DoctorConverter {

    // convert Square Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Doctor d) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("walk", WalkConverter.toDBObject(d.getWalk()))
                .append("drive", DriveConverter.toDBObject(d.getDrive()));

        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Doctor toDoctor(DBObject doc) {
        Doctor d = new Doctor(WalkConverter.toWalk((DBObject) doc.get("walk")), DriveConverter.toDrive((DBObject) doc.get("drive")));
        return d;
    }
}
