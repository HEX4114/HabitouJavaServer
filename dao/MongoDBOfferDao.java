/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import converter.OfferConverter;
import java.util.ArrayList;
import java.util.List;
import model.Offer;
import org.bson.types.ObjectId;

/**
 *
 * @author Sylwia
 */
public class MongoDBOfferDao {

    private DBCollection col;

    public MongoDBOfferDao(MongoClient mongo) {
        this.col = mongo.getDB("habitoudb").getCollection("offers");
    }

    public List<Offer> readAllOffers() {
        List<Offer> data = new ArrayList<>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Offer of = OfferConverter.toOffer(doc);
            data.add(of);
        }
        return data;
    }

    public Offer readOffer(String id) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(id)).get();
        DBObject data = this.col.findOne(query);
        return OfferConverter.toOffer(data);
    }
    
    public static void main(String [] args)
    {
         MongoDBOfferDao dao = new MongoDBOfferDao(new MongoClient("localhost", 27017));
         List<Offer> test = dao.readAllOffers();
         System.out.println(test);
    }

}
