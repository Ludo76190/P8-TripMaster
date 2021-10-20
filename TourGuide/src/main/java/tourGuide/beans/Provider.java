package tourGuide.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Provider {
    public String name;
    public double price;
    public UUID tripId;

}
