package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
////Requete NamedQuery
//@NamedQuery(
//	    name = "Client.findById",
//	    query = "SELECT c FROM Client c WHERE c.userId = :user_id"
//	)
public class Client extends User implements Serializable{

    private List<Ticket> tickets; 

	
	//GETTERS - SETTERS
	
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    //ToString Client
	@Override
	public String toString() {
		return "Client [tickets=" + tickets + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", tel=" + tel + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
}