/**
 * 
 */
package uni.webservice.service;

import java.util.ArrayList;

/**
 * @author racim
 *
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import uni.webservice.model.Speciality;
import uni.webservice.model.University;

@WebService(targetNamespace = "http://service.webservice.uni/", endpointInterface = "uni.webservice.service.UniManagement", portName = "UniManagementImplPort", serviceName = "UniManagementImplService")
public class UniManagementImpl implements UniManagement {

	/**
	 * unis is a {@link Map} collection that contains {@link Integer} as key and
	 * {@link University} as value.
	 */
	private static Map<Integer, University> unis = new HashMap<Integer, University>();

	/**
	 * specs is a {@link Map} collection that contains {@link Integer} as key and
	 * {@link Speciality} as value.
	 */
	private static Map<Integer, Speciality> specs = new HashMap<Integer, Speciality>();

	/**
	 * is a {@link Map} collection that contains {@link Integer} as key, which is
	 * the id of the speciality and {@link Integer} as value, which is the id of the
	 * university
	 */
	private static Map<Integer, Integer> fac = new HashMap<Integer, Integer>();

	public int addUniversity(University u) {
		/**
		 * This function add a university to the database
		 * 
		 * @param name: name of the university to add
		 * @return null on failure, the added university id on success
		 */
		if (u.getId() == 0) {
			return 0;
		}
		unis.put(u.getId(), u);
		return u.getId();
	}

	public University getUniversity(int id) {
		/**
		 * Function to get a university by it's id
		 * 
		 * @param id: Id of the university
		 * @return The university
		 */
		return unis.get(id);
	}

	public int removeUniversity(int id) {
		/**
		 * Function to delete a university by it's id
		 * 
		 * @param id: Id of the university
		 * @return http request's response
		 */
		if (unis.get(id) == null) {
			return -1;
		}
		unis.remove(id);
		return id;
	}

	public int addSpeciality(int id, Speciality s) {
		/**
		 * Function to add a speciality to the database and map it to a university given
		 * it's id
		 * 
		 * @param id: id of the university to map to
		 * @param s:  Speciality object to be added
		 */
		if (s.getId() == 0) {
			return 0;
		}
		specs.put(s.getId(), s); // we add the speciality to the hashmap
		fac.put(s.getId(), id); // we map the speciality id to a university id
		return s.getId();
	}

	public Speciality getSpeciality(int id) {
		/**
		 * Get a speciality given it's id
		 * 
		 * @param id: id of the speciality
		 * @return A speciality
		 */
		return specs.get(id);
	}

	public Speciality getSpecialityFromUniversity(int id, int id_s) {
		/**
		 * Get a specific speciality from a university
		 * 
		 * @param id:   Id of the university to get specialities from
		 * @param id_s: id of the speciality
		 */
		if (!fac.containsKey(id_s) || !fac.containsValue(id))
			return null;

		return specs.get(id_s);
	}

	public int removeSpeciality(int id_u, int id_s) {
		/**
		 * @param
		 * @return
		 */
		if (specs.get(id_s) == null || unis.get(id_u) == null) {
			return -1;
		}
		specs.remove(id_s);
		fac.remove(id_s);
		return id_s;
	}

	public int addSpecialitytoUni(Speciality s, University u) {
		/**
		 * @param
		 * @return
		 */
		if (s.getId() == 0 || u.getId() == 0) {
			return -1;
		}

		/* If the university isn't registered, we add it */
		if (unis.get(u.getId()) == null) {
			unis.put(u.getId(), u);
		}

		/* If the speciality isn't registered, we add it */
		if (specs.get(s.getId()) == null) {
			specs.put(s.getId(), s);
		}
		/* Map the speciality to the university */
		fac.put(s.getId(), u.getId());
		return 1;
	}

	public University[] getAllUniversities() {
		/**
		 * Function to get all the universities on the database
		 */
		Set<Integer> id_u = unis.keySet();
		University[] u = new University[id_u.size()];
		int i = 0;
		for (Integer id : id_u) {
			u[i] = unis.get(id);
			i++;
		}
		return u;
	}

	public Speciality[] getAllSpecialities() {
		/**
		 * Get all specialities from all universities
		 */
		Set<Integer> id_s = specs.keySet();
		Speciality[] s = new Speciality[id_s.size()];
		int i = 0;
		for (Integer id : id_s) {
			s[i] = specs.get(id);
			i++;
		}
		return s;
	}

	public Speciality[] getSpecialitiesFromUni(int id_u) {
		/**
		 * Get all the specialities from a given university
		 * 
		 * @param id: Id of the university to get specialities from
		 */
		ArrayList<Speciality> sl = new ArrayList<Speciality>();
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : fac.entrySet()) {
			System.out.println(entry.getValue());
			if (entry.getValue().equals(id_u)) {
				sl.add(specs.get(entry.getKey()));
				i++;
			}
		}
		Speciality[] s = new Speciality[i];
		for (int j = 0; j < sl.size(); j++) {
			s[j] = sl.get(j);
		}
		return s;
	}
}
