package com.rafalnowak.cinema.reservation.command.infrastructure.web.reservation;

import com.rafalnowak.cinema.reservation.command.application.BookCommand;
import com.rafalnowak.cinema.reservation.command.application.CreateCommand;
import com.rafalnowak.cinema.reservation.command.application.ReleaseCommand;
import com.rafalnowak.cinema.reservation.command.application.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/reservations",
        produces = "application/json",
        consumes = "application/json"
)
class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody CreateCommand createCommand){
        reservationService.create(createCommand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{reservationNumber}")
    public ResponseEntity<Void> removeReservation(@PathVariable String reservationNumber){
        reservationService.removeByReservationNumber(reservationNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{reservationNumber}/book")
    public ResponseEntity<Void> bookSeats(@PathVariable String reservationNumber, @RequestBody BookCommand bookCommand){
        reservationService.bookSeats(reservationNumber, bookCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{reservationNumber}/release")
    public ResponseEntity<Void> releaseSeats(@PathVariable String reservationNumber, @RequestBody ReleaseCommand releaseCommand){
        reservationService.releaseSeats(reservationNumber, releaseCommand);
        return ResponseEntity.ok().build();
    }

}