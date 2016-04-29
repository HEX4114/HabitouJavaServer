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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Offer;

/**
 *
 * @author Sylwia
 */
@WebServlet("/getOfferById")
public class GetOfferByIdServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);
        String id = request.getParameter("id");

        Offer offer = offerDAO.readOffer(id);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();

        out.write("<document>");
        out.write("<offer>");
        out.write("<id>" + offer.getId() + "</id>");
        out.write("<address>" + offer.getAddress() + "</address>");
        out.write("<lati>" + offer.getLatitude() + "</lati>");
        out.write("<long>" + offer.getLongitude() + "</long>");
        out.write("<type>" + offer.getType() + "</type>");
        out.write("<price>" + offer.getPrice() + "</price>");
        out.write("<link>" + offer.getLink() + "</link>");
        out.write("</offer>");
        out.write("</document>");

    }

}
