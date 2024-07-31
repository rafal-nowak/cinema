package com.rafalnowak.cinema.reservation.query.facade;

import com.rafalnowak.cinema.reservation.command.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaQueryReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByReservationNumber(String reservationNumber);
}
