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

import group_4.galaxyMyAdmin.Enumerations.ShipStatus;
import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Models.Ship;
import group_4.galaxyMyAdmin.Models.Vehicule;
import group_4.galaxyMyAdmin.Services.ShipServiceImpl;
import group_4.galaxyMyAdmin.Services.VehiculeServiceImpl;

@Controller
public class ShipsController {

    @Autowired
    ShipServiceImpl shipsService;

    @Autowired
    VehiculeServiceImpl vehiculeService;

    @GetMapping("/ships")
    public String getShipList(@RequestParam(value = "status", required = false) List<String> status, Model model) {
        List<Ship> ships = shipsService.findAll().stream().collect(Collectors.toList());

        List<Ship> filteredShips = (status == null || status.isEmpty()) ? ships
                : ships.stream().filter(ship -> status.contains(ship.getStatus().toString()))
                        .collect(Collectors.toList());

        Vehicule vehicule = new Vehicule("Model X", "SpaceX", 2025, 2026);
        vehiculeService.save(vehicule);
        Ship shipTest = new Ship(1L, ShipStatus._OPE, vehicule);
        shipsService.save(shipTest);

        model.addAttribute("ships", filteredShips);
        model.addAttribute("status", status);
        return "ships";
    }

    @GetMapping("/ships/{id}")
    public String getMethodName(@PathVariable Long id, Model model) {
        Ship ship = shipsService.findById(id);
        model.addAttribute("ship", ship);
        List<Mission> missions = new ArrayList<>();
        try {
            ship.getActivities().forEach(activity -> {
                missions.add(activity.getMission());
            });
            model.addAttribute("shipMissions", missions);
        } catch (NullPointerException | UnsupportedOperationException | IllegalArgumentException ex) {
            model.addAttribute("error", "Something went wrong, please refresh the page");
        }
        return "shipInfo";
    }

    @GetMapping("/ships/new")
    public String showCreateShipAndModelForm(Model model) {
        model.addAttribute("ship", new Ship());
        model.addAttribute("vehicule", new Vehicule());
        model.addAttribute("statuses", ShipStatus.values());
        model.addAttribute("vehicules", vehiculeService.findAll());
        return "formNewShip";
    }

    // Traiter la création d'un nouveau vaisseau
    @PostMapping("/ships/new")
    public String createShip(@ModelAttribute("ship") Ship ship) {
        shipsService.save(ship);  // Sauvegarder le vaisseau avec statut et type
        return "redirect:/ships";  // Redirige vers la liste des vaisseaux
    }

    // Traiter la création d'un nouveau modèle de vaisseau
    @PostMapping("/models/new")
    public String createVehicule(@ModelAttribute("vehicule") Vehicule vehicule) {
        vehiculeService.save(vehicule);  // Sauvegarder le modèle avec nom et description
        return "redirect:/models";  // Redirige vers la liste des modèles
    }
}

