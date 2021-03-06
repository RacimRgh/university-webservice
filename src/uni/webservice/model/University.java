/**
 * 
 */
package uni.webservice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description of a University for the SOAP webservice
 *
 */

public class University {

	/* Used for auto incrementing id */
	private static final AtomicInteger ID_FACTORY = new AtomicInteger(1);
	/* id of the university, auto generated */
	private final int id;
	/* short name of the university */
	private String name;
	/* full address of the university, retrived from OSM API */
	private String address;
	/* Geographical Latitude */
	private double position_x;
	/* Geographical longitude */
	private double position_y;
	public University() {
		/*
		 * Default constructor Takes no parameter, and incerements the ID
		 */
		super();
		id = ID_FACTORY.getAndIncrement();
	}

	public University(String name, double position_x, double position_y) {
		super();
		id = ID_FACTORY.getAndIncrement();
		this.name = name;
		this.position_x = position_x;
		this.position_y = position_y;
	}

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name + ", position_x=" + position_x + ", position_y=" + position_y
				+ "]";
	}

}
