
package uni.webservice.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18
 * Mon Apr 12 18:37:33 CEST 2021
 * Generated source version: 2.7.18
 */

@XmlRootElement(name = "addSpeciality", namespace = "http://service.webservice.uni/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addSpeciality", namespace = "http://service.webservice.uni/")

public class AddSpeciality {

    @XmlElement(name = "speciality")
    private uni.webservice.model.Speciality speciality;

    public uni.webservice.model.Speciality getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(uni.webservice.model.Speciality newSpeciality)  {
        this.speciality = newSpeciality;
    }

}

