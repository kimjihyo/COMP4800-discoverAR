package com.bcit.discoverAR.api.controllers.it;

import com.bcit.discoverAR.api.controllers.AuthController;
import com.bcit.discoverAR.api.controllers.AuthHelper;
import com.bcit.discoverAR.api.payload.request.LoginRequest;
import com.bcit.discoverAR.auth.JwtAuthenticationEntryPoint;
import com.bcit.discoverAR.auth.JwtTokenProvider;
import com.bcit.discoverAR.repository.ApplicationUserRepository;
import com.bcit.discoverAR.repository.RoleRepository;
import com.bcit.discoverAR.services.SpringDataJpaUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
public class AuthControllerIntegrationTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @MockBean
    private SpringDataJpaUserDetailsService customUserDetailsService;

    @MockBean
    private ApplicationUserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private AuthHelper authHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("foobar", "password"))).thenReturn(null);
    }

    @Test
    public void whenSigningInWithValidCredentials_thenReturnJsonObj() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail("foobar");
        loginRequest.setPassword("password");

        String inputJson = super.mapToJson(loginRequest);

        MvcResult mvcResult = mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status= mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        MockHttpServletResponse res = mvcResult.getResponse();

        String content = res.getContentAsString();
        assertEquals("{\"accessToken\":null,\"tokenType\":\"Bearer\"}", content);
    }
}
