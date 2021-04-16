/**
 * 
 */
package uni.restwebservice.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import uni.restwebservice.data.Speciality;
import uni.restwebservice.data.University;
import uni.restwebservice.service.UniversityManagementRest;

/**
 * REST methods for university management
 *
 */
@Path("/")
public class UniversityResource {
	UniversityManagementRest service = new UniversityManagementRest();

	@Context
	UriInfo uriInfo;

	@POST
	@Path("university")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addUniversity(University u) {
		/**
		 * This function add a university to the database using POST
		 * 
		 * @param u: University object to add
		 * @return request's response status
		 */
		University uni = service.addUniversity(u);
		if (uni == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		URI uri = uriInfo.getRequestUri();
		String newUri = uri.getPath() + "/" + uni.getId();
		return Response.status(Response.Status.CREATED).contentLocation(uri.resolve(newUri)).build();
	}

	@GET
	@Path("university/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUniversity(@PathParam("id") int id) {
		/**
		 * Function to get a university by it's id using GET http method
		 * 
		 * @param id: Id of the university
		 * @return http request's response
		 */
		University uni = service.getUniversity(id);
		if (uni == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(uni).links(link).build();
	}

	@DELETE
	@Path("university/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteUniversity(@PathParam("id") int id) {
		/**
		 * Function to delete a university by it's id using DELETE http method
		 * 
		 * @param id: Id of the university
		 * @return http request's response
		 */
		if (service.removeUniversity(id) == -1) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	@POST
	@Path("university/{id}/speciality")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addSpeciality(@PathParam("id") int id, Speciality s) {
		/**
		 * Function to add a speciality to the database and map it to a university given
		 * it's id
		 * 
		 * @param id: id of the university to map to
		 * @param s:  Speciality object to be added
		 */
		Speciality spec = service.addSpeciality(id, s);
		if (spec == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		URI uri = uriInfo.getRequestUri();
		String newUri = uri.getPath() + "/" + spec.getId();
		return Response.status(Response.Status.CREATED).contentLocation(uri.resolve(newUri)).build();
	}

	@GET
	@Path("speciality")
	@Produces(MediaType.APPLICATION_XML)
	public Response getSpeciality(@PathParam("id") int id) {
		/**
		 * Get a speciality given it's id
		 * 
		 * @param id: id of the speciality
		 * @return A speciality
		 */
		Speciality spec = service.getSpeciality(id);
		if (spec == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(spec).links(link).build();
	}

	@GET
	@Path("university/{id}/speciality/{id_s}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getSpecialityFromUni(@PathParam("id") int id, @PathParam("id_s") int id_s) {
		/**
		 * Get a specific speciality from a university
		 * 
		 * @param id:   Id of the university to get specialities from
		 * @param id_s: id of the speciality
		 */
		Speciality uni = service.getSpecialityFromUniversity(id, id_s);
		if (uni == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.status(Response.Status.OK).entity(uni).links(link).build();
	}

	@DELETE
	@Path("university/{id}/speciality/{id_s}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteSpeciality(@PathParam("id") int id, @PathParam("id_s") int id_s) {
		if (service.removeSpeciality(id, id_s) == -1) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("university")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllUniversities() {
		/**
		 * Function to get all the universities on the database
		 */
		return Response.status(Response.Status.OK).entity(service.getAllUniversities()).build();
	}

	@GET
	@Path("speciality")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllSpecialities() {
		/**
		 * Get all specialities from all universities
		 */
		return Response.status(Response.Status.OK).entity(service.getAllSpecialities()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("university/{id}/speciality")
	public Response getAllSpecialitiesFromUni(@PathParam("id") int id) {
		/**
		 * Get all the specialities from a given university
		 * 
		 * @param id: Id of the university to get specialities from
		 */
		return Response.status(Response.Status.OK).entity(service.getSpecialitiesFromUni(id)).build();
	}
}
