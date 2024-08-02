package com.rafalnowak.cinema.user.infrastructure.storage;

import com.rafalnowak.cinema.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
