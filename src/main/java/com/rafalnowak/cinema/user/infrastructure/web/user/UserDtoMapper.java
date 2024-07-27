package com.rafalnowak.cinema.user.infrastructure.web.user;

import com.rafalnowak.cinema.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target="password", constant = "######")
    UserDto toDto(User domain);

    User toDomain(UserDto dto);
}