package group_4.galaxyMyAdmin.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group_4.galaxyMyAdmin.Enumerations.PilotRank;
import group_4.galaxyMyAdmin.Enumerations.PilotStatus;
import group_4.galaxyMyAdmin.Enumerations.Race;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;
import group_4.galaxyMyAdmin.Tools.PilotCreationValidator;
import group_4.galaxyMyAdmin.Tools.PilotUpdater;



@Controller
@RequestMapping("/pilots")
public class PilotsController {

    @Autowired
    PilotServiceImpl pilotService;

    @Autowired
    PilotCreationValidator pilotValidator;

    @Autowired
    PilotUpdater pilotUpdater;

    @GetMapping("")
    public String getPilotsList(@RequestParam(value = "status", required = false) List<String> status, Model model) {
        List<Pilot> pilots = pilotService.findAll().stream().collect(Collectors.toList());
        List<Pilot> filteredPilots = (status == null || status.isEmpty()) ? pilots : 
            pilots.stream().filter(pilot -> status.contains(pilot.getStatus().toString()))
            .collect(Collectors.toList());
            
        Pilot pilotTest = new Pilot("test", "testLastName", Race._HUMAN, LocalDate.now(), 10, PilotStatus._OPE, PilotRank._CAPTAIN);
		pilotService.save(pilotTest);

        Pilot pilotTest2 = new Pilot("ploup", "testLastName", Race._HUMAN, LocalDate.now(), 10, PilotStatus._OPE, PilotRank._APPRENTICE);
		pilotService.save(pilotTest2);

        model.addAttribute("filteredPilots", filteredPilots);
        
        return "pilots";
    }
    
    @GetMapping("/{id}")
    public String getMethodName(@PathVariable Long id, Model model) {
        Pilot pilot = pilotService.findById(id);
        model.addAttribute("pilot", pilot);
        List<Mission> missions = new ArrayList<>();
        try {
            pilot.getActivities().forEach(activity -> {
                missions.add(activity.getMission());
            });
            model.addAttribute("pilotMissions", missions);
        } catch (NullPointerException | UnsupportedOperationException | 
                 IllegalArgumentException ex) {
            model.addAttribute("error", "Something went wrong, please refresh page");
        }
        return "pilotInfo";
    }

    @GetMapping("/new")
    public String getMethodName(Model model) {
        model.addAttribute("pilot", new Pilot());
        model.addAttribute("pilotRaces", Race.values());
        return "pilotCreation";
    }
    
    @PostMapping("/new")
    public String createPilot(@ModelAttribute Pilot pilot,BindingResult result, Model model) {
        pilotValidator.validate(pilot, result);
        if (result.hasErrors()) {
            model.addAttribute("pilotRaces", Race.values());
            return "pilotCreation";
        }
        pilotUpdater.initialize(pilot);
        pilotService.save(pilot);
        return "redirect:/pilots/" + pilot.getId();
    }
    
    

}
