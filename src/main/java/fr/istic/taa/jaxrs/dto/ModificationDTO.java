package fr.istic.taa.jaxrs.dto;

import java.time.LocalDate;

public class ModificationDTO {
	
	    private String nom;
	    private String prenom;
	    private String tel;
	    private LocalDate date_naissance;
	    
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
		
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		
		public LocalDate getDate_naissance() {
			return date_naissance;
		}
		public void setDate_naissance(LocalDate date_naissance) {
			this.date_naissance = date_naissance;
		}

}
