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
import group_4.galaxyMyAdmin.Models.Activity;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Models.Ship;
import group_4.galaxyMyAdmin.Models.Vehicule;
import group_4.galaxyMyAdmin.Services.MissionServiceImpl;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;
import group_4.galaxyMyAdmin.Services.ShipServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MissionsController {

    @Autowired
    MissionServiceImpl missionService;

    @Autowired
    private PilotServiceImpl pilotService;

    @Autowired
    private ShipServiceImpl shipService;

/*     @GetMapping("/missions/create-test")
    public String createTestMission() {
        // Créer une mission d'exemple
        Mission mission = new Mission();
        mission.setTitle("Opération Alpha");
        mission.setDescription("Mission d'infiltration secrète dans le territoire ennemi.");
        mission.setFlightHours(5);
        mission.setStatus(MissionStatus._ONGOING);

        // Créer des pilotes et vaisseaux d'exemple
        Pilot pilot1 = new Pilot();
        pilot1.setFirstname("Luke");
        pilot1.setLastname("Skywalker");
        
        Pilot pilot2 = new Pilot();
        pilot1.setFirstname("Han");
        pilot1.setLastname("Solo");
        
        Ship ship1 = new Ship();
        ship1.setId((long) 11223344);

        Ship ship2 = new Ship();
        ship2.setId((long)99999);

        // Associe les pilotes et les vaisseaux à la mission via des activités
        Activity activity1 = new Activity();
        activity1.setMission(mission);
        activity1.setPilot(pilot1);
        activity1.setShip(ship1);

        Activity activity2 = new Activity();
        activity2.setMission(mission);
        activity2.setPilot(pilot2);
        activity2.setShip(ship2);

        // Ajouter les activités à la mission
        mission.setActivities(Set.of(activity1, activity2));

        // Sauvegarde la mission
        missionService.save(mission);

        return "redirect:/missions"; // Redirige vers la liste des missions pour vérifier l'ajout
}
 */
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
        
        // Récupère la liste des pilotes opérationnels et libres
        List<Pilot> operationalPilots = pilotService.findOperationalAndFreePilots();
        model.addAttribute("operationalPilots", operationalPilots);

        // Récupère la liste des vaisseaux opérationnels
        List<Ship> operationalShips = shipService.findOperationalShips();
        model.addAttribute("operationalShips", operationalShips);

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

