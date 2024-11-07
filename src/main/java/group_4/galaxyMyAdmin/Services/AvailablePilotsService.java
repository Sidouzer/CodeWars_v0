package group_4.galaxyMyAdmin.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group_4.galaxyMyAdmin.Models.Pilot;

@Service
public class AvailablePilotsService {

    @Autowired
    PilotServiceImpl pilotService;

    private final List<Pilot> operationalPilots = new ArrayList<>();
    
    public void addActivity(Pilot pilot) {
                operationalPilots.add(pilot);
    }

    public List<Pilot> getOperationalPilots() {
        return new ArrayList<>(operationalPilots); 
    }

    public void clearOperationalPilots() {
        operationalPilots.clear();
    }
}
