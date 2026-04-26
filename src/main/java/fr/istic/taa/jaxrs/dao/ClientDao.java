package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.*;

public class ClientDao extends AbstractJpaDao<Long, Client>{

	public ClientDao() {
		this.setClazz(Client.class);
	}
	
//	
//	// Criteria Query : On construit la requête avec du code Java. 
//	public List<Client> findAllUser() {
//	    
//	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//	    
//	    CriteriaQuery<Client> cq = cb.createQuery(Client.class);
//	    
//	    Root<Client> userRoot = cq.from(Client.class);
//	    
//	    cq.select(userRoot);
//	    
//	    return entityManager.createQuery(cq).getResultList();
//	}
	
	
}
