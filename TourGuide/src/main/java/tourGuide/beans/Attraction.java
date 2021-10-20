package tourGuide.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Use by proxies
 */
@Getter
@Setter
public class Attraction extends Location {
    public String attractionName;
    public String city;
    public String state;
    public UUID attractionId;

    public Attraction() {
    }

}
