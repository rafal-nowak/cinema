package com.rafalnowak.cinema.reservation.command.domain;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    void remove(Integer id);

    Optional<Reservation> findByReservationNumber(String reservationNumber);

    PageReservation findAll(Pageable pageable);

}