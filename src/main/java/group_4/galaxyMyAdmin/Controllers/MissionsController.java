package group_4.galaxyMyAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group_4.galaxyMyAdmin.Enumerations.MissionStatus;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Services.MissionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MissionsController {

    @Autowired
    MissionServiceImpl missionService;

    @GetMapping("/missions")
    public String getMissions(Model model) {
        // Récupère toutes les missions
        List<Mission> allMissions = new ArrayList<>(missionService.findAll());

        // Filtre les missions en fonction de leur statut
        List<Mission> ongoingMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._ONGOING)
            .collect(Collectors.toList());

        List<Mission> succededMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._SUCCESS)
            .collect(Collectors.toList());

        List<Mission> failureMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._FAIL)
            .collect(Collectors.toList());
            
        // Ajoute les missions filtrées au modèle
        model.addAttribute("ongoingMissions", ongoingMissions);
        model.addAttribute("succededMissions", succededMissions);
        model.addAttribute("failureMissions", failureMissions);

        return "missions"; // Renvoie à la vue `missions.html`
    }
}
