package com.rafalnowak.cinema.user.domain;

import com.rafalnowak.cinema.user.infrastructure.storage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncodingService encoder;

    public User save(User user) {
        return userRepository.save(
                user.withPassword(
                        encoder.encode(user.getPassword())
                )
        );
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void removeById(Integer id) {
        userRepository.remove(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public PageUser findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}