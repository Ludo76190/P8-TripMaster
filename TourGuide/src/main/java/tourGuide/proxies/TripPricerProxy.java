package tourGuide.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tourGuide.beans.Provider;

import java.util.List;
import java.util.UUID;

/**
 * FeignClient interface for tripPricer proxy on port 8084
 */

@FeignClient(name = "tripPricer", url="http://localhost:8083")
public interface TripPricerProxy {

    @GetMapping(value ="/getPrice")
    List<Provider> getPrice(@RequestParam String apiKey, @RequestParam UUID attractionId, @RequestParam int adults, @RequestParam int children, @RequestParam int nightsStay, @RequestParam int rewardsPoints);

    @GetMapping(value ="/getProviderName")
    String getProviderName(@RequestParam String apiKey,@RequestParam int adults);
}
