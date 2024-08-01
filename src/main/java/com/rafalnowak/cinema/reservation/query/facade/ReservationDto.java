package com.rafalnowak.cinema.reservation.query.facade;

import java.util.List;

public record ReservationDto(
        Integer id,
        String reservationNumber,
        List<SeatDto>seats
) {}
