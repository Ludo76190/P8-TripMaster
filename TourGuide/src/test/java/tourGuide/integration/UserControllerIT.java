package tourGuide.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tourGuide.Dto.UserPreferencesDto;
import tourGuide.helper.InternalTestHelper;;
import tourGuide.user.User;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void putUpdatePreferencesITest() throws Exception {

        UserPreferencesDto userPreferencesDTO = new UserPreferencesDto();
        userPreferencesDTO.setNumberOfAdults(2);
        userPreferencesDTO.setTripDuration(3);
        userPreferencesDTO.setNumberOfChildren(2);
        userPreferencesDTO.setCurrency("USD");

        UUID userUUID = UUID.fromString("098c2423-879e-52f2-01d2-f73eb8d04840");
        User user = new User(userUUID, "internalUserTest", "0600000000",
                "internalUserTest0@test.com");

        InternalTestHelper.setInternalUserNumber(1);

        String userName = "internalUser0";
        String questionBody = "{\n" +
                "\"attractionProximity\": 10000,\n" +
                "\"currency\": \"USD\",\n" +
                "\"lowerPricePoint\": 100.0,\n" +
                "\"highPricePoint\": 3000.0,\n" +
                "\"tripDuration\": 7,\n" +
                "\"ticketQuantity\": 4,\n" +
                "\"numberOfAdults\": 2,\n" +
                "\"numberOfChildren\": 2\n" +
                "}";

        MvcResult result = mockMvc.perform(put("/update/Preferences")
                        .param("userName", userName)
                        .content(questionBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotEquals(userPreferencesDTO.getNumberOfAdults(), user.getUserPreferences().getNumberOfAdults());
    }
}