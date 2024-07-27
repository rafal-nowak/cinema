package com.rafalnowak.cinema.user.infrastructure.web.auth;


import com.rafalnowak.cinema.user.infrastructure.web.user.UserDto;

public record AuthenticationResponse(
        String token,
        UserDto userDto
) {
}
