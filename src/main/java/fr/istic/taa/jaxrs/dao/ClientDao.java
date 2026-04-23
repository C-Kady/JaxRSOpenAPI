package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.*;

public class ClientDao extends AbstractJpaDao<Long, Client>{

	public ClientDao() {
		this.setClazz(Client.class);
	}
	
	
////Requete avec JPQL
//	public Client findByEmail(String email) {
//		
//	    String query_jpql = "SELECT c FROM Client c WHERE c.email = :emailValue";
//	    try {
//	        return entityManager.createQuery(query_jpql, Client.class)
//	                .setParameter("emailValue", email)
//	                .getSingleResult();
//	    } catch (NoResultException e) {
//	        // Retourne null si aucun utilisateur n'est trouvé
//	        return null; 
//	    }
//	}  
//	
	
	
	//Requete NamedQuery
//	public Client findByIdNamedQuery(Long id) {
//        	try {
//        		return entityManager
//	        		.createNamedQuery("Client.findById", Client.class)
//	                .setParameter("user_id", id)
//	                .getSingleResult();
//			} catch (NoResultException e) {
//		        // Retourne null si aucun utilisateur n'est trouvé
//		        return null; 
//			}
//    }
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
