package com.rafalnowak.cinema.reservation.command.infrastructure.storage;


import com.rafalnowak.cinema.reservation.command.domain.PageReservation;
import com.rafalnowak.cinema.reservation.command.domain.Reservation;
import com.rafalnowak.cinema.reservation.command.domain.ReservationAlreadyExistsException;
import com.rafalnowak.cinema.reservation.command.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log
public
class ReservationStorageAdapter implements ReservationRepository {

    private final JpaReservationRepository reservationRepository;

    @Override
    public Reservation save(final Reservation reservation) {
        try {
            Reservation saved = reservationRepository.save(reservation);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Reservation with number " + reservation.getReservationNumber() + " already exits in db");
            throw new ReservationAlreadyExistsException();
        }
    }

    @Override
    public void remove(final Integer id) {
        reservationRepository.findById(id).ifPresent(userEntity -> reservationRepository.deleteById(id));
    }

    @Override
    public Optional<Reservation> findByReservationNumber(final String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

}
