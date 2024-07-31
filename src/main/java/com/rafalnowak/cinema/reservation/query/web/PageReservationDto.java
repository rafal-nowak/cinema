package com.rafalnowak.cinema.reservation.query.web;

import java.util.List;

public record PageReservationDto(
        List<ReservationDto> reservations,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
