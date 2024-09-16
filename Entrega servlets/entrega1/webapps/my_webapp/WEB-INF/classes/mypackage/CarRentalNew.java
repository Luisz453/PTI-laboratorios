package mypackage;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class CarRentalNew extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String co2 = req.getParameter("co2_rating");
    String engine = req.getParameter("sub_model_vehicle");
    String dies = req.getParameter("dies_lloguer");
    String unitats = req.getParameter("num_vehicles");
    String discount = req.getParameter("descompte");
    
    JSONObject newrentals = new JSONObject();
    newrentals.put("Rating", co2);
    newrentals.put("Engine", engine);
    newrentals.put("Number of days", dies);
    newrentals.put("Number of units", unitats);
    newrentals.put("Discount", discount);

    //Anterior
    /*try{
      File file = new File("rentals.json");
      if(!file.exists()) file.createNewFile();
      FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(rentals.toJSONString());
      bw.close();
      fw.close();

    }catch(IOException e2){
      e2.printStackTrace();
    }*/

    //NOU
    JSONArray lloguers = null;
    try (FileReader reader = new FileReader("rentals.json")) {
      JSONParser parser = new JSONParser();
      lloguers = (JSONArray) parser.parse(reader);
    }
    catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    if (lloguers == null) lloguers = new JSONArray();
    lloguers.add(newrentals);
     try (FileWriter fileWriter = new FileWriter("rentals.json")) {
        fileWriter.write(lloguers.toJSONString());
        fileWriter.flush();
      } 
      catch (IOException e) {
        e.printStackTrace();
      }

    cont ++;
    out.println("<html><big>CO2 Rating: "+ co2 + "</big><br>"+
                "<html><big>Engine: "+ engine + "</big><br>"+
                "<html><big>Number of days: "+ dies + "</big><br>"+
                "<html><big>Number of units: "+ unitats + "</big><br>"+
                "<html><big>Discount: "+ discount + "</big><br>"+
                cont + " Accesos desde su carga.</html>");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
