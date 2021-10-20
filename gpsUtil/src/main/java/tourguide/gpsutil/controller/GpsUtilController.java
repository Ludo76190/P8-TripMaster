package tourguide.gpsutil.controller;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourguide.gpsutil.service.IGpsUtilService;

import java.util.List;
import java.util.UUID;

@RestController
public class GpsUtilController {
    private static final Logger logger = LogManager.getLogger(GpsUtilController.class);

    @Autowired
    private IGpsUtilService gpsUtilService;

    @GetMapping("/userLocation")
    public VisitedLocation getUserLocation(@RequestParam UUID userId) {
        logger.debug("Call to gpsUtil.getUSerLocation(" + userId + ")");
        return gpsUtilService.getUserLocation(userId);
    }

    @GetMapping("/attractions")
    public List<Attraction> getAttractions() {
        logger.debug("Call to gpsUtil.getAttractions()");
        return gpsUtilService.getAttractions();
    }
}
