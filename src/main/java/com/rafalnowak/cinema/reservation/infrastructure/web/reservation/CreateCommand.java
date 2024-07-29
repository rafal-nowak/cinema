package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

public record CreateCommand(
        String reservationNumber,
        Integer amountOfSeats
) {}
