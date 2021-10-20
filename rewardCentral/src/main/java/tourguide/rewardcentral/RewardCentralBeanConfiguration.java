package tourguide.rewardcentral;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewardCentral.RewardCentral;

@Configuration
public class RewardCentralBeanConfiguration {

    @Bean
    public RewardCentral rewardCentral() {
        return new RewardCentral();
    }
}
