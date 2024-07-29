package com.rafalnowak.cinema;


import com.rafalnowak.cinema.reservation.infrastructure.storage.JpaReservationRepository;
import com.rafalnowak.cinema.security.JWTUtil;
import com.rafalnowak.cinema.user.application.UserService;
import com.rafalnowak.cinema.user.domain.User;
import com.rafalnowak.cinema.user.infrastructure.storage.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("it")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CinemaPocApplication.class
)
@ExtendWith(SpringExtension.class)
public class BaseIT {

    @Autowired
    protected Environment environment;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected UserService userService;

    protected BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected JWTUtil jwtUtil;

    @Autowired
    private ServerPortService serverPortService;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaReservationRepository jpaReservationRepository;

    @BeforeEach
    void init() {
        jpaUserRepository.deleteAll();
        jpaReservationRepository.deleteAll();
    }

    protected String localUrl(String endpoint) {
        int port = serverPortService.getPort();
        return "http://localhost:" + port + endpoint;
    }

    protected String getAccessTokenForUser(User user) {
        String token = jwtUtil.issueToken(user.getEmail(), "ROLE_" + user.getRole());
        return "Bearer " + token;
    }

    protected <T, U> ResponseEntity<U> callHttpMethod(
            HttpMethod httpMethod,
            String url,
            String accessToken,
            T body,
            Class<U> mapToObject
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, accessToken);
        headers.add(HttpHeaders.ACCEPT, "application/json");
        HttpEntity<T> requestEntity;
        if (body == null) {
            requestEntity = new HttpEntity<>(headers);
        } else {
            requestEntity = new HttpEntity<>(body, headers);
        }
        return restTemplate.exchange(
                localUrl(url),
                httpMethod,
                requestEntity,
                mapToObject
        );
    }

}
