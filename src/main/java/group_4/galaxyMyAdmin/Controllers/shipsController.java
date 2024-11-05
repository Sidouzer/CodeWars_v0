package group_4.galaxyMyAdmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group_4.galaxyMyAdmin.Services.ShipServiceImpl;

@Controller
public class shipsController {

    @Autowired
    ShipServiceImpl shipsService;
    

 
 
    @GetMapping("/ships")
    public String getPage(Model model) {
        model.addAttribute("ships", shipsService.findAll());
        return "ships";
    }

}

    

