package group_4.galaxyMyAdmin.Models;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "vehicules")
public class Vehicule implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Set<Activity> activities;

    Long id;

    String name;

    String type;

    int minCapacity;

    int maxCapacity;

     public Vehicule(String name, String type, int minCapacity, int maxCapacity) {
        this.name = name;
        this.type = type;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
       
    }
    public Vehicule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    

    
}
