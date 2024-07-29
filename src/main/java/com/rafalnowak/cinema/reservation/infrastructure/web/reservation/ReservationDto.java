package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import java.util.List;

public record ReservationDto(
        Integer id,
        String reservationNumber,
        List<SeatDto>seats
) {}
