package fr.istic.taa.jaxrs.rest;

import org.hibernate.service.spi.InjectService;

import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("user")
@Produces({"application/json", "application/xml"})
public class UserResource {
	
	private UserDao userDao = new UserDao();
	private UserService userService  = new UserService();
	
	  @GET
	  @Path("/{user_id}")
	  public Response getUserById(@PathParam("user_id") Long user_id)  {
	      // appel DAO
		  User user =  userDao.findByIdNamedQuery(user_id);
		  
		  if(user != null){
			  return Response.ok(user).build();
		  }
	      return Response.status(Response.Status.NOT_FOUND).build();
	  }

	  
	  @POST
	  @Path("/inscrire")
	  @Consumes("application/json")
	  //@Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet)
	  public Response inscription(User newUser) {
		  try {
			  userService.inscription(newUser);
			  return Response.status(Response.Status.CREATED).entity(newUser).build();
		} catch (Exception e) {
			// TODO: handle exception
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	    // add pet
	    //return Response.ok().entity("SUCCESS").build();
	  }

}
