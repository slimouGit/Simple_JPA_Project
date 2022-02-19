package net.slimou.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="animal")
public class Animal {
	
	public enum Type{
        COW, CHICKEN, PORK;
    }
	
	@Id
    @Column(name="id")
    private int id;
 
    @Column(name="species")
    @Enumerated(EnumType.STRING)
    private Animal.Type species;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Animal.Type getSpecies() {
		return species;
	}

	public void setSpecies(Animal.Type species) {
		this.species = species;
	}

	
    
    

}
