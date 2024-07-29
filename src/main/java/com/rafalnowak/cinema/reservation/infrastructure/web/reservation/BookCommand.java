package com.rafalnowak.cinema.reservation.infrastructure.web.reservation;

import java.util.List;

public record BookCommand(
        List<Integer>seatNumbers
) {}
