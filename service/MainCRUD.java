import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import dao.MongoDBSquareDao;
import java.util.List;
import model.Atm;
import model.Square;
import model.Supermarket;
import model.Transport;

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
       MongoDBSquareDao mg = new MongoDBSquareDao(mongoClient);
        
       Atm a = new Atm(12.3, 13.3, 14.3);
       Supermarket sm = new Supermarket(13.3, 14.3, 15.3);
       Transport t = new Transport(20.3, 19.3, 17.3);
       Square s1 = new Square(12.0, 13.0, a, sm, t);
       
       System.out.println(s1.toString());
       
       Square s2 = mg.createSquare(s1);
             
        List<Square> squares = mg.readAllSquares();
        
        for(Square sq : squares) {
            System.out.println(sq.toString());
        }
    }
    
/*
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
*/
}
