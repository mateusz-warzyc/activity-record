package de.gast.activityrecord.service;

import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.Route;

/**
 * Created by mateusz-warzyc.
 */
public interface LogEntryService {

    void saveDeleteActivityEntry(Activity activity);

    void saveDeleteRouteEntry(Route route);
}
