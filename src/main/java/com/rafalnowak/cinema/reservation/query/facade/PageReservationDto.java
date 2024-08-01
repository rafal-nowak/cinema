package com.rafalnowak.cinema.reservation.query.facade;

import java.util.List;

public record PageReservationDto(
        List<ReservationDto> reservations,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
