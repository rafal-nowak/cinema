package com.rafalnowak.cinema.reservation.infrastructure.storage;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface JpaReservationRepository extends JpaRepository<Reservation, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Reservation> findByReservationNumber(String reservationNumber);
}
