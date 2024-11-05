package group_4.galaxyMyAdmin.Services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Activity;
import group_4.galaxyMyAdmin.Repositories.ActivityRepository;

public class ActivityServiceImpl implements Service<Activity>{

    @Autowired
    ActivityRepository actRepo;

    @Override
    public Collection<Activity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Activity findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Activity obj) {
        // TODO Auto-generated method stub      
    }

    public List<Activity> findByPilot_id(Long id) {
        return actRepo.findByPilot_id(id);
    }

}
