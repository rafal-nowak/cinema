package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import com.rafalnowak.cinema.reservation.application.ReservationService;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.security.JWTUtil;
import com.rafalnowak.cinema.security.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
class ReservationController {

    private final ReservationService reservationService;
    private final ReservationDtoMapper reservationMapper;
    private final PageReservationDtoMapper pageReservationDtoMapper;
    private final JWTUtil jwtUtil;
    private final Security security;

    @GetMapping( path = "/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable Integer id) {
        Reservation reservation = reservationService.findById(id);
        return ResponseEntity
                .ok(reservationMapper.toDto(reservation));
    }

    @GetMapping
    public ResponseEntity<PageReservationDto> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageReservationDto pageReservations = pageReservationDtoMapper.toPageDto(reservationService.findAll(pageable));

        return ResponseEntity.ok(pageReservations);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeReservation(@PathVariable Integer id){
        reservationService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
