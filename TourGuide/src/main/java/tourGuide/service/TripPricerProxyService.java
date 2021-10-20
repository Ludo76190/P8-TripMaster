package tourGuide.service;

import tourGuide.beans.Provider;

import java.util.List;
import java.util.UUID;

public interface TripPricerProxyService {

    List<Provider> getPrice(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints);

    String getProviderName(String apiKey, int adults);
}
