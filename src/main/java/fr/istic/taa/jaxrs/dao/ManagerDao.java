package fr.istic.taa.jaxrs.dao;

import java.util.List;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Manager;

public class ManagerDao extends AbstractJpaDao<Long, Manager>{

	public ManagerDao() {
		this.setClazz(Manager.class);
	}
	
	//Requete avec JPQL
	public List<Manager> findMangersActifs() {

	    String query_jpql = "SELECT m FROM Manager m WHERE m.statut_User = 'actif'";

	    return this.entityManager.createQuery(query_jpql).getResultList();
	}

}


