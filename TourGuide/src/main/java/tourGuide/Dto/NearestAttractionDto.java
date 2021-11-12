package tourGuide.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tourGuide.beans.Location;

@Getter
@Setter
@AllArgsConstructor
public class NearestAttractionDto {

    private String attractionName;
    private Location attractionLocation;
    private double distanceUserToAttraction;
    private int rewardPoints;

/*    public double getDistanceUserToAttraction() {
        return distanceUserToAttraction;
    }

    public NearestAttractionDto(String attractionName, Location attractionLocation, double distanceUserToAttraction, int rewardPoints) {
        this.attractionName = attractionName;
        this.attractionLocation = attractionLocation;
        this.distanceUserToAttraction = distanceUserToAttraction;
        this.rewardPoints = rewardPoints;
    }*/
}
