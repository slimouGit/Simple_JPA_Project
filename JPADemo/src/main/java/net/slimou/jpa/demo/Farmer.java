package net.slimou.jpa.demo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="farmer")
public class Farmer {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name="name")
    private String name;
 
    @Column(name="village")
    private String village;
    
    @OneToOne(mappedBy = "farmer", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Farm farm;
 
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getVillage() {
        return village;
    }
 
    public void setVillage(String village) {
        this.village = village;
    }

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}
    
    
}
