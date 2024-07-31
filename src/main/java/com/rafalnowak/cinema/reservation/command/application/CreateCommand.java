package com.rafalnowak.cinema.reservation.command.application;

public record CreateCommand(
        String reservationNumber,
        Integer amountOfSeats
) {}
