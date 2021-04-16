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

	@WebMethod(operationName = "addUniversity", action = "urn:AddUniversity")
	@WebResult(name = "id")
	public int addUniversity(@WebParam(name = "university") University s);

	@WebMethod(operationName = "getUniversity", action = "urn:GetUniversity")
	@WebResult(name = "university")
	public University getUniversity(@WebParam(name = "id") int id);

	@WebMethod(operationName = "removeUniversity", action = "urn:RemoveUniversity")
	@WebResult(name = "id")
	public int removeUniversity(@WebParam(name = "university") int id_s);

	@WebMethod(operationName = "addSpeciality", action = "urn:AddSpeciality")
	@WebResult(name = "id")
	public int addSpeciality(@WebParam(name = "id") int id, @WebParam(name = "speciality") Speciality s);

	@WebMethod(operationName = "getSpeciality", action = "urn:GetSpeciality")
	@WebResult(name = "university")
	public Speciality getSpeciality(@WebParam(name = "id") int id);

	@WebMethod(operationName = "getSpecialitytoUni", action = "urn:GetSpecialitytoUni")
	@WebResult(name = "id")
	public Speciality getSpecialityFromUniversity(@WebParam(name = "id_u") int id_u, @WebParam(name = "id_s") int id_s);

	@WebMethod(operationName = "removeSpeciality", action = "urn:RemoveSpeciality")
	@WebResult(name = "id")
	public int removeSpeciality(@WebParam(name = "id_u") int id_u, @WebParam(name = "id_s") int id_s);

	@WebMethod(operationName = "addSpecialitytoUni", action = "urn:AddSpecialitytoUni")
	@WebResult(name = "id")
	public int addSpecialitytoUni(@WebParam(name = "speciality") Speciality s,
			@WebParam(name = "university") University u);

	@WebMethod(operationName = "getAllUniversities", action = "urn:GetAllUniversities")
	@WebResult(name = "universities")
	public University[] getAllUniversities();

	@WebMethod(operationName = "getAllSpecialities", action = "urn:GetAllSpecialities")
	@WebResult(name = "specialities")
	public Speciality[] getAllSpecialities();

	@WebMethod(operationName = "getSpecialitiesFromUni", action = "urn:GetSpecialitiesFromUni")
	@WebResult(name = "specialities")
	public Speciality[] getSpecialitiesFromUni(int id_u);

}
