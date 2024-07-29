package com.rafalnowak.cinema.reservation.application;

import com.rafalnowak.cinema.reservation.domain.PageReservation;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.reservation.domain.ReservationFactory;
import com.rafalnowak.cinema.reservation.domain.ReservationNotFoundException;
import com.rafalnowak.cinema.reservation.domain.ReservationRepository;
import com.rafalnowak.cinema.reservation.domain.User;
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

    public Reservation create(String reservationNumber, Integer amountOfSeats) {
        return reservationRepository.save(ReservationFactory.createReservation(reservationNumber, amountOfSeats));
    }

    public void removeByReservationNumber(String reservationNumber) {
        Reservation reservation = findByReservationNumber(reservationNumber);
        reservationRepository.remove(reservation.getId());
    }

    public Reservation findByReservationNumber(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void bookSeats(String reservationNumber, List<Integer> seatNumbers) {
        User user = authenticationService.getLoggedInUser();
        Reservation reservation = findByReservationNumber(reservationNumber);
        reservation.bookSeats(user.getId(), seatNumbers);
    }

    @Transactional
    public void releaseSeats(String reservationNumber, List<Integer> seatNumbers) {
        User user = authenticationService.getLoggedInUser();
        Reservation reservation = findByReservationNumber(reservationNumber);
        reservation.releaseSeats(seatNumbers);
    }

    public PageReservation findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }
}