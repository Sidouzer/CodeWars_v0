package group_4.galaxyMyAdmin.Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Vehicule;
import group_4.galaxyMyAdmin.Repositories.VehiculeRepository;

public class VehiculeServiceImpl implements Service<Vehicule>{

    @Autowired
    VehiculeRepository vehiRepo;

    @Override
    public Collection<Vehicule> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vehicule findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Vehicule obj) {
        // TODO Auto-generated method stub
        
    }

    

}
