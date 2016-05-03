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
import model.SquareCriteria;
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
        MongoDBSquareDao squareDAO = new MongoDBSquareDao(mongo, request.getParameter("collection"));
        

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
        SquareCriteria criterions;
        try {
            criterions = getSquareCriteria(request);
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
    
    
    private SquareCriteria getSquareCriteria(HttpServletRequest request) throws Exception {
        String onCar = request.getParameter("car");
        String atm = request.getParameter("atm");
        String supermarket = request.getParameter("supermarket");
        String adress = request.getParameter("adress");
        Adress adressLocation;
        if(adress.equals("null")) {
            Walk walk = new Walk("", 0.0, 0.0, 0.0, 0.0);
            Drive drive = new Drive("", 0.0, 0.0, 0.0, 0.0);
            adressLocation = new Adress(walk, drive);
        } else {
            adressLocation = getLocationFromAdress(request.getParameter("adressstring"));
        }
        String doctor = request.getParameter("doctor");
        String kindergarten = request.getParameter("kindergarten");
        String pollution = request.getParameter("pollution");
        
        Boolean car = onCar.equals("y");        
        
        return new SquareCriteria(car, atm, supermarket, adress, adressLocation, doctor, kindergarten, pollution);
    }
    
    private Adress getLocationFromAdress(String adressString) throws Exception {
        String requestFindPlace = "https://maps.googleapis.com/maps/api/geocode/json?address=" + adressString + "&key=AIzaSyAjCKf6zCL0EwJegJ4sV1wBu3T3gQ3fENA";
        requestFindPlace = requestFindPlace.replaceAll(" ", "+");
        Request request = new Request.Builder().url(requestFindPlace).build();
        
        String result = "";

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println("Error : IOException in loadAdressDatas (Requête Google)");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            System.out.println(e.getStackTrace());
            System.out.println(requestFindPlace);
        }
        
        String status = getInfosFromJsonResponse(result, "status");
        if(status == "ZERO_RESULTS") {
            System.out.println("pas de résultat....");
            throw new Exception();
        }
        
        String latString = getInfosFromJsonResponse(result, "lat");
        String lngString = getInfosFromJsonResponse(result, "lng");
        double lat = Double.parseDouble(latString);
        double lng = Double.parseDouble(lngString);
        
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
                responsePart = responseParsed[i].split("\""+info+"\" : ")[1];
                if(info.equals("lng")) {
                    responsePart = responsePart.split("\n")[0];
                } 
                if(info.equals("status")) {
                    responsePart = responsePart.split("\n")[0];
                    responsePart = responsePart.replaceAll("\"", "");
                }
                return responsePart;
            }
        }
        return "0.0";
    }
}
