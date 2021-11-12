package tourGuide.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tourGuide.beans.Attraction;
import tourGuide.beans.VisitedLocation;
import tourGuide.service.GpsUtilProxyService;
import tourGuide.service.TripPricerProxyService;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProxiesService {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripPricerProxyService tripPricerProxyService;

    @Autowired
    private GpsUtilProxyService gpsUtilProxyService;

    @Test
    public void TestGetProviderName() {
        String tripPricerApiKey = "test-server-api-key";
        String provider = tripPricerProxyService.getProviderName(tripPricerApiKey,1);
        assertNotEquals("", provider);
        assertNotNull(provider.toString());
    }

    @Test
    public void getAttractions_noData_returnAttraction() {

        List<Attraction> attractions = gpsUtilProxyService.getAttractions();
        assertNotNull(attractions);
        assertNotNull(attractions.get(0).toString());
    }
    @Test
    public void getUserLocation_giveUUIDUser_returnVisitedLocation() {

        VisitedLocation visitedLocation = gpsUtilProxyService.getUserLocation(UUID.randomUUID());
        assertNotNull(visitedLocation.toString());
    }

}
