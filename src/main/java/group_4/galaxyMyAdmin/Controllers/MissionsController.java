package group_4.galaxyMyAdmin.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group_4.galaxyMyAdmin.Enumerations.MissionStatus;
import group_4.galaxyMyAdmin.Enumerations.PilotStatus;
import group_4.galaxyMyAdmin.Enumerations.ShipStatus;
import group_4.galaxyMyAdmin.Models.Activity;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Models.Ship;
import group_4.galaxyMyAdmin.Services.ActivityServiceImpl;
import group_4.galaxyMyAdmin.Services.MissionServiceImpl;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;
import group_4.galaxyMyAdmin.Services.ShipServiceImpl;

@Controller
public class MissionsController {

    @Autowired
    MissionServiceImpl missionService;

    @Autowired
    private PilotServiceImpl pilotService;

    @Autowired
    private ShipServiceImpl shipService;

    @Autowired
    private ActivityServiceImpl activityService;

    @GetMapping("/missions")
    public String getMissions(Model model) {
        List<Mission> allMissions = new ArrayList<>(missionService.findAll());

        // Filtre les missions par statut
        List<Mission> ongoingMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._ONGOING)
            .collect(Collectors.toList());
        List<Mission> succededMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._SUCCESS)
            .collect(Collectors.toList());
        List<Mission> failureMissions = allMissions.stream()
            .filter(mission -> mission.getStatus() == MissionStatus._FAIL)
            .collect(Collectors.toList());

        // Ajoute des missions filtrées au modèle
        model.addAttribute("ongoingMissions", ongoingMissions);
        model.addAttribute("succededMissions", succededMissions);
        model.addAttribute("failureMissions", failureMissions);

        return "missions";
    }

    @GetMapping("/missions/{id}")
    public String getMissionDetails(@PathVariable Long id, Model model) {
        Mission mission = missionService.findById(id);
        model.addAttribute("mission", mission);

        List<Activity> activities = new ArrayList<>(mission.getActivities());
        model.addAttribute("activities", activities);

        return "mission-details";
    }

    @GetMapping("/missions/new")
    public String showMissionForm(Model model) {
        model.addAttribute("mission", new Mission());

        // Récupère des pilotes et vaisseaux opérationnels et disponibles
        List<Pilot> operationalPilots = pilotService.findByStatus(PilotStatus._OPE).stream()
            .filter(Pilot::isAvailable)
            .collect(Collectors.toList());
        List<Ship> operationalShips = shipService.findByStatus(ShipStatus._OPE).stream()
            .filter(Ship::isAvailable)
            .collect(Collectors.toList());

        model.addAttribute("operationalPilots", operationalPilots);
        model.addAttribute("operationalShips", operationalShips);

        return "mission-form";
    }

    @PostMapping("/missions/new")
    public String createMission(@Valid @ModelAttribute("mission") Mission mission,
                                BindingResult result,
                                @RequestParam List<Long> pilotIds,
                                @RequestParam List<Long> shipIds,
                                Model model) {
        if (result.hasErrors()) {
            // Recharge les pilotes et vaisseaux opérationnels et disponibles en cas d'erreurs
            List<Pilot> operationalPilots = pilotService.findByStatus(PilotStatus._OPE).stream()
                .filter(Pilot::isAvailable)
                .collect(Collectors.toList());
            List<Ship> operationalShips = shipService.findByStatus(ShipStatus._OPE).stream()
                .filter(Ship::isAvailable)
                .collect(Collectors.toList());
    
            model.addAttribute("operationalPilots", operationalPilots);
            model.addAttribute("operationalShips", operationalShips);
    
            return "mission-form";
        }
    
        // Sauvegarde d'abord la mission pour obtenir un ID
        missionService.save(mission);
    
        // Pour chaque combinaison de pilote et vaisseau, crée une activité et l'enregistre individuellement
        for (Long pilotId : pilotIds) {
            Pilot pilot = pilotService.findById(pilotId);
            for (Long shipId : shipIds) {
                Ship ship = shipService.findById(shipId);
                
                // Crée une nouvelle activité pour chaque combinaison de pilote et vaisseau
                Activity activity = new Activity();
                activity.setPilot(pilot);
                activity.setShip(ship);
                activity.setMission(mission);  // Associe chaque activité à la mission
    
                // Sauvegarde chaque activité individuellement
                activityService.save(activity);
            }
        }
    
        return "redirect:/missions";
    }
    

}
