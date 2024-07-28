package com.rafalnowak.cinema.reservation.domain;

public class ReservationFactory {
    public static Reservation createReservation(Integer amountOfSeats) {
        Reservation reservation = new Reservation(amountOfSeats);
        return reservation;
    }
}
