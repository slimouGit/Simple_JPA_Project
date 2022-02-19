package net.slimou.jpa.demo;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PopulateData {

	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "JPADemo";

	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	private static EntityManager em = getEntityManager();

	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public static void main(String[] args) {
		createFarmer("Max Mustermann", "Feldberg Vally");
		findOneFarmer(101);
		findAllFarmers();
		findFarmerByName("Mus");
	}

	private static void createFarmer(String name, String village) {
		em.getTransaction().begin();
		Farmer farmer = new Farmer();
		farmer.setName(name);
		farmer.setVillage(village);
		em.persist(farmer);
		em.getTransaction().commit();
		em.clear();
	}

	private static void findOneFarmer(int id) {
		List<Farmer> farmer = findById(id);
		System.out.println(farmer.get(0).getName());
	}

	private static void findAllFarmers() {
		List<Farmer> farmers = findAll();
		for (Farmer f : farmers) {
			System.out.println(f.getName());
		}
	}

	private static void findFarmerByName(String name) {
		List<Farmer> farmer = findByName(name);
		System.out.println(farmer.get(0).getVillage());
	}

	/*-----QUERIES----------*/
	
	private static List<Farmer> findById(int id) {
		return em.createQuery("SELECT f FROM Farmer f WHERE f.id LIKE ?1").setParameter(1, id).getResultList();
	}

	private static List<Farmer> findAll() {
		String sqlQuery = "SELECT f.* FROM Farmer f";
		List<Farmer> resultList = em.createNativeQuery(sqlQuery, Farmer.class).getResultList();
		return resultList.stream().collect(Collectors.toList());
	}

	private static List<Farmer> findByName(String name) {
		String sqlQuery = "SELECT f.* FROM Farmer f WHERE f.name like '%" + name + "%'";
		List<Farmer> resultList = em.createNativeQuery(sqlQuery, Farmer.class).getResultList();
		return resultList.stream().collect(Collectors.toList());
	}
		
	
}
