package com.rafalnowak.cinema.auth.api;

public record AuthenticationRequest(
        String username,
        String password
) {
}
