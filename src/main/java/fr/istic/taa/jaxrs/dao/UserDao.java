package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.*;
import jakarta.persistence.*;


public class UserDao extends AbstractJpaDao<Long, User> {

	public UserDao() {
		this.setClazz(User.class);
	}
	
	//Requete NamedQuery
	public User findByEmailNamedQuery(String email) {
        	try {
        		return entityManager
	        		.createNamedQuery("User.findByEmail", User.class)
	                .setParameter("emailValue", email)
	                .getSingleResult();
			} catch (NoResultException e) {
		        // Retourne null si aucun utilisateur n'est trouvé
		        return null; 
			}
    }
	
//	//Requete NamedQuery
//	public User findByIdNamedQuery(Long id) {
//        	try {
//        		return entityManager
//	        		.createNamedQuery("User.findById", User.class)
//	                .setParameter("user_id", id)
//	                .getSingleResult();
//			} catch (NoResultException e) {
//		        // Retourne null si aucun utilisateur n'est trouvé
//		        return null; 
//			}
//    }
//	
//	// Criteria Query : On construit la requête avec du code Java. 
//	public List<User> findAllUser() {
//	    
//	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//	    
//	    CriteriaQuery<User> cq = cb.createQuery(User.class);
//	    
//	    Root<User> userRoot = cq.from(User.class);
//	    
//	    cq.select(userRoot);
//	    
//	    return entityManager.createQuery(cq).getResultList();
//	}
	
}
