package group_4.galaxyMyAdmin.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import group_4.galaxyMyAdmin.Enumerations.PiloteRank;
import group_4.galaxyMyAdmin.Enumerations.PiloteStatus;
import group_4.galaxyMyAdmin.Enumerations.Race;
import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;


@Controller
public class PilotsController {

    @Autowired
    PilotServiceImpl piloteService;
 
    @GetMapping("/pilots")
    public String getPilots(@RequestParam(value = "status", required = false) List<String> status, Model model) {
        List<Pilot> pilots = piloteService.findAll().stream().collect(Collectors.toList());
        List<Pilot> filteredPilots = (status == null || status.isEmpty()) ? pilots : 
            pilots.stream().filter(pilot -> status.contains(pilot.getStatus().toString()))
            .collect(Collectors.toList());
            
        Pilot pilotTest = new Pilot("test", "testLastName", Race._HUMAN, LocalDate.now(), 10, PiloteStatus._OPE, PiloteRank._CAPTAIN);
		piloteService.save(pilotTest);

        Pilot pilotTest2 = new Pilot("ploup", "testLastName", Race._HUMAN, LocalDate.now(), 10, PiloteStatus._OPE, PiloteRank._CAPTAIN);
		piloteService.save(pilotTest2);

        model.addAttribute("filteredPilots", filteredPilots);
        model.addAttribute("status", status);
        
        return "pilots";
    }
    

}
