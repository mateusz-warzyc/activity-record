package de.gast.activityrecord;

import de.gast.activityrecord.entity.Activity;
import de.gast.activityrecord.entity.Route;
import de.gast.activityrecord.repository.ActivityRepository;
import de.gast.activityrecord.repository.RouteRepository;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import static de.gast.activityrecord.TestDataProducer.*;
import static org.junit.Assert.*;

/**
 * Created by mateusz-warzyc.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ActivityRecordApplicationTest {

    @Value("${server.contextPath}")
    private String contextPath;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private RouteRepository routeRepository;

    @After
    public void cleanUp() {
        routeRepository.deleteAll();
        activityRepository.deleteAll();
    }

    @Test
    public void activityShouldBeCreated() {
        //given

        //when
        RestAssured.given().basePath(contextPath)
                .param("sessionId", SESSION_ID).param("clientIp", CLIENT_IP)
                .param("domain", DOMAIN).param("path", PATH)
                .param("hostName", HOST_NAME).param("hostIp", HOST_IP)
                .when().get("save")
                .then().assertThat().statusCode(200);

        //then
        Activity activity = activityRepository.findBySessionIdAndClientIp(SESSION_ID, CLIENT_IP);
        assertNotNull(activity);
    }

    @Test
    public void shouldNotCreateActivityDueToInvalidValues() {
        //given
        String invalidValue = null;

        //when
        RestAssured.given().basePath(contextPath)
                .param("sessionId", SESSION_ID).param("clientIp", CLIENT_IP)
                .param("domain", invalidValue).param("path", PATH)
                .param("hostName", HOST_NAME).param("hostIp", HOST_IP)
                .when().get("save")
                .then().assertThat().statusCode(200);

        //then
        Activity activity = activityRepository.findBySessionIdAndClientIp(SESSION_ID, CLIENT_IP);
        assertNull(activity);
    }
    @Test
    public void deleteShouldRemoveActivityIfLastIsBeforeGivenDate() throws Exception {
        //given
        Date tomorrow = Date.from(ZonedDateTime.now().plusDays(1).toInstant());
        DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        String formattedDate = formatter.format(tomorrow);

        Activity deleteCandidate = activityRepository.save(TestDataProducer.prepareTestActivity(SESSION_ID, CLIENT_IP, new Date(), COUNTER));
        Route routeToDelete = routeRepository.save(TestDataProducer.prepareTestRoute(deleteCandidate, DOMAIN, HOST_IP, HOST_NAME, PATH, new Date()));

        Activity activityToStay = activityRepository.save(TestDataProducer.prepareTestActivity(SESSION_ID, CLIENT_IP, tomorrow, COUNTER));
        Route routeToStay = routeRepository.save(TestDataProducer.prepareTestRoute(activityToStay, DOMAIN, HOST_IP, HOST_NAME, PATH, tomorrow));

        //when
        RestAssured.given().log().all()
                .basePath(contextPath)
                .param("domain", DOMAIN).param("date", formattedDate)
                .when().delete("delete")
                .then().assertThat().statusCode(200);

        //then
        assertFalse(routeRepository.exists(routeToDelete.getId()));
        assertFalse(activityRepository.exists(deleteCandidate.getId()));

        assertTrue(routeRepository.exists(routeToStay.getId()));
        assertTrue(activityRepository.exists(activityToStay.getId()));
    }


}