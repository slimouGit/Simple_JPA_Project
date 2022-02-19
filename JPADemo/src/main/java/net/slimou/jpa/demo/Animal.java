package net.slimou.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="animal")
public class Animal {
	
	public enum Type{
        COW, CHICKEN, PORK;
    }
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name="species")
    @Enumerated(EnumType.STRING)
    private Animal.Type species;
    
    @ManyToOne
    private Farm farm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Animal.Type getSpecies() {
		return species;
	}

	public void setSpecies(Animal.Type species) {
		this.species = species;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	
	

	
    
    

}
