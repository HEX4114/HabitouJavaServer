package service;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sylwia
 */
public class MainCRUD {

    static MongoDatabase db;

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        db = mongoClient.getDatabase("habitoudb");

        findAll();
        findDistance("atm", 6);
        //insertPoint();

    }

    private static void findAll() {
        System.out.println("== Find all");
        FindIterable<Document> iterable = db.getCollection("points").find();
        //new Document("borough", "Manhattan"));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }

    private static void findDistance(String point, int dist) {
        System.out.println("== Find distance");
        FindIterable<Document> iterable = db.getCollection("points").find(
                new Document(point + ".dist", new Document("$lt", dist)));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }

    private static void insertPoint() {
        db.getCollection("points").insertOne(
                new Document("lat", -52.292034)
                .append("long", 17.659409)
                .append("atm",
                        new Document()
                        .append("dist", 8)
                        .append("lat", -52.292034)
                        .append("long", 17.659409))
                .append("shop",
                        new Document()
                        .append("dist", 9)
                        .append("lat", -52.292034)
                        .append("long", 17.659409))
                .append("transport",
                        new Document()
                        .append("dist", 10)
                        .append("lat", -52.292034)
                        .append("long", 17.659409)));

    }

}
