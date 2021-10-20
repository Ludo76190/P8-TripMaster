package tourGuide.Dto;

import tourGuide.beans.Location;

public class RecentUserLocationDto {
    private String userId;
    private Location userLocation;
    private String userName;

    public RecentUserLocationDto(String userId, String userName, Location location) {
        this.userId = userId;
        this.userName = userName;
        this.userLocation = location;
    }
}
