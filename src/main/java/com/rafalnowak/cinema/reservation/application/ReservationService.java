package com.rafalnowak.cinema.reservation.application;

import com.rafalnowak.cinema.reservation.domain.PageReservation;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.reservation.domain.ReservationFactory;
import com.rafalnowak.cinema.reservation.domain.ReservationNotFoundException;
import com.rafalnowak.cinema.reservation.domain.ReservationRepository;
import com.rafalnowak.cinema.reservation.domain.User;
import com.rafalnowak.cinema.user.domain.EncodingService;
import com.rafalnowak.cinema.user.domain.PageUser;
import com.rafalnowak.cinema.user.domain.UserNotFoundException;
import com.rafalnowak.cinema.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AuthenticationService authenticationService;

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

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void bookSeats(Integer reservationId, List<Integer> seatNumbers) {
        User user = authenticationService.getLoggedInUser();
        Reservation reservation = findById(reservationId);
        reservation.bookSeats(user.getId(), seatNumbers);
        reservationRepository.update(reservation);
    }

    public PageReservation findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }
}