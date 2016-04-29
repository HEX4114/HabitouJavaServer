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

        List<OfferInformation> offers = OfferInformation.convertOffers(offerDAO.readAllOffers());

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();

        out.write("<document>");
        for (OfferInformation s : offers) {
            out.write("<offer>");
            out.write("<id>" + s.getId() + "</id>");
            out.write("<lat>" + s.getLatitude() + "</lat>");
            out.write("<long>" + s.getLongitude() + "</long>");
            out.write("</offer>");
        }
        out.write("</document>");

    }

//    public static void main(String[] args) {
//        MongoClient mongo = new MongoClient();
//        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);
//
//        List<OfferInformation> offers = OfferInformation.convertOffers(offerDAO.readAllOffers());
//
//        for (OfferInformation s : offers) {
//            System.out.println("<offer>");
//            System.out.println("<id> " + s.getId() + " " + s.getLatitude() + " " + s.getLongitude() + "</long>");
//        }
//
//    }

}
