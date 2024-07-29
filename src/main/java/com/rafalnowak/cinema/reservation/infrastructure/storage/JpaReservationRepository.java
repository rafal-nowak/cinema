package com.rafalnowak.cinema.reservation.infrastructure.storage;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.user.infrastructure.storage.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByReservationNumber(String reservationNumber);
}
