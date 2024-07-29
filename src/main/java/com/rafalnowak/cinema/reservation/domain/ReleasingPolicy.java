package com.rafalnowak.cinema.reservation.domain;

import java.util.List;

public interface ReleasingPolicy {
    void releaseSeats(Reservation reservation, Integer userId, List<Integer> seatNumbers);
}
