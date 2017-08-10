package de.gast.activityrecord.listener;

import de.gast.activityrecord.configuration.SpringApplicationContext;
import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.Route;
import de.gast.activityrecord.service.LogEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostRemove;
import javax.persistence.PreRemove;

/**
 * Created by mateusz-warzyc.
 */
public class DeleteRouteListener {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteRouteListener.class);

    @PreRemove
    public void preRemoved(Object toDelete) {
        LOGGER.info("Attempting to delete route: {}", toDelete.toString());
    }

    @PostRemove
    public void onRemoved(Object deleted) {
        LogEntryService logEntryService = SpringApplicationContext.getBean(LogEntryService.class);
        logEntryService.saveDeleteRouteEntry((Route) deleted);
        LOGGER.info("Route deleted: {}", deleted.toString());
    }
}
