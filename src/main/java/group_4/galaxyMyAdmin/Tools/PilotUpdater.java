package group_4.galaxyMyAdmin.Tools;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import group_4.galaxyMyAdmin.Enumerations.PiloteRank;
import group_4.galaxyMyAdmin.Enumerations.PiloteStatus;
import group_4.galaxyMyAdmin.Models.Pilot;

@Service
public class PilotUpdater {

    public Pilot initialize(Pilot pilot) {
        pilot.setFlightHours(0);
        pilot.setRank(PiloteRank._APPRENTICE);
        pilot.setRegistrationDate(LocalDate.now());
        pilot.setStatus(PiloteStatus._OPE);

        return pilot;
    }

    
}
