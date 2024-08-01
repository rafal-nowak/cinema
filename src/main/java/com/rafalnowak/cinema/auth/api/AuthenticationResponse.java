package com.rafalnowak.cinema.auth.api;


public record AuthenticationResponse(
        String token,
        AuthUserDto userDto
) {
}
