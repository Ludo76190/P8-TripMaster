package tourguide.rewardcentral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestRewardCentralControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUp() {
        Locale.setDefault(Locale.US);
    }

    @Test
    public void getAttractionRewardPoints_forUUID_OkIsReturn() throws Exception {
        UUID userUUID = UUID.randomUUID();
        String userId = userUUID.toString();
        UUID attractionUUID = UUID.randomUUID();
        String attractionId = attractionUUID.toString();

        mockMvc.perform(get("/getAttractionRewardPoints?attractionId="+attractionId+"&userId=" + userId))
                .andExpect(status().isOk());
    }
    @Test
    public void getAttractionRewardPoints_forIncorrectUUID_errorIsReturn() throws Exception {
        String userId = "123456790";
        String attractionId = "123456790";

        mockMvc.perform(get("/getAttractionRewardPoints?attractionId="+attractionId+"&userId=" + userId))
                .andExpect(status().is(400));
    }
}
