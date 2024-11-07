package group_4.galaxyMyAdmin.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import group_4.galaxyMyAdmin.Enumerations.ShipStatus;
import group_4.galaxyMyAdmin.Models.Ship;

@RepositoryRestResource(collectionResourceRel = "ship")
public interface ShipRepository extends CrudRepository <Ship, Long>{
    List<Ship> findByStatus(ShipStatus ope);
}