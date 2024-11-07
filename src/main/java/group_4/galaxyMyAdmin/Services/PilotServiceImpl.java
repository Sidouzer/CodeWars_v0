package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Enumerations.PilotStatus;
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
        try {
            return pilRepo.findById(id).get();
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            return null;
        }
    }

    @Override
    public void save(Pilot obj) {
        pilRepo.save(obj);
    }

    public Collection<Pilot> findByStatus(PilotStatus status) {
        return StreamSupport.stream(pilRepo.findByStatus(status).spliterator(), false)
        .collect(Collectors.toList());
} 

}
