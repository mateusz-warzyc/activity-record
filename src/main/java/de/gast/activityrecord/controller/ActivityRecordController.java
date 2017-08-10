package de.gast.activityrecord.controller;

import de.gast.activityrecord.service.ActivityRecordService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
public class ActivityRecordController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);
    
    private static final String UTF_8 = "UTF-8";
    
    @Autowired
    private ActivityRecordService activityRecordService;

    @RequestMapping("/save")
    public void SaveActivity(
	    @RequestParam("sessionId") String sessionId,
	    @RequestParam("clientIp") String clientIp,
	    @RequestParam("domain") String domain,
	    @RequestParam("path") String path,
	    @RequestParam("hostName") String hostName,
	    @RequestParam("hostIp") String hostIp) {

	if (StringUtils.isNotBlank(sessionId) && 
		StringUtils.isNotBlank(clientIp) &&
		StringUtils.isNotBlank(domain) &&
		StringUtils.isNotBlank(path) && 
		StringUtils.isNotBlank(hostName) && 
		StringUtils.isNotBlank(hostIp)) {
	    
	    try {	
		activityRecordService.saveActivityRecord(URLDecoder.decode(sessionId, UTF_8),
			URLDecoder.decode(clientIp, UTF_8), URLDecoder.decode(domain, UTF_8),
			URLDecoder.decode(path, UTF_8), URLDecoder.decode(hostName, UTF_8), URLDecoder.decode(hostIp,UTF_8));
		logger.debug("saved activity record with with ipAddress:{} and clientIp:{}",clientIp,sessionId);	
	    } catch (Exception e) {
		logger.error("failed to save activity record with ipAddress:{} and clientIp:{}",clientIp,sessionId,e);
	    }    
	}else{
	    logger.error("one or some of the parameters is missing, nothing will be saved into activity record");
	    return;
	}
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public void deleteActivity(@RequestParam String domain, @RequestParam String date) {
		logger.debug("Requested to delete activity with domain {} and older than {}", domain, date);
//		validate
//		delete

	}
}
