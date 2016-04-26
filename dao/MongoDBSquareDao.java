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
import converter.SquareConverter;
import java.util.ArrayList;
import java.util.List;
import model.Square;
import org.bson.types.ObjectId;

/**
 *
 * @author François
 */
public class MongoDBSquareDao {

    private DBCollection col;

    public MongoDBSquareDao(MongoClient mongo) {
        this.col = mongo.getDB("habitoudb").getCollection("squares");
    }

    public Square createSquare(Square p) {
        DBObject doc = SquareConverter.toDBObject(p);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        p.setId(id.toString());
        return p;
    }

    public void updateSquare(Square p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        this.col.update(query, SquareConverter.toDBObject(p));
    }

    public List<Square> readAllSquares() {
        List<Square> data = new ArrayList<>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Square p = SquareConverter.toSquare(doc);
            data.add(p);
        }
        return data;
    }

    public void deleteSquare(Square p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        this.col.remove(query);
    }

    public Square readSquare(Square p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        DBObject data = this.col.findOne(query);
        return SquareConverter.toSquare(data);
    }

}
