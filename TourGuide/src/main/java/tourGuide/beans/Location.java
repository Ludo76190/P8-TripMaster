package tourGuide.beans;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Use by proxies
 */
@Getter
@Setter
@AllArgsConstructor
public class Location {
    public double longitude;
    public double latitude;

    public Location() {
    }

}
