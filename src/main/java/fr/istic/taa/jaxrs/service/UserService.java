package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ConnexionDTO;
import fr.istic.taa.jaxrs.dto.PasswordDTO;
import fr.istic.taa.jaxrs.dto.UserResponseDTO;

public class UserService {
    
    private UserDao userDao;

    // Injection par constructeur
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
	

	//1-Methode seConnecter
	public UserResponseDTO seConnecter(ConnexionDTO dto) {
	    // 1. On demande au DAO de nous donner l'utilisateur par son email
	    User user = userDao.findByEmailNamedQuery(dto.getEmail());

	    if (user == null) {
	        throw new RuntimeException("Utilisateur non trouvé");
	    }

	    // 2. C'est ICI qu'on utilise la fonction de comparaison
	    // On ne fait JAMAIS "if (motDePasseSaisi == user.getPassword())"
	    boolean match = BCrypt.checkpw(dto.getPassword(), user.getPassword());

	    if (!match) {
	        throw new RuntimeException("Mot de passe incorrect");
	    }
	    

	    String role = null;
	    if (user instanceof Client) {
	        role = "CLIENT";
	    } else if (user instanceof Manager) {
	        role = "MANAGER";
	    } else if (user instanceof Admin) {
	        role = "ADMIN";
	    }

	    
        // Mapper vers DTO de réponse
	    UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getUserId());
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        response.setEmail(user.getEmail());
        response.setTel(user.getTel());
        response.setStatut_user(user.isStatut_user());
        response.setDate_naissance(user.getDate_naissance());
        
        response.setRole(role); //### A revoir
        
        return response; // Connexion réussie
	}
	
	//Déconnexion	Invalide la session en cours ou supprime le token (pas besoin du DAO ici en général).



	//2- Modifier MDP
	public void changerMdp(Long user_id, PasswordDTO dto) throws Exception {
	
	    User user = userDao.findOne(user_id);

	    if (user == null) {
	        throw new RuntimeException("Utilisateur non trouvé");
	    }

	    boolean match = BCrypt.checkpw(dto.getOldPassword(), user.getPassword());

	    if (!match) {
	        throw new RuntimeException("Ancien mot de passe incorrect");
	    }

	    String hashed = BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt());
	    user.setPassword(hashed);
	    
	    this.userDao.update(user);
	    
	}
}
