package tourGuide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.beans.Provider;
import tourGuide.proxies.TripPricerProxy;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TripPricerProxyServiceImpl implements TripPricerProxyService {

    private final TripPricerProxy tripPricerProxy;

    @Override
    public List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) {
        return tripPricerProxy.getPrice(apiKey, attractionId,  adults,  children,  nightsStay,  rewardsPoints);
    }

    @Override
    public String getProviderName(String apiKey, int adults) {
        return tripPricerProxy.getProviderName(apiKey, adults);
    }
}
