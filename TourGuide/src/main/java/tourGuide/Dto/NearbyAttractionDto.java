package tourGuide.Dto;

import gpsUtil.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NearbyAttractionDto {

    private Location userLocation;
    private List<NearestAttractionDto> closestAttractionsList;

}
