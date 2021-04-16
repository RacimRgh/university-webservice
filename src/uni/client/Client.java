package uni.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.jaxrs.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uni.restwebservice.data.Speciality;
import uni.restwebservice.data.University;

public class Client {
	private static final String menu ="\n****************************************\n"
			+ "****************************************\n"
			+ "1- Add a university.\n"
			+ "2- Map a speciality to a university.\n"
			+ "3- Get a university.\n"
			+ "4- Get a speciality.\n"
			+ "5- Get all universities.\n"
			+ "6- Get all specialities.\n"
			+ "7- Get all specialities from a given university.\n"
			+ "10- Stop.\n"
			+ "Your choice: ";

	private static final String webServiceUrl = "http://localhost:8080/UniversityManagement/api/university";
//  private static final String webServiceUrl = "http://localhost:8080/uni.restwebservice/api/university";
	private static final String osmUrl = "https://nominatim.openstreetmap.org/";
	private static final String req = "search?q=";
	private static final String form = "&format=xml";

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		Scanner in = new Scanner(System.in);
		
		int cergy=0, descartes=0, lyon=0;
		String choice = "0";
		do {
			System.out.println(menu);
			choice = in.nextLine();
			switch(choice) {
			case "1":
			{
				cergy = add("Universite de Cergy Pontoise", 0, 0);
				descartes = add("Universite de Paris descartes", 1, 1);
				lyon = add("universite de Lyon 1", 2, 2);
				break;
			}
			case "2":
			{
				addSpeciality(cergy, "L3", "Informatique", "Informatique");
				addSpeciality(cergy, "M1", "Informatique", "IA");
				addSpeciality(descartes, "M1", "Informatique", "Data Science");
				addSpeciality(lyon, "L3", "Biologie", "Biologie");
				break;
			}
			case "3":
			{
				get(cergy);
				get(descartes);
				break;
			}
			case "4":
			{
				break;
			}
			case "5":
			{
				getAll();
				break;
			}
			case "6":
			{
				break;
			}
			case "7":
			{
				getSpecialitiesFromUni(1);
				break;
			}
			case "8":
			{
				break;
			}
			case "10":
			{
				System.exit(1);
				break;
			}
			default:{
				System.out.println("Invalid choice, please repeat.\n");
				break;
			}
			}
		}while(choice!="10");
	}

	private static University getAddress(University s) throws IOException, ParserConfigurationException, SAXException {
		/**
		 * @param
		 * @return
		 */
		String name = s.getName().replace(" ", "+");
		// Create a URL object to hold the URL
		URL url = new URL(osmUrl + req + name + form);

		// Open a connection on the OSM URL and cast the response
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Now it's "open", we can set the request method and headers
		connection.setRequestProperty("accept", "application/json");

		// Make the request
		InputStream responseStream = connection.getInputStream();

		// Parse the XML response
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(responseStream);
		// The <place> tag contains the address data
		NodeList nodeList = document.getElementsByTagName("place");
		if (nodeList.getLength() > 0) {
			String display_name = nodeList.item(0).getAttributes().getNamedItem("display_name").getNodeValue();
			Double lat = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("lat").getNodeValue());
			Double lon = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("lon").getNodeValue());
			s.setAddress(display_name);
			s.setPosition_x(lat);
			s.setPosition_y(lon);
		}
		return s;
	}

	private static Integer add(String name, double x, double y)
			throws IOException, ParserConfigurationException, SAXException {
		/**
		 * @param
		 * @return
		 */
		System.out.print("Adding " + name + "... ");
		WebClient c = WebClient.create(webServiceUrl);
		University s = new University(name, x, y);
		s = getAddress(s);
		Response r = c.post(s);
		if (r.getStatus() == 400) {
			System.out.println("Oops!");
			return null;
		}
		String uri = r.getHeaderString("Content-Location");
		System.out.println("OK.");
		return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
	}

	private static University get(Integer id) {
		/**
		 * @param
		 * @return
		 */
		System.out.print("Getting " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl).path(id);
		University s = null;
		try {
			s = c.get(University.class);
			System.out.println(s.toString());
		} catch (NotFoundException e) {
			System.out.println("Oops!");
		}
		return s;
	}

	private static University[] getAll() {
		/**
		 * @param
		 * @return
		 */
		System.out.println("Getting all...");
		WebClient c = WebClient.create(webServiceUrl);
		ArrayList<University> l = (ArrayList<University>) c.getCollection(University.class);
		for (University s : l) {
			System.out.println(s.toString());
		}
		System.out.println("OK.");
		return l.toArray(new University[l.size()]);
	}
	
	private static Integer addSpeciality(int id_u, String year, String field, String path)
			throws IOException, ParserConfigurationException, SAXException {
		/**
		 * @param
		 * @return
		 */
		System.out.print("Adding " + field + "... ");
		WebClient c = WebClient.create(webServiceUrl).path(id_u).path("/speciality");
		Speciality s = new Speciality(year, field, path);
		Response r = c.post(s);
		if (r.getStatus() == 400) {
			System.out.println("Oops!");
			return null;
		}
		String uri = r.getHeaderString("Content-Location");
		System.out.println("OK.");
		return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
	}
	
	private static Speciality[] getSpecialitiesFromUni(Integer id) {
		/**
		 * @param
		 * @return
		 */
		System.out.print("Getting specialities from: " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl).path(id).path("/speciality");	
		ArrayList<Speciality> l = (ArrayList<Speciality>) c.getCollection(Speciality.class);
		for (Speciality s : l) {
			System.out.println(s.toString());
		}
		System.out.println("OK.");
		return l.toArray(new Speciality[l.size()]);
	}
}
