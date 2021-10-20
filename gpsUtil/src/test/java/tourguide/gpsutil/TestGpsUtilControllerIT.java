package tourguide.gpsutil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestGpsUtilControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUp() {
        Locale.setDefault(Locale.US);
    }

    @Test
    public void getUserLocation_forUUID_OkIsReturn() throws Exception {
        UUID userUUID = UUID.randomUUID();
        mockMvc.perform(get("/userLocation?userId=" + userUUID))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserLocation_forUUIDUnformated_errorIsReturn() throws Exception {
        String userUUID = "1234567890";
        mockMvc.perform(get("/userLocation?userId=" + userUUID))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAttractions_noData_OkIsReturn() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk());
    }

}