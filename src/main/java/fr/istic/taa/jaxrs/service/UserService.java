package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.User;

public class UserService {
	
	//1-proposition d'implementation pour methode s'inscrire
	public void inscription(User user) throws Exception {
		UserDao userDao = new UserDao();
		
	    if (userDao.findByEmail(user.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }
	    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
	    userDao.save(user); // Appel simple au DAO
	}
	

	//2-proposition d'implementation pour methode seConnecter
//	public User seConnecter(String email, String motDePasseSaisi) {
//	    // 1. On demande au DAO de nous donner l'utilisateur par son email
//	    User user = userReferenceDao.findByEmail(email);
//
//	    if (user == null) {
//	        throw new RuntimeException("Utilisateur non trouvé");
//	    }
//
//	    // 2. C'est ICI qu'on utilise la fonction de comparaison
//	    // On ne fait JAMAIS "if (motDePasseSaisi == user.getPassword())"
//	    boolean match = BCrypt.checkpw(motDePasseSaisi, user.getPassword());
//
//	    if (!match) {
//	        throw new RuntimeException("Mot de passe incorrect");
//	    }
//
//	    return user; // Connexion réussie
//	}
	
	//3-Déconnexion	Invalide la session en cours ou supprime le token (pas besoin du DAO ici en général).

}
