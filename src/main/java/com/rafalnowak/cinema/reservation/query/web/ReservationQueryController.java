package com.rafalnowak.cinema.reservation.query.web;


import com.rafalnowak.cinema.reservation.command.domain.Reservation;
import com.rafalnowak.cinema.reservation.query.facade.ReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/reservations",
        produces = "application/json",
        consumes = "application/json"
)
class ReservationQueryController {

    private final ReservationFacade reservationFacade;
    private final ReservationDtoMapper reservationMapper;
    private final PageReservationDtoMapper pageReservationDtoMapper;

    @GetMapping( path = "/{reservationNumber}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String reservationNumber) {
        Optional<Reservation> maybeReservation = reservationFacade.findByReservationNumber(reservationNumber);

        return maybeReservation.map(reservation -> ResponseEntity
                .ok(reservationMapper.toDto(reservation))).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @GetMapping
    public ResponseEntity<PageReservationDto> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageReservationDto pageReservations = pageReservationDtoMapper.toPageDto(reservationFacade.findAll(pageable));

        return ResponseEntity.ok(pageReservations);
    }

}
