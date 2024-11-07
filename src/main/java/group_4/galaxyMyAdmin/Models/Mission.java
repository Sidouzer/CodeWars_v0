package group_4.galaxyMyAdmin.Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import group_4.galaxyMyAdmin.Enumerations.MissionStatus;
import group_4.galaxyMyAdmin.Enumerations.MissionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

@Entity(name = "missions")
public class Mission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Mission type is required")
    private MissionType type;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 0, message = "Flight hours cannot be negative")
    private int flightHours;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<Activity> activities = new HashSet<>();

    public Mission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MissionType getType() {
        return type;
    }

    public void setType(MissionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setPilot(Pilot pilot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPilot'");
    }

    public void setShip(Ship ship) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShip'");
    }
}
