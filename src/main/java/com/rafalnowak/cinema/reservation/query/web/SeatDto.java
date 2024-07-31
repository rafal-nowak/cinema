package com.rafalnowak.cinema.reservation.query.web;

public record SeatDto(
        Integer seatNumber,
        Integer takenBy
) {}
