package fr.istic.taa.jaxrs.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.domain.Event;
import fr.istic.taa.jaxrs.dto.EventDto;
import fr.istic.taa.jaxrs.service.EventService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("event")
@Produces({"application/json", "application/xml"})
public class EventResource {
	
    private EventService eventService;

    public EventResource() {
    	EventDao eventDao = new EventDao();
    	ManagerDao managerDao = new ManagerDao();
        this.eventService = new EventService(managerDao, eventDao);
    } 
    
    @POST
    @Path("/create")
	@Consumes("application/json")
    public Response creerEvent(EventDto dto) {
        try {
            Event newEvent = eventService.creerEvent(dto);
            return Response.status(Response.Status.CREATED)
                           .entity(newEvent)
                           .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/all/{managerId}")
    public Response findEventsByManagerId(@PathParam("managerId") Long manager_Id) {
        return Response.ok(eventService.findEventsByManagerId(manager_Id)).build();
    }
    
    
    @GET
    @Path("all/date")
    public Response findEventsByDate(@QueryParam("date") String dateStr) {
        if (dateStr == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("La date est obligatoire")
                           .build();
        }
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return Response.ok(eventService.findEventsByDate(date)).build();
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Format de date invalide, utilisez YYYY-MM-DD")
                           .build();
        }
    }
    
//    @GET
//    @Path("all/date")
//    public Response findEventsByDate(@QueryParam("date") String dateStr) {
//        LocalDate date = LocalDate.parse(dateStr);
//        return Response.ok(eventService.findEventsByDate(date)).build();
//    }

}
