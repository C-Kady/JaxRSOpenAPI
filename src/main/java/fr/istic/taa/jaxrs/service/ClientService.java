package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.dto.*;

public class ClientService {
    
    private ClientDao clientDao;
    private UserDao userDao;

    // Injection par constructeur
    public ClientService(ClientDao clientDao, UserDao userDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
    }
    
    
	//1-Methode metier S'inscrire
	public UserResponseDTO inscription(InscriptionDTO dto) throws Exception {
		
	    if (this.userDao.findByEmailNamedQuery(dto.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }
	    
	    Client client = new Client();
	    
	    client.setNom(dto.getNom());
	    client.setPrenom(dto.getPrenom());
	    client.setEmail(dto.getEmail());
	    client.setTel(dto.getTel());
	    client.setDate_naissance(dto.getDate_naissance());
	    client.setStatut_user(true);
	    
	    String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
	    client.setPassword(hashed);
	    
	    this.clientDao.save(client); // Appel simple au DAO
	    
        // Mapper vers DTO de réponse
	    UserResponseDTO response = new UserResponseDTO();
        response.setId(client.getUserId());
        response.setNom(client.getNom());
        response.setPrenom(client.getPrenom());
        response.setEmail(client.getEmail());
        response.setTel(client.getTel());
        response.setStatut_user(client.isStatut_user());
        response.setDate_naissance(client.getDate_naissance());
        
        response.setRole("CLIENT"); //### A revoir
        
        return response;
	}
	
	//2-Methode metier Se Connecter (voir User)

}
