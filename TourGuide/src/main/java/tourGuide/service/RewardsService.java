package tourGuide.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tourGuide.beans.Attraction;
import tourGuide.beans.Location;
import tourGuide.beans.VisitedLocation;
import tourGuide.user.User;
import tourGuide.user.UserReward;

@Service
public class RewardsService {
    private final Logger logger = LoggerFactory.getLogger(RewardsService.class);
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    // proximity in miles
    private int defaultProximityBuffer = 10;
    private int proximityBuffer = defaultProximityBuffer;
    private int attractionProximityRange = 200;

    @Autowired
    private GpsUtilProxyService gpsUtil;

    @Autowired
    private RewardCentralProxyService rewardsCentral;


    public RewardsService(GpsUtilProxyService gpsUtil, RewardCentralProxyService rewardCentral) {
        this.gpsUtil = gpsUtil;
        this.rewardsCentral = rewardCentral;
    }

    public void setProximityBuffer(int proximityBuffer) {
        this.proximityBuffer = proximityBuffer;
    }

    public void setDefaultProximityBuffer() {
        proximityBuffer = defaultProximityBuffer;
    }

    public void calculateRewards(User user) {
        List<VisitedLocation> userLocations = new CopyOnWriteArrayList<>(user.getVisitedLocations());
        List<Attraction> attractions = gpsUtil.getAttractions();

        for (VisitedLocation visitedLocation : userLocations) {
            for (Attraction attraction : attractions) {
                if(user.getUserRewards().parallelStream().noneMatch(r -> r.attraction.attractionName.equals(attraction.attractionName))) {
                    if (nearAttraction(visitedLocation, attraction)) {
                        user.addUserReward(new UserReward(visitedLocation, attraction, getRewardPoints(attraction, user.getUserId())));
                    }
                }
            }
        }
    }

    private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
        return getDistance(attraction, visitedLocation.location) > proximityBuffer ? false : true;
    }

    int getRewardPoints(Attraction attraction, UUID userId) {
        return rewardsCentral.getAttractionRewardPoints(attraction.attractionId.toString(), userId.toString());
    }

    public double getDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

    public void newCalculateRewards(List<User> userList) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        for (User user : userList) {
            Runnable runnableTask = () -> {
                calculateRewards(user);
            };

            executorService.execute(runnableTask);
        }
        executorService.shutdown();

        try {
            boolean isExecutorFinished = executorService.awaitTermination(20, TimeUnit.MINUTES);
            if (!isExecutorFinished) {
                logger.error("calculateRewards does not finish in 20 minutes elapsed time");
                executorService.shutdownNow();
            } else {
                logger.debug("calculateRewards finished before the 20 minutes elapsed time");
            }
        } catch (InterruptedException e) {
            logger.error("executorService was interrupted : " + e.getLocalizedMessage());
            executorService.shutdownNow();
        }
    }
}
