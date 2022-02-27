package net.slimou.jpa.demo;

import java.util.Arrays;
import java.util.List;
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
		farmer1.setForename("Horst");
		farmer1.setSurname("Bembel");
		em.persist(farmer1);
		
		Farm farm1 = new Farm();
		farm1.setFarmer(farmer1);
		farm1.setName("Ebbelwoi Palace");
		em.persist(farm1);

		Animal a1 = new Animal();
		a1.setSpecies(Type.CHICKEN);
		a1.setFarm(farm1);
		em.persist(a1);

		Animal a2 = new Animal();
		a2.setSpecies(Type.PORK);
		a2.setFarm(farm1);
		em.persist(a2);
		
		Animal a3 = new Animal();
		a3.setSpecies(Type.COW);
		a3.setFarm(farm1);
		em.persist(a3);

		farm1.setAnimals(Arrays.asList(a1, a2, a3));
		farmer1.setFarm(farm1);
		em.persist(farm1);

		em.getTransaction().commit();
		em.clear();
		
		printFarm(farm1);

	}

	private static void printFarm(Farm farm) {
		System.out.println("Farm:\t" + farm.getName());
		System.out.println("Farmer:\t" + farm.getFarmer().getForename() + " " + farm.getFarmer().getSurname());
		for(Animal animal:farm.getAnimals()) {
			System.out.println("Animal:\t"+ animal.getSpecies());
		}
		
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

	private static Farmer createFarmer(String forename, String surname) {
		em.getTransaction().begin();
		Farmer farmer = new Farmer();
		farmer.setForename(forename);
		farmer.setSurname(surname);
		em.persist(farmer);
		em.getTransaction().commit();
		em.clear();
		return farmer;
	}

	private static void findOneFarmer(int id) {
		List<Farmer> farmer = findById(id);
		System.out.println(farmer.get(0).getForename());
	}

	private static void findAllFarmers() {
		List<Farmer> farmers = findAll();
		for (Farmer f : farmers) {
			System.out.println(f.getForename());
		}
	}

	private static void findFarmerByName(String surname) {
		List<Farmer> farmer = findByName(surname);
		System.out.println(farmer.get(0).getSurname());
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

	private static List<Farmer> findByName(String surname) {
		String sqlQuery = "SELECT f.* FROM Farmer f WHERE f.surname like '%" + surname + "%'";
		List<Farmer> resultList = em.createNativeQuery(sqlQuery, Farmer.class).getResultList();
		return resultList;
	}

}
