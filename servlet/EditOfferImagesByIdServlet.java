package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editOfferImagesById")
public class EditOfferImagesByIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        ServletContext cntx= getServletContext();
          // Get the absolute path of the image
        
        String id = request.getParameter("id");
        File directory = new File(cntx.getContextPath()+"/resources/images/"+id+"/");
        directory.mkdirs();
        //get all the files from a directory
        File[] fList = directory.listFiles();
        
        if(fList[0] != null){
            String mime = cntx.getMimeType(fList[0].getAbsolutePath());
            if (mime == null) {
              response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
              return;
            }

            response.setContentType(mime);
            File file = new File(fList[0].getAbsolutePath());
            response.setContentLength((int)file.length());

            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            // Copy the contents of the file to the output stream
             byte[] buf = new byte[1024];
             int count = 0;
             while ((count = in.read(buf)) >= 0) {
               out.write(buf, 0, count);
            }
            out.close();
            in.close();
        }
    }
}