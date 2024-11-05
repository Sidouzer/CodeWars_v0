package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.beans.factory.annotation.Autowired;


import group_4.galaxyMyAdmin.Models.Ship;
import group_4.galaxyMyAdmin.Repositories.ShipRepository;

public class ShipServiceImpl implements Service<Ship>{

    @Autowired
    ShipRepository shipRepo;

    @Override
    public Collection<Ship> findAll() {
        return StreamSupport.stream(shipRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Ship findById(Long id) {
        try {
            return shipRepo.findById(id).get();
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            return null;
        }
                
    }

    @Override
    public void save(Ship obj) {
        shipRepo.save(obj);
       
        
    }

    
}
