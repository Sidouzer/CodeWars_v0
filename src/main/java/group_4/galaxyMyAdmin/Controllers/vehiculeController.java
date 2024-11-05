package group_4.galaxyMyAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group_4.galaxyMyAdmin.Services.VehiculeServiceImpl;


@Controller
public class vehiculeController {
    
    @Autowired
    VehiculeServiceImpl vehiculesService;
    

 
 
    @GetMapping("/vehicules")
    public String getPage(Model model) {
        model.addAttribute("vehicules", vehiculesService.findAll());
        return "vehicules";
    }

}






    


