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
                .append("latitude", a.getLatitude()).append("longitude", a.getLongitude())
                .append("distanceOnFoot", a.getDistanceOnFoot())
                .append("distanceOnCar", a.getDistanceOnCar());

//        if (a.getId() != null) {
//            builder = builder.append("_id", new ObjectId(a.getId()));
//        }
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Atm toAtm(DBObject doc) {
        Atm a = new Atm((Double) doc.get("latitude"), (Double) doc.get("longitude"), (Double) doc.get("distanceOnFoot"), (Double) doc.get("distanceOnCar"));
        return a;
    }

}
