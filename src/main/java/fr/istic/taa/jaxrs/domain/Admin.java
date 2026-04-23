package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

@Entity
public class Admin extends User implements Serializable  {

    private List<Event> events;


    //GETTERS - SETTERS
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST)
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

    //ToString Admin
	@Override
	public String toString() {
		return "Admin [events=" + events + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", tel=" + tel + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
}

