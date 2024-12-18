package group_4.galaxyMyAdmin.Models;

import java.io.Serializable;
import java.util.Set;

import group_4.galaxyMyAdmin.Enumerations.MissionStatus;
import group_4.galaxyMyAdmin.Enumerations.ShipStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "ships")
public class Ship implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ShipStatus status;

    @OneToOne
    private Vehicule model;

    

    @OneToMany(mappedBy = "ship")
    private Set<Activity> activities;

    public Ship(Long id, ShipStatus status, Vehicule model) {
        this.id = id;
        this.status = status;
        this.model = model;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public Vehicule getModel() {
        return model;
    }

    public void setModel(Vehicule model) {
        this.model = model;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
    public Ship() {
    }

     public boolean isAvailable() {
        for(Activity activity : this.activities) {
            if(activity.getMission().getStatus() == MissionStatus._ONGOING) {
                return false;
            }
        }
        return true;
    }
}
