package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
/*import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;*/

@Entity
//Requete NamedQuery
@NamedQuery(
	    name = "Ticket.findByUserId",
	    query = "SELECT t FROM Ticket t WHERE t.client.userId = :user_id"
	)
public class Ticket implements Serializable {
    
    @EmbeddedId  
    private TicketId id;
    private int numeroPlace;
    private BigDecimal prix;
    private String statut;
    private LocalDate dateAchat;
    private LocalDate dateAnnulation;
	private LocalDate dateRemboursement;
    
    @ManyToOne
    @MapsId("userId") 
    @JoinColumn(name = "userId", nullable = false)
    private Client client;
    
    @ManyToOne
    @MapsId("eventId") // Map a TicketId.eventId
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;
    
    @Column(precision = 8, scale = 2)
    public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
    
    // Getters et Setters
    public TicketId getId() { return id; }
    public void setId(TicketId id) { this.id = id; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
	public int getNumeroPlace() {
		return numeroPlace;
	}
	public void setNumeroPlace(int numeroPlace) {
		this.numeroPlace = numeroPlace;
	}
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public LocalDate getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	public LocalDate getDateAnnulation() {
		return dateAnnulation;
	}
	public void setDateAnnulation(LocalDate dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}
	
	public LocalDate getDateRemboursement() {
		return dateRemboursement;
	}
	public void setDateRemboursement(LocalDate dateRemboursement) {
		this.dateRemboursement = dateRemboursement;
	}

    public void diminuer() {
        // diminuer le nombre de places disponibles
    }
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", numeroPlace=" + numeroPlace + ", prix=" + prix + ", statut=" + statut
				+ ", dateAchat=" + dateAchat + ", dateAnnulation=" + dateAnnulation + ", dateRemboursement="
				+ dateRemboursement + ", client=" + client + ", event=" + event + "]";
	}
    
}


////CONSTRUCTEURS
//public Ticket() {}
//public Ticket(int userId, int eventId) {
//  this.id = new TicketId(userId, eventId);
//}