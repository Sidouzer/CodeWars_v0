package group_4.galaxyMyAdmin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import group_4.galaxyMyAdmin.Services.PilotServiceImpl;

@SpringBootTest
class GalaxyMyAdminApplicationTests {

	@Autowired
	PilotServiceImpl piloteService;

	@Test
	void contextLoads() {
		
	}

	@Test
	void testRun() {

	}

}
