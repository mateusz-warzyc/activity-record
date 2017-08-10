package de.gast.activityrecord.repository;

import org.springframework.data.repository.CrudRepository;

import de.gast.activityrecord.entity.Activity;

public interface ActivityRepository  extends CrudRepository<Activity, Integer>{

    Activity findBySessionIdAndClientIp(String sessionId, String clientIp);
}
