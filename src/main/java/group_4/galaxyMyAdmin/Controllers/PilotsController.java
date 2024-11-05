package group_4.galaxyMyAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group_4.galaxyMyAdmin.Services.PilotServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PilotsController {

    @Autowired
    PilotServiceImpl piloteService;
    
    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
     return entity;
 }
 
    @GetMapping("/pilots")
    public String getPage(Model model) {
        model.addAttribute("pilotes", piloteService.findAll());
        return "pilots";
    }
    
}
