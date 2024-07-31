package com.rafalnowak.cinema.reservation.command.application;

import java.util.List;

public record ReleaseCommand(
        List<Integer>seatNumbers,
        Integer userId
) {}
