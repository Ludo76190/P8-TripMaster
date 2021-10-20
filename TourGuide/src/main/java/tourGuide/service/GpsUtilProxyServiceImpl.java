package tourGuide.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tourGuide.beans.Attraction;
import tourGuide.beans.VisitedLocation;
import tourGuide.proxies.GpsUtilProxy;

import java.util.List;
import java.util.UUID;

/**
 * see GpsUtilProxy for more information
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GpsUtilProxyServiceImpl implements GpsUtilProxyService {

    private final GpsUtilProxy gpsUtilProxy;

    @Override
    public VisitedLocation getUserLocation(UUID userId) {
        VisitedLocation visitedLocation = gpsUtilProxy.getUserLocation(userId);
        return visitedLocation;
    }

    @Override
    public List<Attraction> getAttractions() {
        return gpsUtilProxy.getAttractions();
    }
}
