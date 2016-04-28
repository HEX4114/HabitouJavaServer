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
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import com.mongodb.MongoClient;
import dao.MongoDBSquareDao;
import java.io.PrintWriter;
import model.Criterions;
import model.SquareInformation;
 
@WebServlet("/getSquares")
public class GetSquaresServlet extends HttpServlet {
 
    private static final long serialVersionUID = -6554920927964049383L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSquareDao squareDAO = new MongoDBSquareDao(mongo);
        
        Criterions criterions = getCriterions(request);
        
        List<SquareInformation> squares = SquareInformation.convertSquares(squareDAO.readAllSquares(), criterions);
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();
        int i=0;
        
        for(SquareInformation s : squares) {
            out.write("<square" + i + ">");
            out.write("<id>" + s.getId() + "</id>");
            out.write("<lati>" + s.getLatitude() + "</lati>");
            out.write("<long>" + s.getLongitude() + "</long>");
            out.write("<score>" + s.getColorScore() + "</score>");
            out.write("</square" + i + ">");
            i++;
        }
        
        
    }
    
    private Criterions getCriterions(HttpServletRequest request){
        String onCar = request.getParameter("car");
        String atm = request.getParameter("atm");
        String supermarket = request.getParameter("supermarket");
        
        Boolean car;
        
        if(onCar.equals("y"))
        {
            car = true;
        }
        else
        {
            car = false;
        }
        
        
        Criterions result = new Criterions(car, atm, supermarket);
        
        return result;
    }
}
