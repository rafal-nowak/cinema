package com.rafalnowak.cinema.reservation.domain;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    void update(Reservation reservation);

    void remove(Integer id);

    Optional<Reservation> findById(Integer id);

    PageReservation findAll(Pageable pageable);

}