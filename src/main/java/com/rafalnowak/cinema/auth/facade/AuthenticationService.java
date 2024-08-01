package com.rafalnowak.cinema.auth.facade;

import com.rafalnowak.cinema.auth.api.AuthenticationRequest;
import com.rafalnowak.cinema.auth.api.AuthenticationResponse;
import com.rafalnowak.cinema.security.JWTUtil;
import com.rafalnowak.cinema.security.UserDetailsImpl;
import com.rafalnowak.cinema.user.api.UserDto;
import com.rafalnowak.cinema.user.api.UserDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDtoMapper userDtoMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = userDtoMapper.toDto(principal.getUser());
        String token = jwtUtil.issueToken(userDto.email(), userDto.role());
        return new AuthenticationResponse(token, userDto);
    }

}
