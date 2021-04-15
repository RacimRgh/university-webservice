package uni.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uni.restwebservice.data.University;

public class Client {

  private static String webServiceUrl = "http://localhost:8080/UniversityManagement/api/university";
//  private static String webServiceUrl = "http://localhost:8080/uni.restwebservice/api/university";
  private static String osmUrl = "https://nominatim.openstreetmap.org/";
  private static String req = "search?q=";
  private static String form = "&format=xml";

  public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
	  
//    University cergy = new University("Cergy", 0, 0);
//    add(cergy.getName(), cergy.getPosition_x(), cergy.getPosition_y());
//    University descartes = new University("Descartes", 1, 1);
//    add(descartes.getName(), descartes.getPosition_x(), descartes.getPosition_y());
//    University lyon = new University("Lyon", 2, 2);
//    add(lyon.getName(), lyon.getPosition_x(), lyon.getPosition_y());
    
    int cergy = add("Universite de Cergy Pontoise", 0, 0);
    int descartes = add("Universite de Paris descartes", 1, 1);
    int lyon = add("universite de Lyon 1", 2, 2);
    get(cergy);
    get(descartes);
    get(lyon);

//    getAll();  
  }
  
  private static University getAddress(University s) throws IOException, ParserConfigurationException, SAXException
  {
	  String name = s.getName().replace(" ", "+");
	  // Create a neat value object to hold the URL
	  URL url = new URL(osmUrl+req+name+form);
	  
	  // Open a connection(?) on the URL(??) and cast the response(???)
	  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	  
	  // Now it's "open", we can set the request method, headers etc.
	  connection.setRequestProperty("accept", "application/json");
	  
	  // This line makes the request
	  InputStream responseStream = connection.getInputStream();
	  
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(responseStream);
      NodeList nodeList = document.getElementsByTagName("place");
      if(nodeList.getLength() > 0)
      {
    	  String display_name = nodeList.item(0).getAttributes().getNamedItem("display_name").getNodeValue();
    	  Double lat = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("lat").getNodeValue());
    	  Double lon = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("lon").getNodeValue());
    	  s.setAddress(display_name);
    	  s.setPosition_x(lat);
    	  s.setPosition_y(lon);
      }
      return s;
  }
  
  private static Integer add(String name, double x, double y) throws IOException, ParserConfigurationException, SAXException {
    System.out.print("Adding " + name + "... ");
    WebClient c = WebClient.create(webServiceUrl);
    University s = new University(name, x, y);
    s = getAddress(s);
    Response r = c.post(s);
    if(r.getStatus() == 400) {
      System.out.println("Oops!");
      return null;
    }
    String uri = r.getHeaderString("Content-Location");
    System.out.println("OK.");
    return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
  }

  private static University get(Integer id) {
    System.out.print("Getting " + id + "... ");
    WebClient c = WebClient.create(webServiceUrl).path(id);
    University s = null;
    try {
    	s = c.get(University.class);
    	System.out.println(s.toString());
    } catch(NotFoundException e) {
    	System.out.println("Oops!");
    }
    return s;
  }
 
  private static University[] getAll() {
    System.out.println("Getting all...");
    WebClient c = WebClient.create(webServiceUrl);
    ArrayList<University> l = (ArrayList<University>) c.getCollection(University.class);
    for(University s : l) {
      System.out.println(s.toString());
    }
    System.out.println("OK.");
    return l.toArray(new University[l.size()]);
  }

}
