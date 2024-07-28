package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import com.rafalnowak.cinema.reservation.domain.PageReservation;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageReservationDtoMapper {

    @Mapping(target = "reservations", qualifiedByName = "toReservationDtoList")
    PageReservationDto toPageDto(PageReservation domain);

    @Named("toReservationDtoList")
    @IterableMapping(qualifiedByName = "reservationToReservationDto")
    List<ReservationDto> toListDto(List<Reservation> reservations);

    @Named("reservationToReservationDto")
    ReservationDto toDto(Reservation domain);
}