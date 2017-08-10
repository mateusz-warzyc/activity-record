package de.gast.activityrecord.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.Route;
import de.gast.activityrecord.repository.ActivityRepository;
import de.gast.activityrecord.repository.RouteRepository;

@Service
public class ActivityRecordServiceImpl implements ActivityRecordService{

    private final ActivityRepository activityRepository;
    private final RouteRepository routeRepository;
    
    @Autowired
    public ActivityRecordServiceImpl(ActivityRepository activityRepository, RouteRepository routeRepository) {
		this.activityRepository = activityRepository;
		this.routeRepository = routeRepository;
    }
    
    @Override
    @Transactional
    public void saveActivityRecord(String sessionId, String clientIp, String domain, String path,
	    String hostName, String hostIp) {

	Activity activity = activityRepository.findBySessionIdAndClientIp(sessionId, clientIp);
	
	Date currentDate = new Date();
	
	if (activity == null) {
	    activity = new Activity();
	    activity.setBegin(currentDate);
	    setActivityRecordMainData(sessionId, clientIp, activity, currentDate);
	} else {
	    setActivityRecordMainData(sessionId, clientIp, activity, currentDate);
	}
	
	Route route = new Route();
	route.setActivity(activity);
	route.setDomain(domain);
	route.setHostIp(hostIp);
	route.setHostName(hostName);
	route.setPath(path);
	route.setTimeStamp(currentDate);

	activityRepository.save(activity);	
	routeRepository.save(route);
    }

    private void setActivityRecordMainData(String sessionId, String clientIp, Activity activity, Date currentDate) {
	activity.setSessionId(sessionId);
	activity.setClientIp(clientIp);
	activity.setCounter(activity.getCounter() + 1);
	activity.setLast(new Date());
    }
}
