package group_4.galaxyMyAdmin.Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import group_4.galaxyMyAdmin.Models.Mission;
import group_4.galaxyMyAdmin.Repositories.MissionRepository;

public class MissionServiceImpl implements Service<Mission>{

    @Autowired
    MissionRepository misRepo;

    @Override
    public Collection<Mission> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mission findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Mission obj) {
        // TODO Auto-generated method stub
        
    }

    

}
