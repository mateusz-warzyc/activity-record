package de.gast.activityrecord.repository;

import de.gast.activityrecord.entity.DeleteActivityEntry;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mateusz-warzyc.
 */
public interface LogActivityRepository extends CrudRepository<DeleteActivityEntry, Integer> {
}
