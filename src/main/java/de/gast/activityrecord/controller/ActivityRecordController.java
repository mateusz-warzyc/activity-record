package de.gast.activityrecord.controller;

import de.gast.activityrecord.dto.SaveActivityRequestDto;
import de.gast.activityrecord.service.ActivityRecordService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Objects;

@RestController
public class ActivityRecordController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);
    private static final String UTF_8 = "UTF-8";
    
    @Autowired
    private ActivityRecordService activityRecordService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveActivity(@RequestBody SaveActivityRequestDto request) {

    if (StringUtils.isNotBlank(request.getSessionId()) && StringUtils.isNotBlank(request.getClientIp())
            && StringUtils.isNotBlank(request.getDomain()) &&
        StringUtils.isNotBlank(request.getPath()) && StringUtils.isNotBlank(request.getHostName()) && StringUtils.isNotBlank(request.getHostIp())) {

        try {
            activityRecordService.saveActivityRecord(URLDecoder.decode(request.getSessionId(), UTF_8),
            URLDecoder.decode(request.getClientIp(), UTF_8), URLDecoder.decode(request.getDomain(), UTF_8),
            URLDecoder.decode(request.getPath(), UTF_8), URLDecoder.decode(request.getHostName(), UTF_8), URLDecoder.decode(request.getHostIp(),UTF_8));
            logger.debug("saved activity record with with ipAddress:{} and clientIp:{}",request.getClientIp(),request.getHostName());
        } catch (Exception e) {
            logger.error("failed to save activity record with ipAddress:{} and clientIp:{}",request.getClientIp(),request.getHostName(),e);
        }
    } else {
        logger.error("one or some of the parameters is missing, nothing will be saved into activity record");
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void deleteActivity(@RequestParam("domain") String domain,
                               @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        logger.debug("Requested to delete activity with domain {} and older than {}", domain, date);

        if(StringUtils.isNotBlank(domain) && Objects.nonNull(date)) {
            try {
                activityRecordService.deleteActivityRecord(URLDecoder.decode(domain, UTF_8), date);
                logger.debug("Deleted routes with domain: {}, and date before: {}", domain, date);
            } catch (UnsupportedEncodingException e) {
                logger.error("Failed to delete routes with domain: {}, and date before: {}", domain, date);
            }
        } else {
            logger.error("One or more parameters are missing or wrong. The request has been ignored");
        }
    }
}
