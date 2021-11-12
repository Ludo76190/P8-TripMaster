package tourGuide;

import com.jsoniter.output.JsonStream;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tourGuide.Dto.UserPreferencesDto;
import tourGuide.beans.Provider;
import tourGuide.beans.VisitedLocation;
import tourGuide.service.TourGuideService;
import tourGuide.user.User;

import java.util.List;

@RestController
public class TourGuideController {

    @Autowired
    TourGuideService tourGuideService;

    @GetMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }

    @ApiOperation(value = "Get the most recent location for a user")
    @GetMapping("/getLocation")
    public String getLocation(@RequestParam String userName) {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(getUser(userName));
        return JsonStream.serialize(visitedLocation.location);
    }

    //  TODO: Change this method to no longer return a List of Attractions.
    //  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
    //  Return a new JSON object that contains:
    // Name of Tourist attraction,
    // Tourist attractions lat/long,
    // The user's location lat/long,
    // The distance in miles between the user's location and each of the attractions.
    // The reward points for visiting each Attraction.
    //    Note: Attraction reward points can be gathered from RewardsCentral
    @ApiOperation(value = "Get the closest five tourist attractions to the user whatever the distance")
    @GetMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam String userName) {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(getUser(userName));
        return JsonStream.serialize(tourGuideService.getNearByAttractions(visitedLocation));
    }

    @ApiOperation(value = "Get the list of user's rewards")
    @GetMapping("/getRewards")
    public String getRewards(@RequestParam String userName) {
        return JsonStream.serialize(tourGuideService.getUserRewards(getUser(userName)));
    }

    @ApiOperation(value = "Get a list of every user's most recent location")
    @GetMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
        // TODO: Get a list of every user's most recent location as JSON
        //- Note: does not use gpsUtil to query for their current location,
        //        but rather gathers the user's current location from their stored location history.
        //
        // Return object should be the just a JSON mapping of userId to Locations similar to:
        //     {
        //        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371}
        //        ...
        //     }

        return JsonStream.serialize(tourGuideService.getAllUsersCurrentLocation());
    }

    @ApiOperation(value = "Get a list of of trip deals for a given user")
    @GetMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName) {
        List<Provider> providers = tourGuideService.getTripDeals(getUser(userName));
        return JsonStream.serialize(providers);
    }

    private User getUser(String userName) {
        return tourGuideService.getUser(userName);
    }

    @ApiOperation(value = "Set given user preferences for the user")
    @PutMapping("/update/Preferences")
    public String updatePreferences(@RequestParam String userName, @RequestBody UserPreferencesDto userPreferencesDTO) {
        return JsonStream.serialize(new UserPreferencesDto(userName, tourGuideService.userUpdatePreferences(userName, userPreferencesDTO)));
    }

}