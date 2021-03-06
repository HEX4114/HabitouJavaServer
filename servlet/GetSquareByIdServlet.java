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
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import com.mongodb.MongoClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import model.Square;
import dao.MongoDBSquareDao;
import java.io.PrintWriter;
import java.util.List;

import model.Adress;
import model.SquareCriteria;
import model.Drive;
import model.Walk;

import model.SquareCriteria;
import model.SquareInformation;

 
@WebServlet("/getSquareById")
public class GetSquareByIdServlet extends HttpServlet {
 
    OkHttpClient client = new OkHttpClient();
    private static final long serialVersionUID = -6554920927964049383L;
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBSquareDao squareDAO = new MongoDBSquareDao(mongo, request.getParameter("collection"));

        String id = request.getParameter("id");
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        
        SquareCriteria criterions;
        try {
            criterions = getCriterions(request);
        } catch(Exception e) {
            response.setStatus(204);
            return;
        }
        
        Square square = squareDAO.readSquare(id);
        
        String carString = request.getParameter("car");
        if(carString.equals("y")) {
            square.loadAdressInformations(criterions.getAdressLocation(), true);
        } else {
            square.loadAdressInformations(criterions.getAdressLocation(), false);
        }
        
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
                        out.write("<distance>" + square.getNearestAtm().getWalk().getDistance() + "</distance>");
                    out.write("</walk>");
                    out.write("<drive>");
                        out.write("<name>" + square.getNearestAtm().getDrive().getName() + "</name>");
                        out.write("<lati>" + square.getNearestAtm().getDrive().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestAtm().getDrive().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestAtm().getDrive().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestAtm().getDrive().getDistance() + "</distance>");
                    out.write("</drive>");
                    out.write("<score>" + square.getAtmScore(criterions) + "</score>");
                out.write("</atm>");
                out.write("<supermarket>");
                    out.write("<walk>");
                        out.write("<name>" + square.getNearestSupermarket().getWalk().getName() + "</name>");
                        out.write("<lati>" + square.getNearestSupermarket().getWalk().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestSupermarket().getWalk().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestSupermarket().getWalk().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestSupermarket().getWalk().getDistance() + "</distance>");
                    out.write("</walk>");
                    out.write("<drive>");
                        out.write("<name>" + square.getNearestSupermarket().getDrive().getName() + "</name>");
                        out.write("<lati>" + square.getNearestSupermarket().getDrive().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestSupermarket().getDrive().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestSupermarket().getDrive().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestSupermarket().getDrive().getDistance() + "</distance>");
                    out.write("</drive>");
                    out.write("<score>" + square.getSupermarketScore(criterions) + "</score>");
                out.write("</supermarket>");
                out.write("<adress>");
                    out.write("<walk>");
                        out.write("<name>" + square.getAdress().getWalk().getName() + "</name>");
                        out.write("<lati>" + square.getAdress().getWalk().getLatitude() + "</lati>");
                        out.write("<long>" + square.getAdress().getWalk().getLongitude() + "</long>");
                        out.write("<time>" + square.getAdress().getWalk().getTime() + "</time>");
                        out.write("<distance>" + square.getAdress().getWalk().getDistance() + "</distance>");
                    out.write("</walk>");
                    out.write("<drive>");
                        out.write("<name>" + square.getAdress().getDrive().getName() + "</name>");
                        out.write("<lati>" + square.getAdress().getDrive().getLatitude() + "</lati>");
                        out.write("<long>" + square.getAdress().getDrive().getLongitude() + "</long>");
                        out.write("<time>" + square.getAdress().getDrive().getTime() + "</time>");
                        out.write("<distance>" + square.getAdress().getDrive().getDistance() + "</distance>");
                    out.write("</drive>");
                    out.write("<score>" + square.getAdressScore(criterions) + "</score>");
                out.write("</adress>");
                out.write("<doctor>");
                    out.write("<walk>");
                        out.write("<name>" + square.getNearestDoctor().getWalk().getName() + "</name>");
                        out.write("<lati>" + square.getNearestDoctor().getWalk().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestDoctor().getWalk().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestDoctor().getWalk().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestDoctor().getWalk().getDistance() + "</distance>");
                    out.write("</walk>");
                    out.write("<drive>");
                        out.write("<name>" + square.getNearestDoctor().getDrive().getName() + "</name>");
                        out.write("<lati>" + square.getNearestDoctor().getDrive().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestDoctor().getDrive().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestDoctor().getDrive().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestDoctor().getDrive().getDistance() + "</distance>");
                    out.write("</drive>");
                    out.write("<score>" + square.getDoctorScore(criterions) + "</score>");
                out.write("</doctor>");
                out.write("<kindergarten>");
                    out.write("<walk>");
                        out.write("<name>" + square.getNearestKindergarten().getWalk().getName() + "</name>");
                        out.write("<lati>" + square.getNearestKindergarten().getWalk().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestKindergarten().getWalk().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestKindergarten().getWalk().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestKindergarten().getWalk().getDistance() + "</distance>");
                    out.write("</walk>");
                    out.write("<drive>");
                        out.write("<name>" + square.getNearestKindergarten().getDrive().getName() + "</name>");
                        out.write("<lati>" + square.getNearestKindergarten().getDrive().getLatitude() + "</lati>");
                        out.write("<long>" + square.getNearestKindergarten().getDrive().getLongitude() + "</long>");
                        out.write("<time>" + square.getNearestKindergarten().getDrive().getTime() + "</time>");
                        out.write("<distance>" + square.getNearestKindergarten().getDrive().getDistance() + "</distance>");
                    out.write("</drive>");
                    out.write("<score>" + square.getKindergartenScore(criterions) + "</score>");
                out.write("</kindergarten>");
                out.write("<pollution>");
                    out.write("<name>" + square.getPollution().getName() + "</name>");
                    out.write("<rate>" + square.getPollution().getRate() + "</rate>");
                    out.write("<lati>" + square.getPollution().getLatitude() + "</lati>");
                    out.write("<long>" + square.getPollution().getLongitude() + "</long>");
                    out.write("<distance>" + square.getPollution().getDistance() + "</distance>");
                    out.write("<score>" + criterions.getPollutionScore(square) + "</score>");
                out.write("</pollution>");
            out.write("</square>");
        out.write("</document>");
    }
    
    
    private SquareCriteria getCriterions(HttpServletRequest request) throws Exception {
        String onCar = request.getParameter("car");
        String atm = request.getParameter("atm");
        String supermarket = request.getParameter("supermarket");
        String adress = request.getParameter("adress");
        Adress adressLocation = null;
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
        
        SquareCriteria result = new SquareCriteria(car, atm, supermarket, adress, adressLocation, doctor, kindergarten, pollution);
        return result;
    }
    
    private Adress getLocationFromAdress(String adressString) throws Exception{
        String requestFindPlace = "https://maps.googleapis.com/maps/api/geocode/json?address=" + adressString + "&key=AIzaSyAjCKf6zCL0EwJegJ4sV1wBu3T3gQ3fENA";
        requestFindPlace = requestFindPlace.replaceAll(" ", "+");
        Request request = new Request.Builder().url(requestFindPlace).build();
        
        String result = "";

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println("Error : IOException in getLocationFromAdress (GetSquareByIdServlet, requete google)");
        }
        
        String status = getInfosFromJsonResponse(result, "status");
        if(status == "ZERO_RESULTS") {
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