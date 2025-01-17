package com.rafalnowak.cinema.reservation.command.application;

import com.rafalnowak.cinema.reservation.command.domain.ReservationRepository;
import com.rafalnowak.cinema.reservation.command.infrastructure.storage.JpaReservationRepository;
import com.rafalnowak.cinema.reservation.command.infrastructure.storage.ReservationStorageAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("reservation.domain.properties")
public class ReservationDomainConfiguration {

    @Bean
    public ReservationRepository reservationRepository(JpaReservationRepository jpaUserRepository) {
        return new ReservationStorageAdapter(jpaUserRepository);
    }

}
