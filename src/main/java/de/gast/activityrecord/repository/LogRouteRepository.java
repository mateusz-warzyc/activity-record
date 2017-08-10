package de.gast.activityrecord.repository;

import de.gast.activityrecord.entity.DeleteRouteEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mateusz-warzyc.
 */
public interface LogRouteRepository extends CrudRepository<DeleteRouteEntity, Integer> {
}
