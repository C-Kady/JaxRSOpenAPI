package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.AchatTicketDto;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("ticket")
@Produces({"application/json", "application/xml"})
@Tag(name = "Ticket", description = "Gestion des tickets")
public class TicketResource {
	
    private TicketService ticketService;

    public TicketResource() {
    	ClientDao clientDao = new ClientDao();
    	EventDao eventDao = new EventDao();
    	TicketDao ticketDao = new TicketDao();
        this.ticketService = new TicketService(clientDao, eventDao, ticketDao);
    }   
    

	@POST
	@Path("/acheter")
	@Consumes("application/json")
    @Operation(
            summary = "Acheter un ticket",
            description = "Permet à un client d'acheter un ticket pour un événement"
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "Ticket acheté avec succès",
                content = @Content(schema = @Schema(implementation = Ticket.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erreur lors de l'achat (plus de places, client introuvable...)",
                content = @Content(schema = @Schema(implementation = String.class))
            )
        })
	public Response acheterTicket(AchatTicketDto dto) {
		  try {
			  Ticket ticket = ticketService.acheterTicket(dto);
			  return Response.status(Response.Status.CREATED).entity(ticket).build();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
    
	
    @GET
    @Path("/all/{clientId}")
    @Operation(
            summary = "Récupérer les tickets d'un client",
            description = "Retourne la liste de tous les tickets achetés par un client"
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Liste des tickets du client",
                content = @Content(schema = @Schema(implementation = Ticket.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Client introuvable"
            )
        })
    public Response findEventsByManagerId(@PathParam("clientId") Long clientId) {
        return Response.ok(ticketService.findTicketsByClientId(clientId)).build();
    }
    	

}
