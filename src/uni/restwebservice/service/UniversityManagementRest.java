/**
 * 
 */
package uni.restwebservice.service;

import java.util.HashMap;
import java.util.Map;

import uni.restwebservice.data.Speciality;
import uni.restwebservice.data.University;


/**
 * @author racim
 *
 */
public class UniversityManagementRest {
	
	/**
	 * unis is a {@link Map} collection that contains {@link Integer} as key 
	 * and {@link University} as value.
	 */
	private static Map<Integer,University> unis = new HashMap<Integer, University>();
	
	/**
	 * specs is a {@link Map} collection that contains {@link Integer} as key 
	 * and {@link Speciality} as value.
	 */
	private static Map<Integer,Speciality> specs = new HashMap<Integer, Speciality>();
	
	/**
	 *  is a {@link Map} collection that contains {@link Speciality} as key 
	 * and {@link University} as value.
	 */
	private static Map<Speciality,University> fac = new HashMap<Speciality, University>();
	
	/**
	 * id_u is the static id for the universities (increments on add)
	 * id_s is the static id for the specialities (increments on add)
	 */
	private static int id_u = 0;
	private static int id_s = 0;
	  
	public Speciality addSpeciality(Speciality s){
		/** 
		 * @param
		 * @return
		 * */
		if(s.getId() == 0) {
			return null;
		}
		++id_s;
		specs.put(s.getId(), s);
		return s;
	}
	  
	public int removeSpeciality(int id) {
		/** 
		 * @param
		 * @return
		 * */
		if(specs.get(id)==null) {
			return -1;
		}
		specs.remove(id);
		return id;
	}
	  
	  
	public University addUniversity(University u){
		/** 
		 * @param
		 * @return
		 * */
		if(u.getId() == 0) {
			return null;
		}
		++id_u;
		unis.put(u.getId(), u);
		return u;
	}
	
	
	public University getUniversity(int id) {
	    return unis.get(id);
	  }
	  
	public int removeUniversity(int id) {
		/** 
		 * @param
		 * @return
		 * */
		if(unis.get(id)==null) {
			return -1;
		}
		unis.remove(id);
		return id;
	}
	
	public int addSpecialitytoUni(Speciality s, University u) {
		/** 
		 * @param
		 * @return
		 * */
		if(s.getId() == 0 || u.getId() == 0) {
			return -1;
		}
		
		/* If the university isn't registered, we add it */
		if(unis.get(u.getId()) == null) {
			unis.put(u.getId(), u);
			id_u++;
		}
		
		/* If the speciality isn't registered, we add it */
		if(specs.get(s.getId()) == null) {
			specs.put(s.getId(), s);
			id_s++;
		}
		/* Map the speciality to the university */
		fac.put(s, u);
		return 1;
	}
}

