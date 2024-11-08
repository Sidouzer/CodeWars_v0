package group_4.galaxyMyAdmin.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import group_4.galaxyMyAdmin.Models.Activity;

@Service
public class MissionActivitiesService {

    private final List<Activity> missionActivities = new ArrayList<>();

    public void addActivity(Activity activity) {
        missionActivities.add(activity);
    }

    public List<Activity> getMissionActivities() {
        return new ArrayList<>(missionActivities); 
    }

    public void clearMissionActivities() {
        missionActivities.clear();
    }

}
