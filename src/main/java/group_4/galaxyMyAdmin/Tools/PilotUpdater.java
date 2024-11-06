package group_4.galaxyMyAdmin.Tools;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import group_4.galaxyMyAdmin.Enumerations.PilotRank;
import group_4.galaxyMyAdmin.Enumerations.PilotStatus;
import group_4.galaxyMyAdmin.Models.Pilot;

@Service
public class PilotUpdater {

    public Pilot initialize(Pilot pilot) {
        pilot.setFlightHours(0);
        pilot.setRank(PilotRank._APPRENTICE);
        pilot.setRegistrationDate(LocalDate.now());
        pilot.setStatus(PilotStatus._OPE);

        return pilot;
    }

    
}
