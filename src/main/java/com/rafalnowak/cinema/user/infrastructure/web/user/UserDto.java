package com.rafalnowak.cinema.user.infrastructure.web.user;

import java.time.ZonedDateTime;

public record UserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
