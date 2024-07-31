package com.rafalnowak.cinema.reservation.application;

public record CreateCommand(
        String reservationNumber,
        Integer amountOfSeats
) {}
