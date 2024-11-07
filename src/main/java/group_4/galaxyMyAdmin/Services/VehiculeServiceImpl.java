package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;


import group_4.galaxyMyAdmin.Models.Vehicule;
import group_4.galaxyMyAdmin.Repositories.VehiculeRepository;

public class VehiculeServiceImpl implements Service<Vehicule>{

    @Autowired
    VehiculeRepository vehiRepo;

    @Override
    public Collection<Vehicule> findAll() {
        return StreamSupport.stream(vehiRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }
        
    
    

    @Override
    public Vehicule findById(Long id) {
        try {
            return vehiRepo.findById(id).get();
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            return null;
        }
                
    }




    @Override
    public void save(Vehicule obj) {
        // TODO Auto-generated method stub
        
    }




}