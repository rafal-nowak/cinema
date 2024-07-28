package com.rafalnowak.cinema.reservation.infrastructure.storage;


import com.rafalnowak.cinema.reservation.domain.PageReservation;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.reservation.domain.ReservationRepository;
import com.rafalnowak.cinema.user.domain.PageUser;
import com.rafalnowak.cinema.user.domain.User;
import com.rafalnowak.cinema.user.domain.UserAlreadyExistsException;
import com.rafalnowak.cinema.user.domain.UserRepository;
import com.rafalnowak.cinema.user.infrastructure.storage.JpaUserRepository;
import com.rafalnowak.cinema.user.infrastructure.storage.UserEntity;
import com.rafalnowak.cinema.user.infrastructure.storage.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log
public
class ReservationStorageAdapter implements ReservationRepository {

    private final JpaReservationRepository reservationRepository;
    private final ReservationEntityMapper mapper;

    @Override
    public Reservation save(final Reservation reservation) {
        try {
            ReservationEntity saved = reservationRepository.save(mapper.toEntity(reservation));
            log.info("Saved entity " + saved);
            return mapper.toDomain(saved);
        } catch (DataIntegrityViolationException ex) {
            log.warning("I can't save this Reservation in db");
            throw ex;
        }
    }

    @Override
    public void update(final Reservation reservation) {
        reservationRepository.findById(reservation.getId()).ifPresent(userEntity -> reservationRepository.save(mapper.toEntity(reservation)));
    }

    @Override
    public void remove(final Integer id) {
        reservationRepository.findById(id).ifPresent(userEntity -> reservationRepository.deleteById(id));
    }

    @Override
    public Optional<Reservation> findById(final Integer id) {
        return reservationRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public PageReservation findAll(final Pageable pageable) {
        Page<ReservationEntity> pageOfReservationsEntity = reservationRepository.findAll(pageable);
        List<Reservation> reservationsOnCurrentPage = pageOfReservationsEntity.getContent().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
        return new PageReservation(
                reservationsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfReservationsEntity.getTotalPages(),
                pageOfReservationsEntity.getTotalElements()
        );
    }
}
