
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

@XmlRootElement(name = "addSpecialityResponse", namespace = "http://service.webservice.uni/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addSpecialityResponse", namespace = "http://service.webservice.uni/")

public class AddSpecialityResponse {

    @XmlElement(name = "id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int newId)  {
        this.id = newId;
    }

}

