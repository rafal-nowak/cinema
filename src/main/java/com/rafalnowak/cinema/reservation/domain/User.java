package com.rafalnowak.cinema.reservation.domain;

public record User(
        Integer id,
        UserRole role
) {}