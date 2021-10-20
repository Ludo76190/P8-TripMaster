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

}
