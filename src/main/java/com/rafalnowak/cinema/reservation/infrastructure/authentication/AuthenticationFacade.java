package com.rafalnowak.cinema.reservation.infrastructure.authentication;


import com.rafalnowak.cinema.reservation.application.AuthenticationService;
import com.rafalnowak.cinema.security.UserDetailsImpl;
import com.rafalnowak.cinema.user.application.UserService;
import com.rafalnowak.cinema.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade implements AuthenticationService {
    private final UserService userService;
    private final UserAuthenticationMapper mapper;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Integer getLoggedInUserId() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return user.getId();
    }

    @Override
    public com.rafalnowak.cinema.reservation.domain.User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return mapper.toReservationContext(user);
//        return new com.rafalnowak.cinema.reservation.domain.User(user.getId(), user.getRole());
    }

}