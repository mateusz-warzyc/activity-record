package de.gast.activityrecord.service;

public interface ActivityRecordService {

    void saveActivityRecord(String sessionId, String clientIp, String domain, String path, String hostName, String hostIp);
}
