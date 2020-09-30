package com.bcit.discoverAR.api.controllers.it;

import com.bcit.discoverAR.api.controllers.AuthHelper;
import com.bcit.discoverAR.api.controllers.JourneyController;
import com.bcit.discoverAR.auth.JwtAuthenticationEntryPoint;
import com.bcit.discoverAR.auth.JwtTokenProvider;
import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.models.Journey;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import com.bcit.discoverAR.repository.JourneyRepository;
import com.bcit.discoverAR.services.SpringDataJpaUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = JourneyController.class, secure = false) // TODO: Use non-deprecated method
public class JourneyControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @MockBean
    private SpringDataJpaUserDetailsService customUserDetailsService;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private ApplicationUserRepository userRepository;

    @MockBean
    private JourneyRepository journeyRepository;

    @MockBean
    private AuthHelper authHelper;

    private final String userName = "Mehdi";
    private final Long userId = 1L;
    private final Long journeyId = 1L;

    private String fakeToken;
    private UserDetails userDetails;
    private ApplicationUser user;
    private Journey journey;

    @Before
    public void setUp() {
        fakeToken = "Bearer foobar";
        userDetails = mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn(userName);

        user = new ApplicationUser();
        user.setId(userId);
        journey = new Journey("real");
        journey.setUser(user);
        journey.setId(journeyId);

        Mockito.when(authHelper.getUserDetails(fakeToken)).thenReturn(userDetails);
        Mockito.when(authHelper.getUser(fakeToken)).thenReturn(user);

        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(journeyRepository.save(any(Journey.class))).thenReturn(journey);
        Mockito.when(journeyRepository.findByUserIdAndId(user.getId(), journeyId)).thenReturn(Optional.of(journey));
    }

    @Test
    public void whenCreatingJourney_thenReturnJsonWithId() throws Exception {
        mvc.perform(post("/api/journeys/create")
                .header("Authorization", fakeToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(journeyId));
    }

    @Test
    public void whenGetAllUserJourneys_thenReturnArrayOfJourneys() throws Exception {
        List<Journey> journeys = new ArrayList<>();
        journeys.add(journey);

        Mockito.when(journeyRepository.findByUserId(userId)).thenReturn(Optional.of(journeys));

        mvc.perform(get("/api/journeys/viewAll")
                .header("Authorization", fakeToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void whenGettingJourneyId_thenReturnJourneyObj() throws Exception {
        mvc.perform(get("/api/journeys/" + journeyId)
                .header("Authorization", fakeToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("real"));
    }

    @Test
    public void whenUpdatingJourney_thenReturnUpdatedObj() throws Exception {
        Journey newJourney = journey;
        newJourney.setTitle("Test");

        mvc.perform(put("/api/journeys/update/" + journeyId)
                .header("Authorization", fakeToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(newJourney)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }

}
