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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody CreateCommand bookCommand){
        reservationService.create(bookCommand.reservationNumber(), bookCommand.amountOfSeats());
        return ResponseEntity.ok().build();
    }

    @GetMapping( path = "/{reservationNumber}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String reservationNumber) {
        Reservation reservation = reservationService.findByReservationNumber(reservationNumber);
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

    @DeleteMapping("{reservationNumber}")
    public ResponseEntity<Void> removeReservation(@PathVariable String reservationNumber){
        reservationService.removeByReservationNumber(reservationNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{reservationNumber}/book")
    public ResponseEntity<Void> bookSeats(@PathVariable String reservationNumber, @RequestBody BookCommand bookCommand){
        reservationService.bookSeats(reservationNumber, bookCommand.seatNumbers());
        return ResponseEntity.ok().build();
    }

    @PostMapping("{reservationNumber}/release")
    public ResponseEntity<Void> bookSeats(@PathVariable String reservationNumber, @RequestBody ReleaseCommand releaseCommand){
        reservationService.releaseSeats(reservationNumber, releaseCommand.seatNumbers());
        return ResponseEntity.ok().build();
    }

}
