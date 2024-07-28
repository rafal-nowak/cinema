package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import java.util.List;

public record PageReservationDto(
        List<ReservationDto> reservations,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
