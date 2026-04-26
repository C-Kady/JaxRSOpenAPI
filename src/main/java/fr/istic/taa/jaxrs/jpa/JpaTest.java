//package fr.istic.taa.jaxrs.jpa;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import fr.istic.taa.jaxrs.dao.ManagerDao;
//import fr.istic.taa.jaxrs.dao.UserDao;
//import fr.istic.taa.jaxrs.domain.Manager;
//import fr.istic.taa.jaxrs.domain.User;
//
//public class JpaTest {
//
//
//	private EntityManager manager;
//
//	public JpaTest(EntityManager manager) {
//		this.manager = manager;
//	}
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		EntityManager manager = EntityManagerHelper.getEntityManager();
//
//		JpaTest test = new JpaTest(manager);
//
//		EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//		
//		//cree directement l'utilisateur lié
//
////		ManagerDao managerDao = new ManagerDao();
////		Manager m = new Manager();
////		m.setNom("Dupont");
////		m.setPrenom("Jean");
////		m.setStatut_user(true);
////		managerDao.save(m);
////		System.out.println(managerDao.findAll());
////		
////		ManagerDao managerDao = new ManagerDao();
////		System.out.println(managerDao.findMangersActifs());
////		
////		UserDao userDao = new UserDao();
////		System.out.println(userDao.findUsersByNom("Aryath"));
//		
//		try {
//
//			// TODO create and persist entity
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		/*tx.commit();
//
//			
//   	 manager.close();
//		EntityManagerHelper.closeEntityManagerFactory();*/
//		System.out.println(".. done");
//	}
//
//}

package fr.istic.taa.jaxrs.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		try {

			// TODO create and persist entity
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

}
