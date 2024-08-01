package com.rafalnowak.cinema.reservation.query.facade;

import com.rafalnowak.cinema.reservation.command.domain.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {

    ReservationDto toDto(Reservation domain);

}