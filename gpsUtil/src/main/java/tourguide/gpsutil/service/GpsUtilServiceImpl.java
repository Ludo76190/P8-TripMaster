package tourguide.gpsutil.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class GpsUtilServiceImpl implements IGpsUtilService {

    private static final Logger logger = LogManager.getLogger(GpsUtilServiceImpl.class);

    @Autowired
    private GpsUtil gpsUtil;

    public GpsUtilServiceImpl() {
        Locale.setDefault(Locale.US);
    }

    public VisitedLocation getUserLocation(UUID userId) {
        logger.debug("Call to gpsUtil.getUSerLocation(" + userId + ")");
        return gpsUtil.getUserLocation(userId);
    }

    public List<Attraction> getAttractions() {
        logger.debug("Call to gpsUtil.getAttractions()");
        return gpsUtil.getAttractions();
    }
}
