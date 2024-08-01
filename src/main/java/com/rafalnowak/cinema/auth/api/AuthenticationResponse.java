package com.rafalnowak.cinema.auth.api;


import com.rafalnowak.cinema.user.api.UserDto;

public record AuthenticationResponse(
        String token,
        UserDto userDto
) {
}
