package de.gast.activityrecord.repository;

import de.gast.activityrecord.entity.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RouteRepository extends CrudRepository<Route, Integer> {

    @Query("SELECT re FROM Route re WHERE re.activity.id = :activityId")
    List<Route> findByActivityId(@Param("activityId") int id);

    @Query("SELECT re FROM Route re WHERE re.domain = :domain AND re.activity.last < :date")
    Set<Route> findRoutesByDomainAndLastActivity(@Param("domain") String domain, @Param("date") Date date);

}
