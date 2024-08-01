package com.rafalnowak.cinema.reservation.query.facade;

import java.util.List;

public record ReservationDto(
        String reservationNumber,
        List<SeatDto>seats
) {}
