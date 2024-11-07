package group_4.galaxyMyAdmin.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import group_4.galaxyMyAdmin.Models.Pilot;
import java.util.List;
import group_4.galaxyMyAdmin.Enumerations.PilotStatus;


@RepositoryRestResource(collectionResourceRel = "pilot")
public interface PilotRepository extends CrudRepository <Pilot, Long>{
    List<Pilot> findByStatus(PilotStatus status);
}