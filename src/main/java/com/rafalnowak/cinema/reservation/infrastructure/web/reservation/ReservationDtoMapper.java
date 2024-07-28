package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {

    ReservationDto toDto(Reservation domain);

    Reservation toDomain(ReservationDto dto);
}