package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
//Requete NamedQuery
@NamedQuery(
	    name = "User.findById",
	    query = "SELECT u FROM User u WHERE u.userId = :user_id"
	)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
	
	protected long userId;
    protected String nom;
    protected String prenom;
    protected String email;
    protected LocalDate date_naissance;
    protected String password;
    protected int tel;
    protected boolean statut_user;

    
    //Getters and Setters
    
	@Id
	@GeneratedValue
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}

	
	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isStatut_user() {
		return statut_user;
	}
	public void setStatut_user(boolean statut_user) {
		this.statut_user = statut_user;
	}
	
	
    //ToString User
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
    /*public User(long userId, String nom, String prenom, String email, long tel, LocalDate dateNaiss, String mdp) {
		super();
		this.userId = userId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.dateNaiss = dateNaiss;
		this.mdp = mdp;
	}
    
    public User() {}*/
}
