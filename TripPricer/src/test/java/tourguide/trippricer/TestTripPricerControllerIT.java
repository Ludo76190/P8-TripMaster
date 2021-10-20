package tourguide.trippricer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestTripPricerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPrice_correctInput_OkIsReturn() throws Exception {

        String apiKey="api-key-test";
        UUID attractionId=UUID.randomUUID();
        int adults=2;
        int children=2;
        int nightsStay=7;
        int rewardsPoints=10;

        mockMvc.perform(get("/getPrice?apiKey="+apiKey+"&attractionId=" + attractionId+"&adults=" + adults+"&children=" + children+"&nightsStay=" + nightsStay+"&rewardsPoints=" + rewardsPoints))
                .andExpect(status().isOk());
    }

    @Test
    public void getProviderName_correctInput_OkIsReturn() throws Exception {

        String apiKey="test-server-api-key";
        int adults=2;

        mockMvc.perform(get("/getProviderName?apiKey="+apiKey+"&adults=" + adults))
                .andExpect(status().isOk());
    }
}
