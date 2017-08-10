package de.gast.activityrecord.service;

import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.DeleteActivityEntry;
import de.gast.activityrecord.entity.DeleteRouteEntity;
import de.gast.activityrecord.entity.Route;
import de.gast.activityrecord.repository.LogActivityRepository;
import de.gast.activityrecord.repository.LogRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by mateusz-warzyc.
 */
@Service
public class LogEntryServiceImpl implements LogEntryService {

    @Autowired
    private LogActivityRepository logActivityRepository;

    @Autowired
    private LogRouteRepository logRouteRepository;

    @Override
    public void saveDeleteActivityEntry(Activity activity) {
        DeleteActivityEntry entry = new DeleteActivityEntry();
        entry.setTimestamp(new Date());
        entry.setDescription(activity.toString());
        logActivityRepository.save(entry);
    }

    @Override
    public void saveDeleteRouteEntry(Route route) {
        DeleteRouteEntity entity = new DeleteRouteEntity();
        entity.setTimestamp(new Date());
        entity.setDescription(route.toString());
        entity.setActivityId(route.getActivity().getId());
        logRouteRepository.save(entity);
    }
}
