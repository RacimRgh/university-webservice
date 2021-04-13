/**
 * 
 */
package uni.restwebservice.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
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

import uni.restwebservice.data.University;
import uni.restwebservice.service.UniversityManagementRest;

/**
 * @author racim
 *
 */
@Path("university")
public class UniversityResource {
	UniversityManagementRest service = new UniversityManagementRest();
	
	@Context
	UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addUniversity(University u)
	{
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
	
}
