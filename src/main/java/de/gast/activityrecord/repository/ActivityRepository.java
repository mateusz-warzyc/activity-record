package de.gast.activityrecord.repository;

import org.springframework.data.repository.CrudRepository;

import de.gast.activityrecord.entity.Activity;

import java.util.Set;

public interface ActivityRepository  extends CrudRepository<Activity, Integer>{

    Activity findBySessionIdAndClientIp(String sessionId, String clientIp);

    void deleteByIdIn(Set<Integer> ids);
}
