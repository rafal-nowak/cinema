package com.rafalnowak.cinema.user.infrastructure.storage;

import com.rafalnowak.cinema.user.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);

}
