package tourGuide;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tourGuide.Dto.NearbyAttractionDto;
import tourGuide.Dto.RecentUserLocationDto;
import tourGuide.Dto.UserPreferencesDto;
import tourGuide.beans.Provider;
import tourGuide.beans.VisitedLocation;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.*;
import tourGuide.user.User;
import tourGuide.user.UserPreferences;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTourGuideService {

    @Autowired
    private GpsUtilProxyService gpsUtil;

    @Autowired
    private RewardCentralProxyService rewardCentral;

    @Autowired
    private TourGuideService tourGuideService;

    @Test
    public void getUserLocation() {
        //GpsUtilProxyService gpsUtil = new GpsUtilProxyServiceImpl();
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = tourGuideService.trackUserLocation(user);
        tourGuideService.tracker.stopTracking();
        assertEquals(visitedLocation.userId, user.getUserId());
    }

    @Test
    public void addUser() {
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

        tourGuideService.addUser(user);
        tourGuideService.addUser(user2);

        User retrivedUser = tourGuideService.getUser(user.getUserName());
        User retrivedUser2 = tourGuideService.getUser(user2.getUserName());

        tourGuideService.tracker.stopTracking();

        assertEquals(user, retrivedUser);
        assertEquals(user2, retrivedUser2);
    }

    @Test
    public void getAllUsers() {
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

        tourGuideService.addUser(user);
        tourGuideService.addUser(user2);

        List<User> allUsers = tourGuideService.getAllUsers();

        tourGuideService.tracker.stopTracking();

        assertTrue(allUsers.contains(user));
        assertTrue(allUsers.contains(user2));
    }

    @Test
    public void trackUser() {
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = tourGuideService.trackUserLocation(user);

        tourGuideService.tracker.stopTracking();

        assertEquals(user.getUserId(), visitedLocation.userId);
    }

    @Test
    public void getNearbyAttractions() {
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = tourGuideService.trackUserLocation(user);

        NearbyAttractionDto attractions = tourGuideService.getNearByAttractions(visitedLocation);

        tourGuideService.tracker.stopTracking();

        assertEquals(5, attractions.getClosestAttractionsList().size());
    }

    @Test
    public void getTripDeals() {
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);

        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        UserPreferencesDto userPreferencesDTO = new UserPreferencesDto();
        userPreferencesDTO.setAttractionProximity(10000);
        userPreferencesDTO.setCurrency("USD");
        userPreferencesDTO.setLowerPricePoint(100);
        userPreferencesDTO.setHighPricePoint(3000);
        userPreferencesDTO.setTripDuration(7);
        userPreferencesDTO.setTicketQuantity(4);
        userPreferencesDTO.setNumberOfAdults(2);
        userPreferencesDTO.setNumberOfChildren(2);
        UserPreferences userPreferences = new UserPreferences(userPreferencesDTO);
        user.setUserPreferences(userPreferences);

        tourGuideService.addUser(user);

        List<Provider> providers = tourGuideService.getTripDeals(user);

        tourGuideService.tracker.stopTracking();

        assertEquals(5, providers.size());
    }

    @Test
    public void userUpdatePreferences() {
        UserPreferencesDto userPreferencesDTO = new UserPreferencesDto();
        userPreferencesDTO.setNumberOfAdults(2);
        userPreferencesDTO.setTripDuration(3);
        userPreferencesDTO.setCurrency("USD");

        UUID userUUID = UUID.fromString("098c2423-879e-52f2-01d2-f73eb8d04840");
        User user = new User(userUUID, "internalUserTest", "0600000000", "internalUsertest@test.com");

        user.setUserPreferences(new UserPreferences(userPreferencesDTO));
        Assert.assertEquals(user.getUserPreferences().getNumberOfAdults(), userPreferencesDTO.getNumberOfAdults());
    }

    @Test
    public void getAllCurrentUserLocations() {
        //GpsUtilProxyService gpsUtil = new GpsUtilProxyServiceImpl();
        RewardsService rewardsService = new RewardsService(gpsUtil, rewardCentral);
        InternalTestHelper.setInternalUserNumber(0);
        TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

        int nbUsers = tourGuideService.getAllUsers().size();

        List<RecentUserLocationDto> recentUserLocationDtos = tourGuideService.getAllUsersCurrentLocation();

        assertEquals(recentUserLocationDtos.size(), nbUsers);
    }

}
