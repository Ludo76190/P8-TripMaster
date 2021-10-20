package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tourGuide.beans.Attraction;
import tourGuide.beans.VisitedLocation;

import java.util.List;
import java.util.UUID;

/**
 * FeignClient interface for GpsUtil proxy on port 8081
 */
@FeignClient(name = "gpsUtil", url = "http://localhost:8081")
public interface GpsUtilProxy {

    /**
     * call GpsUtil proxy for getUserLocation
     *
     * @param userId uuid User
     * @return Visited location class
     */
    @GetMapping(value = "/userLocation")
    VisitedLocation getUserLocation(@RequestParam UUID userId);

    /**
     * call GpsUtil proxy for getattraction
     *
     * @return all attractions
     */
    @GetMapping(value = "/attractions")
    List<Attraction> getAttractions();

}
