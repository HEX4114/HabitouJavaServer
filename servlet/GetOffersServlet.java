/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mongodb.MongoClient;
import dao.MongoDBOfferDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OfferCriteria;
import model.OfferInformation;

/**
 *
 * @author Sylwia
 */
@WebServlet("/getOffers")
public class GetOffersServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);

        List<OfferInformation> offers;
        
        if (request.getParameterMap().isEmpty()) {
            offers = OfferInformation.convertOffers(offerDAO.readAllOffers());
        } else {
            OfferCriteria criterions = getCriteria(request);
            offers = OfferInformation.convertOffers(offerDAO.readAllOffers(), criterions);
        }

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();

        out.write("<document>");
        for (OfferInformation s : offers) {
            out.write("<offer>");
            out.write("<id>" + s.getId() + "</id>");
            out.write("<lat>" + s.getLatitude() + "</lat>");
            out.write("<long>" + s.getLongitude() + "</long>");
            out.write("<type>" + s.getType() + "</type>");
            out.write("</offer>");
        }
        out.write("</document>");

    }
        //    public static void main(String[] args) {
    //        MongoClient mongo = new MongoClient();
    //        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);
    //
    //        //OfferCriteria criterions = new OfferCriteria(true, true, null, null, null);
    //        List<OfferInformation> offers = OfferInformation.convertOffers(offerDAO.readAllOffers());
    //        //List<OfferInformation> offers = OfferInformation.convertOffers(offerDAO.readAllOffers(), criterions);
    //
    //        for (OfferInformation s : offers) {
    //            System.out.println("<offer>");
    //            System.out.println("<id> " + offerDAO.readOffer(s.getId()));
    //        }
    //
    //    }

    private OfferCriteria getCriteria(HttpServletRequest request) {
        String buy = request.getParameter("buy");
        String rent = request.getParameter("rent");
        String rooms = request.getParameter("rooms");
        String floor = request.getParameter("floor");
        String maxPrice = request.getParameter("maxPrice");

        Boolean toBuy = buy.equals("y");
        Boolean toRent = rent.equals("y");

        return new OfferCriteria(toBuy, toRent, Integer.parseInt(rooms), Integer.parseInt(floor), Integer.parseInt(maxPrice));

    }

}
