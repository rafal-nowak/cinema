package com.rafalnowak.cinema.auth.facade;

import com.rafalnowak.cinema.auth.api.AuthUserDto;
import com.rafalnowak.cinema.auth.api.AuthUserDtoMapper;
import com.rafalnowak.cinema.auth.api.AuthenticationRequest;
import com.rafalnowak.cinema.auth.api.AuthenticationResponse;
import com.rafalnowak.cinema.security.JWTUtil;
import com.rafalnowak.cinema.security.Security;
import com.rafalnowak.cinema.security.UserDetailsImpl;
import com.rafalnowak.cinema.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AuthUserDtoMapper userDtoMapper;
    private final JWTUtil jwtUtil;
    private final Security security;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        AuthUserDto userDto = userDtoMapper.toDto(principal.getUser());
        String token = jwtUtil.issueToken(userDto.email(), userDto.role());
        return new AuthenticationResponse(token, userDto);
    }

    public AuthUserDto getLoggedInUser() {
        User user = security.getPrincipal();

        return userDtoMapper.toDto(user);
    }

}
