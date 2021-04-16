/**
 * 
 */
package uni.webservice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description of a Speciality for the SOAP webservice
 *
 */
public class Speciality {
	/* Used for auto incrementing id */
	private static final AtomicInteger ID_FACTORY = new AtomicInteger(1);
	/* Speciality's id */
	private final int id;
	/* Year: L1,L2,L3,M1,M2 */
	private String year;
	/* Field of the speciality (ex: computer science) */
	private String field;
	/* Path in the field (ex: IA, Emebedded...) */
	private String path;

	public Speciality() {
		/*
		 * Default constructor Takes no parameter, and incerements the ID
		 */
		super();
		id = ID_FACTORY.getAndIncrement();
	}

	public Speciality(String year, String field, String path) {
		super();
		id = ID_FACTORY.getAndIncrement();
		this.year = year;
		this.field = field;
		this.path = path;
	}

	public String getYear() {
		return year;
	}

	public int getId() {
		return id;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getpath() {
		return path;
	}

	public void setpath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", year=" + year + ", field=" + field + ", path=" + path + "]";
	}

}
