package tourguide.rewardcentral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewardCentral.RewardCentral;

import java.util.UUID;

@Service
public class RewardCentralServiceImpl implements RewardCentralService {

    @Autowired
    private RewardCentral rewardCentral;

    public int getAttractionRewardPoints(UUID attractionId, UUID userId) {

        return rewardCentral.getAttractionRewardPoints(attractionId,userId);
    }
}
