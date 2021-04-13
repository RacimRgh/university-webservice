package uni.client;

import java.util.ArrayList;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import uni.restwebservice.data.University;

public class Client {

  private static String webServiceUrl = "http://localhost:8080/uni.restwebservice/api/university";

  public static void main(String[] args) {
	  
//    University cergy = new University("Cergy", 0, 0);
//    add(cergy.getName(), cergy.getPosition_x(), cergy.getPosition_y());
//    University descartes = new University("Descartes", 1, 1);
//    add(descartes.getName(), descartes.getPosition_x(), descartes.getPosition_y());
//    University lyon = new University("Lyon", 2, 2);
//    add(lyon.getName(), lyon.getPosition_x(), lyon.getPosition_y());
    
    int cergy = add("Cergy", 0, 0);
    int descartes = add("Descartes", 1, 1);
    int lyon = add("Lyon", 2, 2);
    get(cergy);
    get(descartes);
    get(lyon);

//    getAll();  
  }

  private static Integer add(String name, double x, double y) {
    System.out.print("Adding " + name + "... ");
    WebClient c = WebClient.create(webServiceUrl);
    University s = new University(name, x, y);
    Response r = c.post(s);
    if(r.getStatus() == 400) {
      System.out.println("Oops!");
      return null;
    }
    String uri = r.getHeaderString("Content-Location");
    System.out.println("OK.");
//    return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
    return s.getId();
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
