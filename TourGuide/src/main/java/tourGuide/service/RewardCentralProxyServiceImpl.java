package tourGuide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuide.proxies.RewardCentralProxy;

/**
 * see REwardCentralProxy for more information
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RewardCentralProxyServiceImpl implements RewardCentralProxyService {

    private final RewardCentralProxy rewardCentralProxy;

    @Override
    public int getAttractionRewardPoints(String attractionId, String userId) {

        return rewardCentralProxy.getAttractionRewardPoints(attractionId, userId);

    }
}
