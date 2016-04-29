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
        
        out.write("<document>");
        out.write("<square>");
            out.write("<id>" + square.getId() + "</id>");
            out.write("<lati>" + square.getLatitude() + "</lati>");
            out.write("<long>" + square.getLongitude() + "</long>");
            out.write("<atm>");
                out.write("<walk>");
                    out.write("<name>" + square.getNearestAtm().getWalk().getName() + "</name>");
                    out.write("<lati>" + square.getNearestAtm().getWalk().getLatitude() + "</lati>");
                    out.write("<long>" + square.getNearestAtm().getWalk().getLongitude() + "</long>");
                    out.write("<time>" + square.getNearestAtm().getWalk().getTime() + "</time>");
                out.write("</walk>");
                out.write("<drive>");
                    out.write("<name>" + square.getNearestAtm().getDrive().getName() + "</name>");
                    out.write("<lati>" + square.getNearestAtm().getDrive().getLatitude() + "</lati>");
                    out.write("<long>" + square.getNearestAtm().getDrive().getLongitude() + "</long>");
                    out.write("<time>" + square.getNearestAtm().getDrive().getTime() + "</time>");
                out.write("</drive>");
            out.write("</atm>");
            out.write("<supermarket>");
                out.write("<walk>");
                    out.write("<name>" + square.getNearestSupermarket().getWalk().getName() + "</name>");
                    out.write("<lati>" + square.getNearestSupermarket().getWalk().getLatitude() + "</lati>");
                    out.write("<long>" + square.getNearestSupermarket().getWalk().getLongitude() + "</long>");
                    out.write("<time>" + square.getNearestSupermarket().getWalk().getTime() + "</time>");
                out.write("</walk>");
                out.write("<drive>");
                    out.write("<name>" + square.getNearestAtm().getDrive().getName() + "</name>");
                    out.write("<lati>" + square.getNearestAtm().getDrive().getLatitude() + "</lati>");
                    out.write("<long>" + square.getNearestAtm().getDrive().getLongitude() + "</long>");
                    out.write("<time>" + square.getNearestAtm().getDrive().getTime() + "</time>");
                out.write("</drive>");
            out.write("</supermarket>");
        out.write("</square>");
        out.write("</document>");
    }
}