package com.rafalnowak.cinema.reservation.infrastructure.web;

import com.rafalnowak.cinema.reservation.domain.ReservationAlreadyExistsException;
import com.rafalnowak.cinema.reservation.domain.ReservationNotFoundException;
import com.rafalnowak.cinema.reservation.domain.SeatAlreadyTakenException;
import com.rafalnowak.cinema.reservation.domain.SeatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
class ReservationCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleSeatNotFoundException(SeatNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatAlreadyTakenException.class)
    public final ResponseEntity<ErrorResponse> handleSeatAlreadyTakenException(SeatAlreadyTakenException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReservationAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleReservationAlreadyExistsException(ReservationAlreadyExistsException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<ErrorResponse> handleCommandNotSupportedException(IOException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    private <E extends RuntimeException> ResponseEntity<ErrorResponse> buildResponse(E exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }

}