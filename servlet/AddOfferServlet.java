
package servlet;

import com.mongodb.MongoClient;
import dao.MongoDBOfferDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Offer;


@WebServlet("/addOffer")
public class AddOfferServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);
        String adress = request.getParameter("adress");
        String type = request.getParameter("type");
        String link = request.getParameter("link");
        Double price = Double.parseDouble(request.getParameter("price"));
        Double lat = null;
        Double lng = null;
        
        URL yahoo = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyArobVSwthXEPCYJFsepnC0yRz13ER9EQU");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null){
            inputLine = inputLine.replaceAll(" ", "");
            System.out.println(inputLine);
            String elementType = inputLine.substring(0, 5);
            if(elementType.equals("<lat>"))
            {
                String value = inputLine.replaceFirst("<lat>", "");
                value = value.replaceFirst("</lat>", "");
                lat = Double.parseDouble(inputLine);
                continue;
            }
            
            if(elementType.equals("<lng>"))
            {
                String value = inputLine.replaceFirst("<lng>", "");
                value = value.replaceFirst("</lng>", "");
                lng = Double.parseDouble(inputLine);
                break;
            }
        }
        in.close();
        
        Offer offer = new Offer(adress, lat, lng, type, price, link);
        
        offerDAO.createOffer(offer);

        
    }

}
