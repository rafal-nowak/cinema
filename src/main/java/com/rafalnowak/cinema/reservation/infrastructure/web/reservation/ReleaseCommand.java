package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import java.util.List;

public record ReleaseCommand(
        List<Integer>seatNumbers,
        Integer userId
) {}
