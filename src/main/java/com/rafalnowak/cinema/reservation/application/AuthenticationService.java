package com.rafalnowak.cinema.reservation.application;

import com.rafalnowak.cinema.reservation.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication getAuthentication();
    Integer getLoggedInUserId();
    User getLoggedInUser();
}
