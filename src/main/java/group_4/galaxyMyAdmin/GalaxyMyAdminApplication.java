package group_4.galaxyMyAdmin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import group_4.galaxyMyAdmin.Services.ActivityServiceImpl;
import group_4.galaxyMyAdmin.Services.MissionServiceImpl;
import group_4.galaxyMyAdmin.Services.PilotServiceImpl;
import group_4.galaxyMyAdmin.Services.ShipServiceImpl;
import group_4.galaxyMyAdmin.Services.VehiculeServiceImpl;

@SpringBootApplication
public class GalaxyMyAdminApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(GalaxyMyAdminApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
	
	@Bean
	public ActivityServiceImpl activityService(){
		return new ActivityServiceImpl();
	}

	@Bean
	public MissionServiceImpl missionService(){
		return new MissionServiceImpl();
	}

	@Bean
	public PilotServiceImpl pilotService(){
		return new PilotServiceImpl();
	}

	@Bean
	public ShipServiceImpl shipService(){
		return new ShipServiceImpl();
	}

	@Bean
	public VehiculeServiceImpl vehiculeService() {
		return new VehiculeServiceImpl();
	}
}
