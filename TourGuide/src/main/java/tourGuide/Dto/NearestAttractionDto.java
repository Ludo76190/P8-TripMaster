package tourGuide.Dto;


import gpsUtil.location.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NearestAttractionDto {

    private String attractionName;
    private Location attractionLocation;
    private double distanceUserToAttraction;
    private int rewardPoints;

}
