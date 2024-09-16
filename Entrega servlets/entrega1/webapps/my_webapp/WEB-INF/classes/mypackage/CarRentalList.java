package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CarRentalList extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    cont ++;
    out.println("<html><big><b>Rental List: </b></big><br> </html>");
    //File arxiu = new File("rentals.json");
    //if(!arxiu.exists()) out.println("<html>You don't have any rentals<br> </html>");
    JSONParser parser = new JSONParser();
    try(Reader reader = new FileReader("rentals.json")) {

           Object o = null;
            try {
                o = parser.parse(reader);
            } catch (ParseException e) {
                e.printStackTrace();
            }
          JSONArray rentals = (JSONArray) o;
          
          
          for (Object obj : rentals) {
            JSONObject lloguer = (JSONObject) obj;
            String num_unitats = (String) lloguer.get("Number of units");
            String descompte = (String) lloguer.get("Discount");
            String rating = (String) lloguer.get("Rating");
            String num_dies = (String) lloguer.get("Number of days");
            String engine = (String) lloguer.get("Engine");
            out.println("<html><big>CO2 Rating: "+ rating + "</big><br>"+
                "<html><big>Engine: "+ engine + "</big><br>"+
                "<html><big>Number of days: "+ num_dies + "</big><br>"+
                "<html><big>Number of units: "+ num_unitats + "</big><br>"+
                "<html><big>Discount: "+ descompte + "</big><br><br></html>");
          }
      

    }catch(IOException e2){
      e2.printStackTrace();
    }

  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
    
  }
}
