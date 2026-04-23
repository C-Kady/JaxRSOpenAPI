package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Event implements Serializable {

    private long eventId;
	private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private LocalDate dateConcert;
    private String genreMusical;
    private int nbPlaceDispo;
    private String dureeConcert;
    private String statut;
    private LocalDate dateValidation;
    private String commentairesAdmin;
    private Manager manager;
    private Admin admin;
    private List<Ticket> tickets;
    
    
    //GETTERS - SETTERS
	@Id
    @GeneratedValue
    public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

    @ManyToOne
    public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}

    @ManyToOne
    public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() { 
    	return tickets; 
    }
    public void setTickets(List<Ticket> tickets) { 
    	this.tickets = tickets; 
    }
    
    
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}
	
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	public LocalDate getDateConcert() {
		return dateConcert;
	}
	public void setDateConcert(LocalDate dateConcert) {
		this.dateConcert = dateConcert;
	}
	
	public String getGenreMusical() {
		return genreMusical;
	}
	public void setGenreMusical(String genreMusical) {
		this.genreMusical = genreMusical;
	}
	
	public int getNbPlaceDispo() {
		return nbPlaceDispo;
	}
	public void setNbPlaceDispo(int nbPlaceDispo) {
		this.nbPlaceDispo = nbPlaceDispo;
	}
	
	public String getDureeConcert() {
		return dureeConcert;
	}
	public void setDureeConcert(String dureeConcert) {
		this.dureeConcert = dureeConcert;
	}
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public LocalDate getDateValidation() {
		return dateValidation;
	}
	public void setDateValidation(LocalDate dateValidation) {
		this.dateValidation = dateValidation;
	}
	
	public String getCommentairesAdmin() {
		return commentairesAdmin;
	}
	public void setCommentairesAdmin(String commentairesAdmin) {
		this.commentairesAdmin = commentairesAdmin;
	}
	
    //ToString Event
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", nom=" + nom + ", description=" + description + ", artiste=" + artiste
				+ ", lieu=" + lieu + ", dateConcert=" + dateConcert + ", genreMusical=" + genreMusical
				+ ", nbPlaceDispo=" + nbPlaceDispo + ", dureeConcert=" + dureeConcert + ", statut=" + statut
				+ ", dateValidation=" + dateValidation + ", commentairesAdmin=" + commentairesAdmin + ", manager="
				+ manager + ", admin=" + admin + ", tickets=" + tickets + "]";
	}
    
}