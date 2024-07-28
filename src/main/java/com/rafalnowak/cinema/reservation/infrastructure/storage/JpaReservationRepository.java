package com.rafalnowak.cinema.reservation.infrastructure.storage;

import com.rafalnowak.cinema.user.infrastructure.storage.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}
