package com.rafalnowak.cinema.reservation.command.application;

import java.util.List;

public record BookCommand(
        List<Integer>seatNumbers,
        Integer userId
) {}
