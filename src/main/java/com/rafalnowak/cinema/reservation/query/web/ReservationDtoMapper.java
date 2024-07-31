package com.rafalnowak.cinema.reservation.query.web;

import com.rafalnowak.cinema.reservation.domain.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {

    ReservationDto toDto(Reservation domain);

}