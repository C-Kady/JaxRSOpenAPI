package fr.istic.taa.jaxrs.rest;

import org.hibernate.service.spi.InjectService;

import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ConnexionDTO;
import fr.istic.taa.jaxrs.dto.PasswordDTO;
import fr.istic.taa.jaxrs.dto.UserResponseDTO;
import fr.istic.taa.jaxrs.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("user")
@Produces({"application/json", "application/xml"})
public class UserResource {
	
    private UserService userService;

    public UserResource() {
        UserDao userDao = new UserDao();
        this.userService = new UserService(userDao);
    }
	
//	  @GET
//	  @Path("/{user_id}")
//	  public Response getUserById(@PathParam("user_id") Long user_id)  {
//	      // appel DAO
//		  User user =  userDao.findOne(user_id);
//		  
//		  if(user != null){
//			  return Response.ok(user).build();
//		  }
//	      return Response.status(Response.Status.NOT_FOUND).build();
//	  }


	// Modification mot de passe
	  @PUT
	  @Path("/updatePassword/{user_id}")
	  public Response modifierPassword(@PathParam("user_id") Long user_id, PasswordDTO dto) {
	      // user contient juste l'ancien et le nouveau password
	      try {
	          userService.changerMdp(user_id, dto);
	          return Response.ok("Mot de passe modifié").build();
	      } catch (Exception e) {
	          return Response.status(400).entity(e.getMessage()).build();
	      }
	  }
	  
	  
	  @POST
	  @Path("/connexion")
	  @Consumes("application/json")
	  public Response connection(ConnexionDTO dto) {

	    // user contient juste email + password
	    try {
			UserResponseDTO response = userService.seConnecter(dto);
	        return Response.ok(response).build();
	    } catch (Exception e) {
	        return Response.status(401).entity("Email ou mot de passe incorrect").build();
	    }
	    
	  }

}
