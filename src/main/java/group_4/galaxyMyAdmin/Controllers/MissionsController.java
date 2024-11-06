package group_4.galaxyMyAdmin.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/missions/{id}")
    public String getMissionDetails(@PathVariable Long id, Model model) {
        // Récupère la mission par son ID
        Mission mission = missionService.findById(id);
        
        // Ajoute la mission au modèle
        model.addAttribute("mission", mission);
        
        // Récupère les activités associées pour obtenir les pilots et vaisseaux
        List<Activity> activities = mission.getActivities().stream().collect(Collectors.toList());

        // Ajoute les activités au modèle pour l'affichage dans la vue
        model.addAttribute("activities", activities);

        // Retourner la vue des détails de la mission
        return "mission-details";
}

        // Affiche le formulaire de création de mission
    @GetMapping("/missions/new")
    public String showMissionForm(Model model) {
        model.addAttribute("mission", new Mission());
            
        // Récupère les pilotes opérationnels
        List<Pilot> operationalPilots = (List<Pilot>) pilotService.findByStatus(PilotStatus._OPE);

        // Filtre pour ne garder que ceux qui sont disponibles
        List<Pilot> availableOperationalPilots = operationalPilots.stream()
            .filter(Pilot::isAvailable) 
            .collect(Collectors.toList());

        // Récupère les vaisseaux opérationnels
        List<Ship> operationalShips = shipService.findByStatus(ShipStatus._OPE);

        // Filtre pour ne garder que les vaisseaux disponibles
        List<Ship> availableOperationalShips = operationalShips.stream()
            .filter(Ship::isAvailable)
            .collect(Collectors.toList());

        // Ajoute les pilotes et vaisseaux filtrés au modèle
        model.addAttribute("operationalPilots", availableOperationalPilots);
        model.addAttribute("operationalShips", availableOperationalShips);

        return "mission-form";
        }

    // Gère la soumission du formulaire de création de mission
    @PostMapping("/missions/new")
    public String createMission(@ModelAttribute("mission") Mission mission,
                                @RequestParam Long pilotId,
                                @RequestParam Long shipId) {
        // Associe le pilote et le vaisseau sélectionnés à la mission
        Pilot pilot = pilotService.findById(pilotId);
        Ship ship = shipService.findById(shipId);
        
        mission.setPilot(pilot);
        mission.setShip(ship);
        
        // Enregistre la mission
        missionService.save(mission);
        
        return "redirect:/missions"; // Redirige vers la liste des missions
    }
}

