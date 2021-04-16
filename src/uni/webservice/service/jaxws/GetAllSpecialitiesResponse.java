
package uni.webservice.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18
 * Fri Apr 16 16:37:44 CEST 2021
 * Generated source version: 2.7.18
 */

@XmlRootElement(name = "getAllSpecialitiesResponse", namespace = "http://service.webservice.uni/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllSpecialitiesResponse", namespace = "http://service.webservice.uni/")

public class GetAllSpecialitiesResponse {

    @XmlElement(name = "specialities")
    private uni.webservice.model.Speciality[] specialities;

    public uni.webservice.model.Speciality[] getSpecialities() {
        return this.specialities;
    }

    public void setSpecialities(uni.webservice.model.Speciality[] newSpecialities)  {
        this.specialities = newSpecialities;
    }

}
