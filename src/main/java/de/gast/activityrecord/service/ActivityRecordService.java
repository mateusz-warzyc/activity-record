package de.gast.activityrecord.service;

import java.util.Date;

public interface ActivityRecordService {

    void saveActivityRecord(String sessionId, String clientIp, String domain, String path, String hostName, String hostIp);

    void deleteActivityRecord(String domain, Date date);
}
