/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.Images;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class ImageConverter {
    
    public static DBObject toDBObject(Images i) {
        
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
        if (i.getId() != null)
               builder = builder.append("_id", new ObjectId(i.getId()));
        for(String s : i.getImagesNamesList())
        {
           builder = builder.append("name", s);
        }
        
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Images toImages(DBObject doc) {
        
        Images i = new Images();
        ObjectId id = (ObjectId) doc.get("_id");
        i.setId(id.toString());
        String name = (String) doc.get("name");
        i.addImageName(name);
        
        return i;
    }
    
}
