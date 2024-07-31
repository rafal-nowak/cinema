package com.rafalnowak.cinema.reservation.command.domain;

import java.util.List;

public interface BookingPolicy {
    void bookSeats(Reservation reservation, Integer userId, List<Integer> seatNumbers);
}
