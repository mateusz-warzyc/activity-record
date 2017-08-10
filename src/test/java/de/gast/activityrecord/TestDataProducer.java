package de.gast.activityrecord;

import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.Route;

import java.util.Date;

/**
 * Created by mateusz-warzyc.
 */
public class TestDataProducer {

    public static final String SESSION_ID = "sessionId";
    public static final String CLIENT_IP = "clientIp";
    public static final String DOMAIN = "domain";
    public static final String PATH = "path";
    public static final String HOST_NAME = "hostName";
    public static final String HOST_IP = "hostIp";
    public static final String DATE_PATTERN = "YYYY-MM-dd";
    public static final Integer COUNTER = 1;

    public static Activity prepareTestActivity(String sessionId, String clientIp, Date lastDate, Integer counter) {
        Activity activity = new Activity();
        activity.setSessionId(sessionId);
        activity.setClientIp(clientIp);
        activity.setCounter(counter);
        activity.setLast(lastDate);
        return activity;
    }

    public static Route prepareTestRoute(Activity activity, String domain, String hostIp, String hostName, String path, Date currentDate) {
        Route route = new Route();
        route.setActivity(activity);
        route.setDomain(domain);
        route.setHostIp(hostIp);
        route.setHostName(hostName);
        route.setPath(path);
        route.setTimeStamp(currentDate);
        return route;
    }
}
