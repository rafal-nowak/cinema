package com.rafalnowak.cinema;

import com.rafalnowak.cinema.reservation.application.ReservationService;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
public class DefaultReservations implements CommandLineRunner {

    private final ReservationService reservationService;

    public DefaultReservations(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) {
        try {
            createReservationWithAmountOfSeats(10);
            createReservationWithAmountOfSeats(5);
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }

    private void createReservationWithAmountOfSeats(Integer amountOfSeats) throws Exception {
        reservationService.create(amountOfSeats);
    }
}
