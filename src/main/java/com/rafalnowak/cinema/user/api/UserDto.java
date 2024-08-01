package com.rafalnowak.cinema.user.api;

public record UserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
