package com.rafalnowak.cinema.user.infrastructure.web.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
