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
 * @author racim
 *
 */
@Path("/university")
public class UniversityResource {
	UniversityManagementRest service = new UniversityManagementRest();
	
	@Context
	UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addUniversity(University u)
	{
		/** 
		 * @param
		 * @return
		 * */
		University uni = service.addUniversity(u);
	    if(uni == null) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    URI uri = uriInfo.getRequestUri();
	    String newUri = uri.getPath() + "/" + uni.getId();
		return Response.status(Response.Status.CREATED)
                .contentLocation(uri.resolve(newUri))
                .build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUniversity(@PathParam("id") int id) {
		/** 
		 * @param
		 * @return
		 * */
		University uni = service.getUniversity(id);
	    if(uni== null) {
	    	return Response.status(Response.Status.NOT_FOUND).build();
	    }
	    Link link = Link.fromUri(uriInfo.getRequestUri())
	                    .rel("self")
	                    .type("application/xml")
	                    .build();
	    return Response.status(Response.Status.OK)
	                   .entity(uni)
	                   .links(link)
	                   .build();
	  }
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteUniversity(@PathParam("id") int id)
	{
		if(service.removeUniversity(id) == -1) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).build();
	}
	
	@POST
	@Path("/{id}/speciality")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addSpeciality(@PathParam("id") int id, Speciality s)
	{
		/** 
		 * @param
		 * @return
		 * */
		Speciality spec = service.addSpeciality(id, s);
	    if(spec == null) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
	    }
	    URI uri = uriInfo.getRequestUri();
	    String newUri = uri.getPath() + "/" + spec.getId();
		return Response.status(Response.Status.CREATED)
                .contentLocation(uri.resolve(newUri))
                .build();
	}
	
	
	@GET
	@Path("/{id}/speciality/{id_s}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getSpeciality(@PathParam("id") int id, @PathParam("id_s") int id_s)
	{
		/** 
		 * @param
		 * @return
		 * */
		Speciality uni = service.getSpecialityFromUniversity(id, id_s);
	    if(uni== null) {
	    	return Response.status(Response.Status.NOT_FOUND).build();
	    }
	    Link link = Link.fromUri(uriInfo.getRequestUri())
	                    .rel("self")
	                    .type("application/xml")
	                    .build();
	    return Response.status(Response.Status.OK)
	                   .entity(uni)
	                   .links(link)
	                   .build();
	}
	
	@DELETE
	@Path("/{id}/speciality/{id_s}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteSpeciality(@PathParam("id") int id, @PathParam("id_s") int id_s)
	{
		if(service.removeSpeciality(id, id_s) == -1) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).build();
	}
}
