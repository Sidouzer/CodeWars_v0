package group_4.galaxyMyAdmin.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import group_4.galaxyMyAdmin.Enumerations.PiloteRank;
import group_4.galaxyMyAdmin.Enumerations.PiloteStatus;
import group_4.galaxyMyAdmin.Enumerations.Race;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "pilots")
public class Pilot implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String firstname;

    String lastname;

    Race race;

    LocalDate registrationDate;

    int registrationAge;

    PiloteStatus status;

    PiloteRank rank;

    @OneToMany(mappedBy = "pilot", cascade = CascadeType.ALL)
    Set<Activity> activities;

    public Pilot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRegistrationAge() {
        return registrationAge;
    }

    public void setRegistrationAge(int registrationAge) {
        this.registrationAge = registrationAge;
    }

    public PiloteStatus getStatus() {
        return status;
    }

    public void setStatus(PiloteStatus status) {
        this.status = status;
    }

    public PiloteRank getRank() {
        return rank;
    }

    public void setRank(PiloteRank rank) {
        this.rank = rank;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    
}
