package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

public record SeatDto(
        Integer seatNumber,
        Integer takenBy
) {}
