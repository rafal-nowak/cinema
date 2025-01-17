package com.rafalnowak.cinema.reservation.command.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
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
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "reservation_number_unique",
                        columnNames = "reservationNumber"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
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

    @Column
    String reservationNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Seat> seats = new ArrayList<>();

    @Version
    private Integer version;

    @Transient
    BookingPolicy bookingPolicy;

    @Transient
    ReleasingPolicy releasingPolicy;

    Reservation(final String reservationNumber, Integer amountOfSeats) {
        this.reservationNumber = reservationNumber;
        for (int i = 0; i < amountOfSeats; i++) {
            seats.add(new Seat(i + 1, null));
        }
    }

    public void bookSeats(Integer userId, List<Integer> seatNumbers) {
        if (bookingPolicy == null) {
            throw new IllegalStateException("Booking policy not set");
        }

        bookingPolicy.bookSeats(this, userId, seatNumbers);
    }

    public void releaseSeats(Integer userId, List<Integer> seatNumbers) {
        if (releasingPolicy == null) {
            throw new IllegalStateException("Releasing policy not set");
        }

        releasingPolicy.releaseSeats(this, userId, seatNumbers);
    }

    Seat findSeat(Integer seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat;
            }
        }

        return null;
    }

}
