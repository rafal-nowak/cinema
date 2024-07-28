package com.rafalnowak.cinema.reservation.infrastructure.storage;

import com.rafalnowak.cinema.reservation.domain.SeatAlreadyTakenException;
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
public class SeatEntity {
    @Id
    Integer seatNumber;
    @Column(
            nullable = true
    )
    Integer takenBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatEntity that = (SeatEntity) o;
        return seatNumber.equals(that.seatNumber);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
