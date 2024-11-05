package group_4.galaxyMyAdmin.Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Pilote;
import group_4.galaxyMyAdmin.Repositories.PiloteRepository;

public class PiloteServiceImpl implements Service<Pilote>{

    @Autowired
    PiloteRepository pilRepo;

    @Override
    public Collection<Pilote> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pilote findById(Long id) {
        return pilRepo.findById(id).get();
    }

    @Override
    public void save(Pilote obj) {
        // TODO Auto-generated method stub
        
    }

    

}
