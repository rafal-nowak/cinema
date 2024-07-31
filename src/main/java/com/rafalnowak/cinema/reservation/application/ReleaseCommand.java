package com.rafalnowak.cinema.reservation.application;

import java.util.List;

public record ReleaseCommand(
        List<Integer>seatNumbers,
        Integer userId
) {}
