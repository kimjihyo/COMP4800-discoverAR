package com.bcit.discoverAR.repository.it;

import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationUserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    private ApplicationUser mainUser;

    @Before
    public void setUp() {
        mainUser = new ApplicationUser("foobar", "foobar@email.com", "password");
        entityManager.persist(mainUser);
        entityManager.flush();
    }

    @Test
    public void whenFindByEmail_thenReturnApplicationUser() {
        ApplicationUser found = applicationUserRepository.findByEmail(mainUser.getEmail()).get();
        assertEquals(found, mainUser);
    }

    @Test
    public void whenFindByUsername_thenReturnApplicationUser() {
        ApplicationUser found = applicationUserRepository.findByUsername(mainUser.getUsername()).get();
        assertEquals(found, mainUser);
    }

    @Test
    public void whenFindByUsernameOrEmail_thenReturnApplicationUser() {
        ApplicationUser foundWithUsername = applicationUserRepository.findByUsernameOrEmail(mainUser.getUsername(), "").get();
        assertEquals(foundWithUsername, mainUser);

        ApplicationUser foundWithEmail = applicationUserRepository.findByUsernameOrEmail("", mainUser.getEmail()).get();
        assertEquals(foundWithEmail, mainUser);
    }

    @Test
    public void whenExistsByEmail_thenReturnBoolean() {
        Boolean isFound = applicationUserRepository.existsByEmail(mainUser.getEmail());
        assertEquals(isFound, true);

        Boolean isNotFound = applicationUserRepository.existsByEmail("");
        assertEquals(isNotFound, false);
    }

    @Test
    public void whenExistsByUsername_thenReturnBoolean() {
        Boolean isFound = applicationUserRepository.existsByUsername(mainUser.getUsername());
        assertEquals(isFound, true);

        Boolean isNotFound = applicationUserRepository.existsByUsername("");
        assertEquals(isNotFound, false);
    }

    @Test
    public void whenFindAll_thenReturnList() {
        List<ApplicationUser> result = new ArrayList<>();
        result.add(mainUser);

        List<ApplicationUser> initialList = applicationUserRepository.findAll();
        assertEquals(initialList, result);

        ApplicationUser secondaryUser = new ApplicationUser("test", "test@email.com", "password");
        entityManager.persist(secondaryUser);
        entityManager.flush();

        result.add(secondaryUser);
        initialList = applicationUserRepository.findAll();
        assertEquals(initialList, result);
    }
}
