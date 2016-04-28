/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Supermarket;

/**
 *
 * @author François
 */
public class SupermarketConverter {
    
    public static DBObject toDBObject(Supermarket s) {
 
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("walk", WalkConverter.toDBObject(s.getWalk()))
                .append("drive", DriveConverter.toDBObject(s.getDrive()));
        
        return builder.get();
                
    }
 
    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Supermarket toSupermarket(DBObject doc) {
        Supermarket s = new Supermarket(WalkConverter.toWalk((DBObject) doc.get("walk")), DriveConverter.toDrive((DBObject) doc.get("drive")));
        return s;
    }
    
}
