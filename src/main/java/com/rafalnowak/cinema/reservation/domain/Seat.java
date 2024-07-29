package com.rafalnowak.cinema.reservation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
//        name = "SEATS",
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    Integer seatNumber;
    @Column(
            nullable = true
    )
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
