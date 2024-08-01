package com.rafalnowak.cinema.reservation.query.facade;

public record SeatDto(
        Integer seatNumber,
        Integer takenBy
) {}
