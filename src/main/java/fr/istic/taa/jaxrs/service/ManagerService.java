package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;

import fr.istic.taa.jaxrs.dao.*;
import fr.istic.taa.jaxrs.domain.*;

public class ManagerService {  
	
    private ManagerDao managerDao;
    private UserDao userDao;

    public ManagerService(ManagerDao managerDao, UserDao userDao) {
        this.managerDao = managerDao;
        this.userDao = userDao;
    }
	
	//1-Methode metier inscrire Manager
	public void inscriptionManager(Manager manager) throws Exception {
		
	    if (this.userDao.findByEmailNamedQuery(manager.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }
	    String tempPassword = manager.getNom()+("123"); // ex: "Kouassi123"
	    manager.setPassword(BCrypt.hashpw(tempPassword, BCrypt.gensalt())); 
	    this.managerDao.save(manager); // Appel simple au DAO
	}


}
