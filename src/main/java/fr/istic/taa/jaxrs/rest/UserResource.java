package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.dto.ConnexionDto;
import fr.istic.taa.jaxrs.dto.PasswordDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("user")
@Produces({"application/json", "application/xml"})
@Tag(name = "User", description = "Gestion des Utilisateurs")
public class UserResource {
	
    private UserService userService;

    public UserResource() {
        UserDao userDao = new UserDao();
        this.userService = new UserService(userDao);
    }

    // Connexion
		@POST
		@Path("/connexion")
		@Consumes("application/json")
		@Operation(summary = "Se connecter", description = "Permet à un utilisateur de se connecter")
		@ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Connexion réussie",
		        content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
		    @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect")
		})
	  public Response connexion(
			    @RequestBody(
			            description = "Email et mot de passe de l'utilisateur",
			            required = true,
			            content = @Content(schema = @Schema(implementation = ConnexionDto.class))
			        )
			    ConnexionDto dto) {

	    // user contient juste email + password
	    try {
			UserResponseDto response = userService.seConnecter(dto);
	        return Response.ok(response).build();
	    } catch (Exception e) {
	        return Response.status(401).entity("Email ou mot de passe incorrect").build();
	    }
	    
	  }
	  

	// Modification mot de passe
	  @PUT
	  @Path("/updatePassword/user_id/{user_id}")
	  @Consumes("application/json")
	  @Operation(summary = "Modifier le mot de passe", description = "Permet de changer le mot de passe d'un utilisateur")
	  @ApiResponses({
		@ApiResponse(responseCode = "200", description = "Mot de passe modifié avec succès"),
		@ApiResponse(responseCode = "400", description = "Ancien mot de passe incorrect ou données invalides"),
		@ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
		})
	  public Response modifierPassword(
			    @Parameter(description = "ID de l'utilisateur", required = true, example = "1")
			    @PathParam("user_id") Long user_id,
			    
			    @RequestBody(
			        description = "Ancien et nouveau mot de passe",
			        required = true,
			        content = @Content(schema = @Schema(implementation = PasswordDto.class))
			    )PasswordDto dto) {
		  
	      try {
	          userService.changerMdp(user_id, dto);
	          return Response.ok("Mot de passe modifié").build();
	      } catch (Exception e) {
	          return Response.status(400).entity(e.getMessage()).build();
	      }
	  }
	  
	  
	  //liste des utilisateurs
	    @GET
	    @Path("/all")
	    @Operation(summary = "Lister tous les utilisateurs", description = "Retourne la liste de tous les utilisateurs")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Utilisateurs trouvés avec succes",
	            content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
	    })
	    public Response listeUtilisateurs() {
	        return Response.ok(userService.listeUtilisateurs()).build();
	    }
}
