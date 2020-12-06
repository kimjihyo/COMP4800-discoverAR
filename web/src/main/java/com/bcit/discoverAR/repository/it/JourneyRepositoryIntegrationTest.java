package com.bcit.discoverAR.repository.it;

import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.models.Journey;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import com.bcit.discoverAR.repository.JourneyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JourneyRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    private ApplicationUser mainUser;

    private Journey mainJourney;

    @Before
    public void setUp() {
        mainUser = new ApplicationUser("foobar", "foobar@email.com", "password");
        mainJourney = new Journey("testJourney");
        mainJourney.setUser(mainUser);
        entityManager.persist(mainUser);
        entityManager.persist(mainJourney);
        entityManager.flush();
    }

    @Test
    public void whenFindByUserId_thenReturnJourneyList() {
        List<Journey> found = journeyRepository.findByUserId(mainUser.getId()).get();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0), mainJourney);
    }

    @Test
    public void whenFindById_thenReturnJourney() {
        Journey found = journeyRepository.findById(mainJourney.getId()).get();
        assertEquals(found, mainJourney);
    }

    @Test
    public void whenFindByUserIdAndId_thenReturnJourney() {
        Journey found = journeyRepository.findByUserIdAndId(mainUser.getId(), mainJourney.getId()).get();
        assertEquals(found, mainJourney);
    }
}
