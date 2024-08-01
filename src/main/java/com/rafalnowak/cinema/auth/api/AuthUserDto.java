package com.rafalnowak.cinema.auth.api;

public record AuthUserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
