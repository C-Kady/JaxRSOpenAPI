package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
//Requete NamedQuery
@NamedQuery(
	    name = "Manager.findByEmail",
	    query = "SELECT m FROM Manager m WHERE m.email = :emailValue"
	)
public class Manager extends User implements Serializable {
	
    private List<Event> events;


    //GETTERS - SETTERS
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST)
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
    //ToString Manager
	@Override
	public String toString() {
		return "Manager [events=" + events + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", tel=" + tel + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
	
//    //CONSTRUCTEURS
//    public Manager() {
//		super();
//    }
//    public Manager(String statutManager) {
//		this.setStatutManager(statutManager);
//	}
}
