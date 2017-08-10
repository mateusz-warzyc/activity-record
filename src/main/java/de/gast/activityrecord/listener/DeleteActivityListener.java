package de.gast.activityrecord.listener;

import de.gast.activityrecord.configuration.SpringApplicationContext;
import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.service.LogEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostRemove;
import javax.persistence.PreRemove;

/**
 * Created by mateusz-warzyc.
 */
public class DeleteActivityListener {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteActivityListener.class);

    @PreRemove
    public void preRemoved(Object toDelete) {
        LOGGER.info("Attempting to delete activity: {}", toDelete.toString());
    }

    @PostRemove
    public void onRemoved(Object deleted) {
        LogEntryService logEntryService = SpringApplicationContext.getBean(LogEntryService.class);
        logEntryService.saveDeleteActivityEntry((Activity) deleted);
        LOGGER.info("Activity deleted: {}", deleted.toString());
    }
}
