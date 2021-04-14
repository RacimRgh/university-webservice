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
	 *  is a {@link Map} collection that contains {@link Integer} as key, which is the id of the speciality 
	 * and {@link Integer} as value, which is the id of the university
	 */
	private static Map<Integer,Integer> fac = new HashMap<Integer, Integer>();
	  
	public University addUniversity(University u){
		/** 
		 * @param
		 * @return
		 * */
		if(u.getId() == 0) {
			return null;
		}
		unis.put(u.getId(), u);
		return u;
	}
	
	
	public University getUniversity(int id) {
		/** 
		 * @param
		 * @return
		 * */
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
	  
	public Speciality addSpeciality(int id, Speciality s){
		/** 
		 * @param
		 * @return
		 * */
		if(s.getId() == 0) {
			return null;
		}
		specs.put(s.getId(), s);
		fac.put(id, s.getId());
		return s;
	}
	
	public Speciality getSpecialityFromUniversity(int id, int id_s) {
		/** 
		 * @param
		 * @return
		 * */
		if (!fac.containsKey(id_s) || !fac.containsValue(id))
			return null;
		
	    return specs.get(id_s);
	  }
	  
	public int removeSpeciality(int id_u, int id_s) {
		/** 
		 * @param
		 * @return
		 * */
		if(specs.get(id_s)==null || unis.get(id_u) == null) {
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
		 * */
		if(s.getId() == 0 || u.getId() == 0) {
			return -1;
		}
		
		/* If the university isn't registered, we add it */
		if(unis.get(u.getId()) == null) {
			unis.put(u.getId(), u);
		}
		
		/* If the speciality isn't registered, we add it */
		if(specs.get(s.getId()) == null) {
			specs.put(s.getId(), s);
		}
		/* Map the speciality to the university */
		fac.put(s.getId(), u.getId());
		return 1;
	}
}

