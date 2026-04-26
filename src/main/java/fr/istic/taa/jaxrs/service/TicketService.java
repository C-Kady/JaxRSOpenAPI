package fr.istic.taa.jaxrs.service;

import java.util.List;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import fr.istic.taa.jaxrs.dto.AchatTicketDto;
import jakarta.transaction.Transactional;

public class TicketService {
    

    private ClientDao clientDao;
    private EventDao eventDao;
    private TicketDao ticketDao;

    // Injection par constructeur
    public TicketService(ClientDao clientDao, EventDao eventDao, TicketDao ticketDao) {
        this.clientDao = clientDao;
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }
    

    // Acheter Ticket
    @Transactional
    public Ticket acheterTicket(AchatTicketDto dto) {

        Client client = clientDao.findOne(dto.getClientId());
        Event event = eventDao.findOne(dto.getEventId());
        if (event.getNb_place_disponible() <= 0) {
            throw new RuntimeException("Plus de places disponibles");
        }
        

        // Construire la clé composite
        TicketId ticket_id = new TicketId(dto.getClientId(), dto.getEventId()); 
        // Vérifier que ce client n'a pas déjà un ticket pour cet event
        if (ticketDao.findOne(ticket_id) != null) {
            throw new RuntimeException("Vous avez déjà un ticket pour cet event");
        }
        

        Ticket ticket = new Ticket();
        ticket.setId(ticket_id);
        ticket.setStatut(StatutTicket.ACHETE);
        ticket.setDateAchat(dto.getDateAchat());
        ticket.setEvent(event);
        ticket.setClient(client);
        ticketDao.save(ticket);

        event.diminuerPlaces();
        eventDao.save(event);

        return ticket;
    }
    

    public List<Ticket> findTicketsByClientId(Long clientId) {
    	
        if (this.clientDao.findOne(clientId) == null) {
            throw new IllegalArgumentException("Client Introuvable");
        }
        return ticketDao.findByClientIdNamedQuery(clientId);
    }
    
	
//	TicketService
//	
//	C'est la partie la plus complexe car elle touche à l'argent et aux places disponibles.
//
//	acheterTicket(Long userId, Long concertId) :
//
//	Vérifie si le concert est complet.
//
//	Décrémente le nombre de places via ConcertDao.
//
//	Crée le ticket via TicketDao.
//
//	annulerEtRembourser(Long ticketId) :
//
//	Vérifie si la date du concert n'est pas passée.
//
//	Déclenche la logique de remboursement (API bancaire ou avoir).
//
//	Supprime/Archive le ticket via TicketDao.
//
//	Réaugmente le nombre de places du concert.
//
//	genererPdfTicket(Long ticketId) : Récupère les infos du ticket et transforme les données en flux PDF (pour le téléchargement).

	
//	public void annulerAchat(Long userId, Long concertId) {
//	    TicketId id = new TicketId(userId, concertId);
//	    ticketDao.deleteById(id);
//	}

}
