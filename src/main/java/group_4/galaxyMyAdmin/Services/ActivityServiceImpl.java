package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Activity;
import group_4.galaxyMyAdmin.Repositories.ActivityRepository;

public class ActivityServiceImpl implements Service<Activity>{

    @Autowired
    ActivityRepository actRepo;

    @Override
    public Collection<Activity> findAll() {
        return StreamSupport.stream(actRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Activity findById(Long id) {
        try {
            return actRepo.findById(id).get();
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            return null;
        }
    }

    @Override
    public void save(Activity obj) {
        actRepo.save(obj);      
    }

    public List<Activity> findByPilot_id(Long id) {
        return actRepo.findByPilot_id(id);
    }

}
