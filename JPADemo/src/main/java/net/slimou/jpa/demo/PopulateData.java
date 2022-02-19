package net.slimou.jpa.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.slimou.jpa.demo.Animal.Type;

public class PopulateData {

	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "FarmeVille";

	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	private static EntityManager em = getEntityManager();

	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public static void main(String[] args) {
		em.getTransaction().begin();
		
		Farmer farmer1 = new Farmer();
		farmer1.setName("Pumuckel Da Vinci");
		farmer1.setVillage("Bengal-Anatolien");
		em.persist(farmer1);
		
		Farm farm1 = new Farm();
		farm1.setFarmer(farmer1);
		em.persist(farm1);

		Animal a1 = new Animal();
		a1.setSpecies(Type.CHICKEN);
		a1.setFarm(farm1);
		em.persist(a1);

		Animal a2 = new Animal();
		a2.setSpecies(Type.PORK);
		a2.setFarm(farm1);
		em.persist(a2);

		farm1.setAnimals(Arrays.asList(a1, a2));
		farmer1.setFarm(farm1);
		em.persist(farm1);

		em.getTransaction().commit();
		em.clear();

	}

	private static Animal createAnimal(Type species, Farm farm) {
		em.getTransaction().begin();
		Animal animal = new Animal();
		animal.setSpecies(species);
		animal.setFarm(farm);
		em.persist(animal);
		em.getTransaction().commit();
		em.clear();
		return animal;
	}

	private static Farmer createFarmer(String name, String village) {
		em.getTransaction().begin();
		Farmer farmer = new Farmer();
		farmer.setName(name);
		farmer.setVillage(village);
		em.persist(farmer);
		em.getTransaction().commit();
		em.clear();
		return farmer;
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
		return resultList;
	}

	private static List<Farmer> findByName(String name) {
		String sqlQuery = "SELECT f.* FROM Farmer f WHERE f.name like '%" + name + "%'";
		List<Farmer> resultList = em.createNativeQuery(sqlQuery, Farmer.class).getResultList();
		return resultList;
	}

}
