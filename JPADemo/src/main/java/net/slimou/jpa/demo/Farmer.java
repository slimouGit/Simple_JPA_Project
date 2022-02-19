package net.slimou.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="farmer")
public class Farmer {
 
    @Id
    @Column(name="id")
    private int id;
 
    @Column(name="name")
    private String name;
 
    @Column(name="village")
    private String village;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
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
}
