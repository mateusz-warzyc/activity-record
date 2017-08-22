package de.gast.activityrecord.controller;

import de.gast.activityrecord.dto.SaveActivityRequestDto;
import de.gast.activityrecord.service.ActivityRecordService;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@RestController
@Validated
public class ActivityRecordController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);
    private static final String UTF_8 = "UTF-8";
    
    @Autowired
    private ActivityRecordService activityRecordService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveActivity(@Valid @RequestBody SaveActivityRequestDto request) throws UnsupportedEncodingException {
        //TODO: too many parameters in method signature
        activityRecordService.saveActivityRecord(request.getSessionId(), request.getClientIp(), request.getDomain(),
                    request.getPath(), request.getHostName(), request.getHostIp());
        logger.debug("saved activity record with with ipAddress:{} and clientIp:{}", request.getClientIp(), request.getHostName());
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void deleteActivity(@NotNull @NotEmpty @RequestParam("domain") String domain,
                               @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws UnsupportedEncodingException {
        logger.debug("Requested to delete activity with domain {} and older than {}", domain, date);
        activityRecordService.deleteActivityRecord(URLDecoder.decode(domain, UTF_8), date);
        logger.debug("Deleted routes with domain: {}, and date before: {}", domain, date);
    }
}
