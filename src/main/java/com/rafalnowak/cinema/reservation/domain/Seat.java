package com.rafalnowak.cinema.reservation.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Seat {
    Integer seatNumber;
    Integer takenBy;

    public boolean isTaken() {
        return takenBy != null;
    }

    public void takeBy(Integer userId) {
        if (takenBy != null) {
            throw new SeatAlreadyTakenException();
        }
        takenBy = userId;
    }

    public void release() {
        takenBy = null;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

}
