package com.rafalnowak.cinema.reservation.infrastructure.storage;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReservationEntityMapper {

    ReservationEntity toEntity(Reservation domain);

    Reservation toDomain(ReservationEntity entity);

}
