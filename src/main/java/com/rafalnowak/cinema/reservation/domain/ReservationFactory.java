package com.rafalnowak.cinema.reservation.domain;

public class ReservationFactory {
    public static Reservation createReservation(String reservationNumber, Integer amountOfSeats) {
        Reservation reservation = new Reservation(reservationNumber, amountOfSeats);
        return reservation;
    }
}
