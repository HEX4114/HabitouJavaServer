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
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import com.mongodb.MongoClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import dao.MongoDBSquareDao;
import java.io.PrintWriter;
import model.Criterions;
import model.SquareInformation;
import model.Adress;
import model.Walk;
import model.Drive;
 
@WebServlet("/getSquares")
public class GetSquaresServlet extends HttpServlet {
 
    OkHttpClient client = new OkHttpClient();
    private static final long serialVersionUID = -6554920927964049383L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSquareDao squareDAO = new MongoDBSquareDao(mongo);
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
        Criterions criterions;
        try {
            criterions = getCriterions(request);
        } catch(Exception e) {
            response.setStatus(204);
            return;
        }
        
        List<SquareInformation> squares = SquareInformation.convertSquares(squareDAO.readAllSquares(), criterions);
        

        PrintWriter out = response.getWriter();
        
        out.write("<document>");
        for(SquareInformation s : squares) {
            out.write("<square>");
            out.write("<id>" + s.getId() + "</id>");
            out.write("<lat>" + s.getLatitude() + "</lat>");
            out.write("<long>" + s.getLongitude() + "</long>");
            out.write("<score>" + s.getColorScore() + "</score>");
            out.write("</square>");
        }
        out.write("</document>");
        
        
    }
    
    private Criterions getCriterions(HttpServletRequest request) throws Exception {
        String onCar = request.getParameter("car");
        String atm = request.getParameter("atm");
        String supermarket = request.getParameter("supermarket");
        String adress = request.getParameter("adress");
        Adress adressLocation;
        if(adress != "null") {
            adressLocation = getLocationFromAdress(request.getParameter("adressstring"));
        } else {
            Walk walk = new Walk("", 0.0, 0.0, 0.0, 0.0);
            Drive drive = new Drive("", 0.0, 0.0, 0.0, 0.0);
            adressLocation = new Adress(walk, drive);
        }
        
        
        Boolean car;
        
        if(onCar.equals("y")) {
            car = true;
        }
        else {
            car = false;
        }
        
        
        Criterions result = new Criterions(car, atm, supermarket, adress, adressLocation);
        
        return result;
    }
    
    private Adress getLocationFromAdress(String adressString) throws Exception {
        String requestFindPlace = "http://maps.googleapis.com/maps/api/geocode/json?address=" + adressString + "&key=AIzaSyAjCKf6zCL0EwJegJ4sV1wBu3T3gQ3fENA";
        
        Request request = new Request.Builder().url(requestFindPlace).build();
        //OkHttpClient client = new OkHttpClient();
        String result = "";

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println("Error : IOException in loadAdressDatas");
        }
        
        String status = getInfosFromJsonResponse(result, "status");
        if(status == "ZERO_RESULTS") {
            throw new Exception();
        }
        double lat = Double.parseDouble(getInfosFromJsonResponse(result, "lat"));
        double lng = Double.parseDouble(getInfosFromJsonResponse(result, "lng"));
        
        Walk walk = new Walk("", lat, lng, 0.0, 0.0);
        Drive drive = new Drive("", lat, lng, 0.0, 0.0);
        return new Adress(walk, drive);
    }
    
    public String getInfosFromJsonResponse(String responseServer, String info)
    {
        String responsePart = "";
        String[] responseParsed = responseServer.split(",");
        for(int i = 0; i < responseParsed.length; i++)
        {
            if(responseParsed[i].contains("\""+info+"\" :"))
            {
                responsePart = responseParsed[i].split(" : ")[1];
                if(info == "lng") {
                    responsePart = responseParsed[i].split(" ")[0];
                } else if(info == "lat") {
                    responsePart = responseParsed[i].split(",")[0];
                }
                return responsePart;
            }
        }
        return "0.0";
    }
}
