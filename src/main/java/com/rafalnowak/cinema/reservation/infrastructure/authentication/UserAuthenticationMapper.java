package com.rafalnowak.cinema.reservation.infrastructure.authentication;

import com.rafalnowak.cinema.reservation.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {

    User toReservationContext(com.rafalnowak.cinema.user.domain.User user);

}
