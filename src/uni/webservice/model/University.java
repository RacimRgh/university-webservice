/**
 * 
 */
package uni.webservice.model;

/**
 * @author racim
 *
 */
import java.util.ArrayList;

public class University {
	
	private int id;
	private String name;
	private ArrayList<Speciality> specialities;
	double position_x;
	double position_y;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Speciality> getSpecialities() {
		return specialities;
	}
	public void setSpecialities(ArrayList<Speciality> specialities) {
		this.specialities = specialities;
	}
	public double getPosition_x() {
		return position_x;
	}
	public void setPosition_x(double position_x) {
		this.position_x = position_x;
	}
	public double getPosition_y() {
		return position_y;
	}
	public void setPosition_y(double position_y) {
		this.position_y = position_y;
	}
	
	
}
