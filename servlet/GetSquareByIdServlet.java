/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author François
 */
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import com.mongodb.MongoClient;
import model.Square;
import dao.MongoDBSquareDao;
import java.io.PrintWriter;
 
@WebServlet("/getSquareById")
public class GetSquareByIdServlet extends HttpServlet {
 
    private static final long serialVersionUID = -6554920927964049383L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSquareDao squareDAO = new MongoDBSquareDao(mongo);
        String id = request.getParameter("id");
        
        Square square = squareDAO.readSquare(id);
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();
        
        out.write("<square>");
            out.write("<id>" + square.getId() + "</id>");
            out.write("<lati>" + square.getLatitude() + "</lati>");
            out.write("<long>" + square.getLongitude() + "</long>");
            out.write("<atm>");
                out.write("<id>" + square.getNearestAtm().getId() + "</id>");
                out.write("<lati>" + square.getNearestAtm().getLatitude() + "</lati>");
                out.write("<long>" + square.getNearestAtm().getLongitude() + "</long>");
            out.write("</atm>");
            out.write("<supermarket>");
                out.write("<id>" + square.getNearestSupermarket().getId() + "</id>");
                out.write("<lati>" + square.getNearestSupermarket().getLatitude() + "</lati>");
                out.write("<long>" + square.getNearestSupermarket().getLongitude() + "</long>");
            out.write("</supermarket>");
            out.write("<transport>");
                out.write("<id>" + square.getNearestTransport().getId() + "</id>");
                out.write("<lati>" + square.getNearestTransport().getLatitude() + "</lati>");
                out.write("<long>" + square.getNearestTransport().getLongitude() + "</long>");
            out.write("</transport>");
        out.write("</square>");
    }
}