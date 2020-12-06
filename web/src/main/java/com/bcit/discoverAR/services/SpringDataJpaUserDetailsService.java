package com.bcit.discoverAR.services;

import com.bcit.discoverAR.auth.UserPrincipal;
import com.bcit.discoverAR.models.ApplicationUser;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {
    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    public SpringDataJpaUserDetailsService(ApplicationUserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        ApplicationUser user = this.userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email"
                        + usernameOrEmail));
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        ApplicationUser user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }

    public ApplicationUser getCurrentUser(Long id) {
        ApplicationUser user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return user;
    }
}
