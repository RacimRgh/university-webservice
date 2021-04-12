/**
 * 
 */
package uni.webservice.service;

/**
 * @author racim
 *
 */
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import uni.webservice.model.Speciality;
import uni.webservice.model.University;

@WebService(name = "UniManagement", targetNamespace = "http://service.webservice.uni/")
public interface UniManagement {

	@WebMethod(operationName = "addSpeciality", action = "urn:AddSpeciality")
	@WebResult(name = "id")
	public int addSpeciality(@WebParam(name = "speciality") Speciality s);
	
	
	@WebMethod(operationName = "removeSpeciality", action = "urn:RemoveSpeciality")
	@WebResult(name = "id")
	public int removeSpeciality(@WebParam(name = "speciality") int id_s);
	
	@WebMethod(operationName = "addUniversity", action = "urn:AddUniversity")
	@WebResult(name = "id")
	public int addUniversity(@WebParam(name = "university") University s);
	
	
	@WebMethod(operationName = "removeUniversity", action = "urn:RemoveUniversity")
	@WebResult(name = "id")
	public int removeUniversity(@WebParam(name = "university") int id_s);
	
	
	@WebMethod(operationName = "addSpecialitytoUni", action = "urn:AddSpecialitytoUni")
	@WebResult(name = "id")
	public int addSpecialitytoUni(@WebParam(name="speciality") Speciality s, @WebParam(name="university") University u);
	
}
