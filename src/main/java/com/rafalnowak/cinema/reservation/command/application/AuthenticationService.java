package com.rafalnowak.cinema.reservation.command.application;

import com.rafalnowak.cinema.reservation.command.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
