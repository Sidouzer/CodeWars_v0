package group_4.galaxyMyAdmin.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import group_4.galaxyMyAdmin.Models.Ship;

@RepositoryRestResource(collectionResourceRel = "ship")
public interface ShipRepository extends CrudRepository <Ship, Long>{

}