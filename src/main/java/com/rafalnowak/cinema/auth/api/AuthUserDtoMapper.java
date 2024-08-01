package com.rafalnowak.cinema.auth.api;

import com.rafalnowak.cinema.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthUserDtoMapper {

    @Mapping(target="password", constant = "######")
    AuthUserDto toDto(User domain);

}