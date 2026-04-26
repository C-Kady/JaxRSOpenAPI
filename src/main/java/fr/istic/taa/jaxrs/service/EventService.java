package fr.istic.taa.jaxrs.service;

import java.time.LocalDate;
import java.util.List;

import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.domain.Event;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.dto.EventDto;

public class EventService {
	
    private EventDao eventDao;
	private ManagerDao managerDao;

    // Injection par constructeur
    public EventService(ManagerDao managerDao, EventDao eventDao) {
        this.managerDao = managerDao;
        this.eventDao = eventDao;
    }
    
    
    public Event creerEvent(EventDto dto) {
        Manager manager = managerDao.findOne(dto.getManagerId());
        if (manager == null) {
            throw new IllegalArgumentException("Manager introuvable");
        }

        Event event = new Event();
        event.setNom(dto.getNom());
        event.setDescription(dto.getDescription());
        event.setArtiste(dto.getArtiste());
        event.setLieu(dto.getLieu());
        event.setDate_concert(dto.getDateConcert());
        event.setGenreMusical(dto.getGenreMusical());
        event.setNb_place_disponible(dto.getNbPlaceDispo());
        event.setDureeConcert(dto.getDureeConcert());
        event.setManager(manager);

        eventDao.save(event);
        
        return event;
    }
     

    public List<Event> findEventsByDate(LocalDate date) {
    	
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        return eventDao.findByDateEventNamedQuery(date);
    }


    public List<Event> findEventsByManagerId(Long managerId) {
    	
        if (this.managerDao.findOne(managerId) == null) {
            throw new IllegalArgumentException("Manager Introuvable");
        }
        return eventDao.findByManagerId(managerId);
    }

}
