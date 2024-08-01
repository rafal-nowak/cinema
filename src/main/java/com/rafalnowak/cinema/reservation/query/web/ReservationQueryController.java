package com.rafalnowak.cinema.reservation.query.web;


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


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/reservations",
        produces = "application/json",
        consumes = "application/json"
)
class ReservationQueryController {

    private final ReservationFacade reservationFacade;

    @GetMapping( path = "/{reservationNumber}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String reservationNumber) {
        return ResponseEntity.ok(reservationFacade.findByReservationNumber(reservationNumber));
    }

    @GetMapping
    public ResponseEntity<PageReservationDto> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(reservationFacade.findAll(pageable));
    }

}
