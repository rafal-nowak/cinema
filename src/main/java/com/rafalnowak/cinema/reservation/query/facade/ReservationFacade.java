package com.rafalnowak.cinema.reservation.query.facade;


import com.rafalnowak.cinema.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public
class ReservationFacade {

    private final JpaQueryReservationRepository reservationRepository;

    public Optional<Reservation> findByReservationNumber(final String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

    public PageReservation findAll(final Pageable pageable) {
        Page<Reservation> pageOfReservationsEntity = reservationRepository.findAll(pageable);
        List<Reservation> reservationsOnCurrentPage = new ArrayList<>(pageOfReservationsEntity.getContent());
        return new PageReservation(
                reservationsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfReservationsEntity.getTotalPages(),
                pageOfReservationsEntity.getTotalElements()
        );
    }
}
