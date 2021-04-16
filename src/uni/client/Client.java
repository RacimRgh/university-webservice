package uni.client;

/**
 * @author Righi Racim and Ratovo Maeva
 * This class is used to test the rest webservice.
 * It provides a simple console UI that gives multiple choices to the user
 *
 */

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
	private static final String menu = "\n****************************************\n"
			+ "****************************************\n" + "1- Add a university.\n"
			+ "2- Map a speciality to a university.\n" + "3- Get a university.\n" + "4- Get a speciality.\n"
			+ "5- Get all universities.\n" + "6- Get all specialities.\n"
			+ "7- Get all specialities from a given university.\n"
			+ "8- Get address from a name.\n" + "10- Stop.\n" + "Your choice: ";

	private static final String webServiceUrl = "http://localhost:8080/UniversityManagement/api";
//  private static final String webServiceUrl = "http://localhost:8080/uni.restwebservice/api/university";
	private static final String osmUrl = "https://nominatim.openstreetmap.org/";
	private static final String req = "search?q=";
	private static final String form = "&format=xml";

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		Scanner in = new Scanner(System.in);

		int cergy = 0, descartes = 0, lyon = 0, uni = 0;
		String choice = "0";
		do {
			System.out.println(menu);
			choice = in.nextLine();
			switch (choice) {
			case "1": {
//				System.out.println("\n****Name of the university: ");
//				String name = in.nextLine();
//				uni = add(name);

				/*********************************************************************************
				 ************************** For testing only
				 ****************************************/
				cergy = add("Universite de Cergy Pontoise");
				descartes = add("Universite de Paris descartes");
				lyon = add("universite de Lyon 1");
				/*********************************************************************************/
				break;
			}
			case "2": {
//				System.out.println("\n****University id: ");
//				int id_u = in.nextInt();
//				System.out.println("\n****Speciality field: ");
//				System.out.println();
//				String field = in.nextLine();
//				System.out.println("\n****Speciality year (L1,L2,L3,M1,M2): ");
//				String year = in.nextLine();
//				System.out.println("\n****Speciality path: ");
//				String path = in.nextLine();
//				addSpeciality(id_u, year, field, path);
				/*********************************************************************************
				 ************************** For testing only
				 ****************************************/
				addSpeciality(cergy, "L3", "Informatique", "Informatique");
				addSpeciality(cergy, "M1", "Informatique", "IA");
				addSpeciality(descartes, "M1", "Informatique", "Data Science");
				addSpeciality(lyon, "L3", "Biologie", "Biologie");
				/*********************************************************************************/
				break;
			}
			case "3": {
//				System.out.println("\n****Getting university\n****University ID: ");
//				int id_u = in.nextInt();
//				get(id_u);
				/*********************************************************************************
				 ************************** For testing only
				 ****************************************/
				get(cergy);
				get(descartes);
				/*********************************************************************************/
				break;
			}
			case "4": {
				break;
			}
			case "5": {
				getAll();
				break;
			}
			case "6": {
				getAllSpecialities();
				break;
			}
			case "7": {
				getSpecialitiesFromUni(1);
				break;
			}
			case "8": {
				System.out.println("\n****Name: ");
				String name = in.nextLine();
				University s = new University(name, 0, 0);
				getAddress(s);
				System.out.println("Full address: " + s.getAddress());
				System.out.println("Latitude: " + s.getPosition_x());
				System.out.println("Longitude: " + s.getPosition_y());
				break;
			}
			case "10": {
				System.exit(1);
				break;
			}
			default: {
				System.out.println("Invalid choice, please repeat.\n");
				break;
			}
			}
		} while (choice != "10");
	}

	private static University getAddress(University s) throws IOException, ParserConfigurationException, SAXException {
		/**
		 * This functions takes a university's name, and retrieves it's full address +
		 * it's longitude and latitude using the Open Street Map Api and specifically
		 * Nominatim
		 * 
		 * @param s: The university for which we want to get the address
		 * @return The updated university, having the correct longitude, latitude and
		 *         address
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

	private static Integer add(String name) throws IOException, ParserConfigurationException, SAXException {
		/**
		 * This function add a university to the database using POST, and also call the
		 * function getAddress to retrieve the correct coordinates
		 * 
		 * @param name: name of the university to add
		 * @return null on failure, the added university id on success
		 */
		System.out.print("Adding " + name + "... ");
		WebClient c = WebClient.create(webServiceUrl).path("university");
		University s = new University(name, 0, 0);
		s = getAddress(s); // get the full address
		Response r = c.post(s);
		if (r.getStatus() == 400) {
			System.out.println("Error!");
			return null;
		}
		String uri = r.getHeaderString("Content-Location");
		System.out.println("OK.");
		return Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
	}

	private static University get(Integer id) {
		/**
		 * Function to get a university by it's id using GET http method
		 * 
		 * @param id: Id of the university
		 * @return The university
		 */
		System.out.print("Getting " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl).path("university/").path(id);
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
		 * Function to get all the universities on the database
		 * 
		 * @return List of universities
		 */
		System.out.println("Getting all...");
		WebClient c = WebClient.create(webServiceUrl).path("university");
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
		 * Function to add a speciality to the database and map it to a university given
		 * it's id
		 * 
		 * @param id_u:  id of the university to map to
		 * @param year:  Level of the speciality (L1,L2,L3,M1,M2)
		 * @param field: Field like Computer Science, Biology...
		 * @param path:  Path like IA, Data Science, Cellular biology...
		 * @return null on failure, the speciality id on success
		 */
		System.out.print("Adding " + field + "... ");
		WebClient c = WebClient.create(webServiceUrl).path("university/").path(id_u).path("/speciality");
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
		 * Get all the specialities from a given university
		 * 
		 * @param id: Id of the university to get specialities from
		 * @return List of specialities
		 */
		System.out.print("Getting specialities from: " + id + "... ");
		WebClient c = WebClient.create(webServiceUrl).path("university/").path(id).path("/speciality");
		ArrayList<Speciality> l = (ArrayList<Speciality>) c.getCollection(Speciality.class);
		for (Speciality s : l) {
			System.out.println(s.toString());
		}
		System.out.println("OK.");
		return l.toArray(new Speciality[l.size()]);
	}

	public static Speciality[] getAllSpecialities() {
		/**
		 * Get all specialities from all universities
		 * 
		 * @return List of specialities
		 */
		System.out.println("Getting all...");
		WebClient c = WebClient.create(webServiceUrl).path("speciality");
		ArrayList<Speciality> l = (ArrayList<Speciality>) c.getCollection(Speciality.class);
		for (Speciality s : l) {
			System.out.println(s.toString());
		}
		System.out.println("OK.");
		return l.toArray(new Speciality[l.size()]);
	}
}
