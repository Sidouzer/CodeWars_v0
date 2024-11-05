package group_4.galaxyMyAdmin.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group_4.galaxyMyAdmin.Enumerations.PiloteRank;
import group_4.galaxyMyAdmin.Enumerations.PiloteStatus;
import group_4.galaxyMyAdmin.Enumerations.Race;
import group_4.galaxyMyAdmin.Models.Activity;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Services.ActivityServiceImpl;
import group_4.galaxyMyAdmin.Services.MissionServiceImpl;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;


@Controller
@RequestMapping("/pilots")
public class PilotsListController {

    @Autowired
    PilotServiceImpl piloteService;

    @Autowired
    ActivityServiceImpl activityService;

    @Autowired
    MissionServiceImpl missionService;


    @GetMapping("")
    public String getPilotsList(@RequestParam(value = "status", required = false) List<String> status, Model model) {
        List<Pilot> pilots = piloteService.findAll().stream().collect(Collectors.toList());
        List<Pilot> filteredPilots = (status == null || status.isEmpty()) ? pilots : 
            pilots.stream().filter(pilot -> status.contains(pilot.getStatus().toString()))
            .collect(Collectors.toList());
            
        Pilot pilotTest = new Pilot("test", "testLastName", Race._HUMAN, LocalDate.now(), 10, PiloteStatus._OPE, PiloteRank._CAPTAIN);
		piloteService.save(pilotTest);

        Pilot pilotTest2 = new Pilot("ploup", "testLastName", Race._HUMAN, LocalDate.now(), 10, PiloteStatus._OPE, PiloteRank._CAPTAIN);
		piloteService.save(pilotTest2);

        model.addAttribute("filteredPilots", filteredPilots);
        
        return "pilots";
    }
    
    @GetMapping("/{id}")
    public String getMethodName(@PathVariable Long id, Model model) {
        model.addAttribute("pilot", piloteService.findById(id));
        List<Activity> activities = activityService.findByPilot_id(id);
        List<Mission> missions = new ArrayList<>();
        try {
            activities.forEach(activity -> {
                missions.add(activity.getMission());
            });
            model.addAttribute("pilotMissions", missions);
        } catch (NullPointerException | UnsupportedOperationException | 
                 IllegalArgumentException ex) {
            model.addAttribute("noMissions", "No missions");
        }
        return "pilotInfo";
    }
    

}
