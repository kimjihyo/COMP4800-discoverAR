package com.bcit.discoverAR.services.it;

import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import com.bcit.discoverAR.services.SpringDataJpaUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SpringDataJpaUserDetailsServiceIntegrationTest {
    @TestConfiguration
    static class SpringDataJpaUserDetailsServiceTestContextConfiguration {
        @Bean
        public UserDetailsService userDetailsService(ApplicationUserRepository applicationUserRepository) {
            return new SpringDataJpaUserDetailsService(applicationUserRepository);
        }
    }

    @Autowired
    private SpringDataJpaUserDetailsService userDetailsService;

    @MockBean
    private ApplicationUserRepository applicationUserRepository;

    private ApplicationUser user;

    @Before
    public void setUp() {
        user = new ApplicationUser("foobar", "foobar@email.com", "password");
        Mockito.when(applicationUserRepository.findByUsernameOrEmail(user.getUsername(), user.getUsername())).thenReturn(Optional.of(user));
        Mockito.when(applicationUserRepository.findByUsernameOrEmail(user.getEmail(), user.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(applicationUserRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    public void whenValidUsername_thenApplicationUserShouldBeFound() {
        UserDetails details = userDetailsService.loadUserByUsername("foobar");
        assertEquals(details.getUsername(), "foobar");
    }

    @Test
    public void whenValidEmail_thenApplicationUserShouldBeFound() {
        UserDetails details = userDetailsService.loadUserByUsername("foobar@email.com");
        assertEquals(details.getUsername(), "foobar");
    }

    @Test
    public void whenValidId_thenApplicationUserShouldBeFound() {
        UserDetails details = userDetailsService.loadUserById(user.getId());
        assertEquals(details.getUsername(), user.getUsername());
    }
}
