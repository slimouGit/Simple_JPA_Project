package net.slimou.jpa.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "farm")
public class Farm {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "farm")
	private List<Animal> animals;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "farmer")
	private Farmer farmer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

}
