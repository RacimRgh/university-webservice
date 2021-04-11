/**
 * 
 */
package uni.webservice.service;

/**
 * @author racim
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import uni.webservice.model.Speciality;
import uni.webservice.model.University;


@WebService(targetNamespace = "http://service.webservice.uni/",
	endpointInterface = "uni.webservice.service.UniManagement", 
	portName = "UniManagementImplPort", 
	serviceName = "UniManagementImplService")
public class UniManagementImpl implements UniManagement {
	
	private static ArrayList<Speciality> specs = new ArrayList<Speciality>();
	private static ArrayList<University> unis = new ArrayList<University>();
	private static Map<Speciality,University> uni_spec = new HashMap<Speciality, University>();
	private static int id_u = 0;
	private static int id_s = 0;
	  
	public int addSpeciality(Speciality s)
	{
		if(s.getId() == 0) {
			return -1;
		}
		++id_s;
		specs.add(s);
		return id_s;
	}
	  
	public int removeSpeciality(Speciality s)
	{
		return 1;
	}
	  
	  
	public int addUniversity(University u)
	{
		if(u.getId() == 0) {
			return -1;
		}
		++id_u;
		unis.add(u);
		return id_u;
	}
	  
	public int removeUniversity(University s)
	{
		return 1;
	}

}

