package tourguide.rewardcentral.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourguide.rewardcentral.service.RewardCentralService;

import java.util.UUID;

@RestController
public class RewardCentralControler {
    private Logger logger = LoggerFactory.getLogger(RewardCentralControler.class);

    @Autowired
    private RewardCentralService rewardCentralService;

    @GetMapping(value = "/getAttractionRewardPoints")
    public int getAttractionRewardPoints (@RequestParam UUID attractionId, @RequestParam UUID userId) {
            return rewardCentralService.getAttractionRewardPoints(attractionId,userId);

   }

}
