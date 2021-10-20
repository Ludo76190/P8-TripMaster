package tourGuide.Dto;

import lombok.Getter;
import lombok.Setter;
import tourGuide.beans.Location;

import java.util.List;

@Getter
@Setter
public class NearbyAttractionDto {

    private Location userLocation;
    private List<NearestAttractionDto> closestAttractionsList;

}
