package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Pilot;
import group_4.galaxyMyAdmin.Repositories.PilotRepository;

public class PilotServiceImpl implements Service<Pilot>{

    @Autowired
    PilotRepository pilRepo;

    @Override
    public Collection<Pilot> findAll() {
            return StreamSupport.stream(pilRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Pilot findById(Long id) {
        return pilRepo.findById(id).get();
    }

    @Override
    public void save(Pilot obj) {
        // TODO Auto-generated method stub
        
    }

    

}
