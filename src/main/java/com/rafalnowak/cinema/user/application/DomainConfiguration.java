package com.rafalnowak.cinema.user.application;

import com.rafalnowak.cinema.user.domain.EncodingService;
import com.rafalnowak.cinema.user.domain.UserRepository;
import com.rafalnowak.cinema.user.infrastructure.storage.JpaUserRepository;
import com.rafalnowak.cinema.user.infrastructure.storage.UserEntityMapper;
import com.rafalnowak.cinema.user.infrastructure.storage.UserStorageAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ConfigurationProperties("domain.properties")
public class DomainConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository, UserEntityMapper mapper) {
        return new UserStorageAdapter(jpaUserRepository, mapper);
    }

//    @Bean
//    public UserService userService(UserRepository userRepository, EncodingService encoder, Clock clock)  {
//        return new UserService(userRepository, encoder, clock);
//    }

}
