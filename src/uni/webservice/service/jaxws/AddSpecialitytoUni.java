
package uni.webservice.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.18
 * Mon Apr 12 13:02:50 CEST 2021
 * Generated source version: 2.7.18
 */

@XmlRootElement(name = "addSpecialitytoUni", namespace = "http://service.webservice.uni/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addSpecialitytoUni", namespace = "http://service.webservice.uni/", propOrder = {"speciality", "university"})

public class AddSpecialitytoUni {

    @XmlElement(name = "speciality")
    private uni.webservice.model.Speciality speciality;
    @XmlElement(name = "university")
    private uni.webservice.model.University university;

    public uni.webservice.model.Speciality getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(uni.webservice.model.Speciality newSpeciality)  {
        this.speciality = newSpeciality;
    }

    public uni.webservice.model.University getUniversity() {
        return this.university;
    }

    public void setUniversity(uni.webservice.model.University newUniversity)  {
        this.university = newUniversity;
    }

}
