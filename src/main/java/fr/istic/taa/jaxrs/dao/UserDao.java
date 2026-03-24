package fr.istic.taa.jaxrs.dao;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;


public class UserDao extends AbstractJpaDao<Long, User> {

	public UserDao() {
		this.setClazz(User.class);
	}
	
	//Requete NamedQuery
	public User findByIdNamedQuery(Long id) {
        	try {
        		return entityManager
	        		.createNamedQuery("User.findById", User.class)
	                .setParameter("user_id", id)
	                .getSingleResult();
			} catch (NoResultException e) {
		        // Retourne null si aucun utilisateur n'est trouvé
		        return null; 
			}
    }
	
	//Requete avec JPQL
	public User findByEmail(String email) {
		
	    String query_jpql = "SELECT u FROM User u WHERE u.email = :emailValue";
	    try {
	        return entityManager.createQuery(query_jpql, User.class)
	                .setParameter("emailValue", email)
	                .getSingleResult();
	    } catch (NoResultException e) {
	        // Retourne null si aucun utilisateur n'est trouvé
	        return null; 
	    }
	}  
	
	// Criteria Query : On construit la requête avec du code Java. 
	public List<User> findAllUser() {
	    
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    
	    Root<User> userRoot = cq.from(User.class);
	    
	    cq.select(userRoot);
	    
	    return entityManager.createQuery(cq).getResultList();
	}
	
}
