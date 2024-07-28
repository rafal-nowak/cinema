package com.rafalnowak.cinema.reservation.application;

import com.rafalnowak.cinema.reservation.domain.PageReservation;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.reservation.domain.ReservationFactory;
import com.rafalnowak.cinema.reservation.domain.ReservationNotFoundException;
import com.rafalnowak.cinema.reservation.domain.ReservationRepository;
import com.rafalnowak.cinema.user.domain.EncodingService;
import com.rafalnowak.cinema.user.domain.PageUser;
import com.rafalnowak.cinema.user.domain.User;
import com.rafalnowak.cinema.user.domain.UserNotFoundException;
import com.rafalnowak.cinema.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation create(Integer amountOfSeats) {
        return reservationRepository.save(ReservationFactory.createReservation(amountOfSeats));
    }

    public void removeById(Integer id) {
        reservationRepository.remove(id);
    }

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
    }

    public PageReservation findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }
}