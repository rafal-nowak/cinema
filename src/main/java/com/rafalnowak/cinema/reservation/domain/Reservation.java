package com.rafalnowak.cinema.reservation.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
//        name = "RESERVATIONS",
)
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation_id_seq",
            sequenceName = "reservation_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_id_seq"
    )
    Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
