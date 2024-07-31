package com.rafalnowak.cinema.reservation.query.facade;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageReservation implements Serializable {

    List<Reservation> reservations;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}