package com.rafalnowak.cinema.reservation.command.domain;

public record User(
        Integer id,
        UserRole role
) {}