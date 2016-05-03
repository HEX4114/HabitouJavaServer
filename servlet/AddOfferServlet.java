
package servlet;

import com.mongodb.MongoClient;
import dao.MongoDBOfferDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Offer;


@WebServlet("/addOffer")
@MultipartConfig
public class AddOfferServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBOfferDao offerDAO = new MongoDBOfferDao(mongo);
        
        String address = request.getParameter("address");
        String type = request.getParameter("type");
        String link = request.getParameter("link");
        Double price = Double.parseDouble(request.getParameter("price"));
        Double lat = null;
        Double lng = null;

        String encodedAddress = URLEncoder.encode(address, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
        
        URL google = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+encodedAddress+"&key=AIzaSyArobVSwthXEPCYJFsepnC0yRz13ER9EQU");
        URLConnection yc = google.openConnection();
        
        
        try{
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
                    lat = Double.parseDouble(value);
                    continue;
                }

                if(elementType.equals("<lng>"))
                {
                    String value = inputLine.replaceFirst("<lng>", "");
                    value = value.replaceFirst("</lng>", "");
                    lng = Double.parseDouble(value);
                    break;
                }
            }
            in.close();

            Offer offer = new Offer(URLDecoder.decode(address, "UTF-8"), lat, lng, type, price, link);

            offer = offerDAO.createOffer(offer);
            uploadImageFile(request, response, offer.getId());
        }
        catch(IOException e){
            
        }

        
    }
    
    
    private void uploadImageFile(HttpServletRequest request, HttpServletResponse response, String id){
        
        try {
            
            Part filePart = null;
            filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            
            String fileName = filePart.getSubmittedFileName();
            File path = new File(getServletContext().getContextPath()+"/resources/images/"+id+"/");
            if (!path.exists()) {
                path.mkdirs();
            }
            File imageToUpload = new File(path.getAbsolutePath()+"/"+fileName);
            filePart.write(imageToUpload.getAbsolutePath());
            
        } catch (IOException | ServletException ex) {
            Logger.getLogger(AddOfferServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
