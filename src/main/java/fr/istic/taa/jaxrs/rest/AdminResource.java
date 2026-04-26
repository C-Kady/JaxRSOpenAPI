package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.AdminDao;
import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.dto.InscriptionClientDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.AdminService;
import fr.istic.taa.jaxrs.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;


@Path("admin")
@Produces({"application/json", "application/xml"})
@Tag(name = "Admin", description = "Gestion des Admin")
public class AdminResource {

    private AdminService adminService;

    public AdminResource() {
        AdminDao adminDao = new AdminDao();
        this.adminService = new AdminService(adminDao);
    }
    

	  //liste des utilisateurs
	    @GET
	    @Path("/all")
	    @Operation(summary = "Lister tous les Admins", description = "Retourne la liste de tous les Admins")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Admins trouvés avec succes",
	            content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
	    })
	    public Response listeUtilisateurs() {
	        return Response.ok(adminService.listeAdmins()).build();
	    }

}
