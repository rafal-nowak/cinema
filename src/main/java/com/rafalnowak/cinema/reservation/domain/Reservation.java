package com.rafalnowak.cinema.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Reservation {
    Integer id;
    List<Seat> seats = new ArrayList<>();

    Reservation(final Integer amountOfSeats) {
        for (int i = 0; i < amountOfSeats; i++) {
            seats.add(new Seat(i + 1, null));
        }
    }

    public void bookSeats(Integer userId, List<Integer> seatNumbers) {
        for (Integer seatNumber : seatNumbers) {
            Seat seat = findSeat(seatNumber);
            if (seat == null) {
                throw new SeatNotFoundException();
            }
            seat.takeBy(userId);
        }
    }

    public void releaseSeats(List<Integer> seatNumbers) {
        for (Integer seatNumber : seatNumbers) {
            Seat seat = findSeat(seatNumber);
            if (seat == null) {
                throw new SeatNotFoundException();
            }
            seat.release();
        }
    }

    private Seat findSeat(Integer seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }

        return null;
    }

    public Integer getId() {
        return id;
    }
}
