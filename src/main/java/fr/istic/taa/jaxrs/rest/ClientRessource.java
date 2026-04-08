package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.dto.ClientResponseDTO;
import fr.istic.taa.jaxrs.dto.InscriptionDTO;
import fr.istic.taa.jaxrs.dto.UserResponseDTO;
import fr.istic.taa.jaxrs.service.ClientService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("client")
@Produces({"application/json", "application/xml"})
public class ClientRessource {

    private ClientService clientService;

    public ClientRessource() {
        ClientDao clientDao = new ClientDao();
        UserDao UserDao = new UserDao();
        this.clientService = new ClientService(clientDao, UserDao);
    }
    

	
	@POST
	@Path("/inscrire")
	@Consumes("application/json")
	//@Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet)
	public Response inscription(InscriptionDTO dto) {
		  try {
			  UserResponseDTO response = clientService.inscription(dto);
			  return Response.status(Response.Status.CREATED).entity(response).build();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}




