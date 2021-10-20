package tourGuide.service;

import tourGuide.beans.Attraction;
import tourGuide.beans.VisitedLocation;

import java.util.List;
import java.util.UUID;

public interface GpsUtilProxyService {

    VisitedLocation getUserLocation(UUID userId);

    List<Attraction> getAttractions();

}
