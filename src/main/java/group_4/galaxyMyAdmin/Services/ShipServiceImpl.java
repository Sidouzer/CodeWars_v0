package group_4.galaxyMyAdmin.Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Ship;
import group_4.galaxyMyAdmin.Repositories.ShipRepository;

public class ShipServiceImpl implements Service<Ship>{

    @Autowired
    ShipRepository shipRepo;

    @Override
    public Collection<Ship> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ship findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Ship obj) {
        // TODO Auto-generated method stub
        
    }

    
}
