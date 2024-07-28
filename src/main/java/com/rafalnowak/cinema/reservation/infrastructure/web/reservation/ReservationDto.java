package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import java.util.List;

public record ReservationDto(
        Integer id,
        List<SeatDto>seats
) {}
