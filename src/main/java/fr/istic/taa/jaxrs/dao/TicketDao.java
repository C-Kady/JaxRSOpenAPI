package fr.istic.taa.jaxrs.dao;

import java.util.Collections;
import java.util.List;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.TicketId;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.NoResultException;

public class TicketDao extends AbstractJpaDao<TicketId, Ticket> {

    public TicketDao() {
        this.setClazz(Ticket.class);
    }
	
	//Requete NamedQuery
	public List<Ticket> findByUserIdNamedQuery(Long id) {
        	try {
        		return entityManager
	        		.createNamedQuery("Ticket.findByUserId", Ticket.class)
	                .setParameter("user_id", id)
	                .getResultList();
			} catch (NoResultException e) {
		        // Retourne liste vide si aucun utilisateur n'est trouvé
				return Collections.emptyList();
			}
    }

    // Requête pour voir qui vient à un concert
    public List<Ticket> findByEventId(Long eid) {
	    String query_jpql = "SELECT t FROM Ticket t WHERE t.event.eventId = :event_id";
    	try {
            return entityManager
            		.createQuery(query_jpql, Ticket.class)
                    .setParameter("event_id", eid)
                    .getResultList();
			
		} catch (Exception e) {
			return Collections.emptyList();
		}
    }
	
//	save(Ticket t)
//
//	delete(Long id)
//
//	findByUserId(Long userId)
//
//	findById(Long id).


    //Exemple d'utilisation cle composite
   // TicketDao ticketDao = new TicketDao();
   // // K est un TicketId
   // TicketId id = new TicketId(userId, concertId); 
   // Ticket t = ticketDao.findOne(id);
    
}
