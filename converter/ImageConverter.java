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
        
        Integer j = 0;
        for(String s : i.getImagesNamesList())
        {
           builder = builder.append("name"+j, s);
           j++;
        }
        
        return builder.get();
    }

    // convert DBObject Object to Square
    // take special note of converting ObjectId to String
    public static Images toImages(DBObject doc) {
        
        Images i = new Images();
        
        Integer j = 0;
        String name = (String) doc.get("name"+j);
        while(name != null)
        {
            i.addImageName(name);
            j++;
            name = (String) doc.get("name"+j);
        }
        
        return i;
    }
    
}
