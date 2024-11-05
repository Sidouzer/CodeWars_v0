package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Repositories.MissionRepository;

public class MissionServiceImpl implements Service<Mission>{

    @Autowired
    MissionRepository misRepo;

    @Override
    public Collection<Mission> findAll() {
        // Convertit un Iterable en Collection via StreamSupport
    return StreamSupport.stream(misRepo.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }
    @Override
    public Mission findById(Long id) {
        try {
            return misRepo.findById(id).get(); // Return une mission en fonction de l'id
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public void save(Mission obj) {
        // Enregistre ou met à jour une mission
        misRepo.save(obj);
    }
}
