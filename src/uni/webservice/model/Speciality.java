/**
 * 
 */
package uni.webservice.model;

/**
 * @author racim
 *
 */
public class Speciality {
	private int id;
	private int year;
	private String field;
	private String course;
	public int getYear() {
		return year;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	

}
