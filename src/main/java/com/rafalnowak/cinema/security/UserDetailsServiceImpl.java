package com.rafalnowak.cinema.security;

import com.rafalnowak.cinema.user.domain.User;
import com.rafalnowak.cinema.user.domain.UserNotFoundException;
import com.rafalnowak.cinema.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return new UserDetailsImpl(user);
    }
}
