package com.rafalnowak.cinema.reservation.application;

import com.rafalnowak.cinema.BaseIT;
import com.rafalnowak.cinema.TestUserFactory;
import com.rafalnowak.cinema.reservation.domain.Reservation;
import com.rafalnowak.cinema.reservation.domain.ReservationFactory;
import com.rafalnowak.cinema.reservation.domain.Seat;
import com.rafalnowak.cinema.user.application.UserService;
import com.rafalnowak.cinema.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest extends BaseIT {
    @Autowired
    UserService userService;

    @Autowired
    ReservationService reservationService;


    @Test
    void add_user_test() {
        //given
        User user = TestUserFactory.createUser();
        userService.save(user);

        //when
        User readUser = userService.findByEmail(user.getEmail());

        //then
        Assertions.assertEquals(user.getEmail(), readUser.getEmail());
        Assertions.assertEquals(user.getName(), readUser.getName());
        Assertions.assertTrue(passwordEncoder.matches(user.getPassword(), readUser.getPassword()));
    }

    @Test
    void add_reservation_test() {
        //given
        Reservation reservation = ReservationFactory.createReservation(10);
//        User user = TestUserFactory.createUser();
//        userService.save(user);

        //when
//        User readUser = userService.findByEmail(user.getEmail());
//
//        //then
//        Assertions.assertEquals(user.getEmail(), readUser.getEmail());
//        Assertions.assertEquals(user.getName(), readUser.getName());
//        Assertions.assertTrue(passwordEncoder.matches(user.getPassword(), readUser.getPassword()));
    }

    @Test
    void add_reservation_test1() {
        //given
        Reservation reservation = new Reservation(5);
        Reservation savedReservation = reservationService.save(reservation);

        //when
        Reservation readReservation = reservationService.findById(savedReservation.getId());

        //then
        System.out.println("####### Seats: " + readReservation.getSeats());;
        for (Seat seat : reservation.getSeats()) {
            System.out.println("####### Seat number: " + seat.getSeatNumber() + " is taken: " + seat.isTaken() );
        }

    }

    @Test
    void add_reservation_test2() {
        //given
        Reservation reservation = new Reservation(5);
        reservation.bookSeats(1, List.of(1, 2, 3));
        Reservation savedReservation = reservationService.save(reservation);

        //when
        Reservation readReservation = reservationService.findById(savedReservation.getId());

        //then
        System.out.println("####### Seats: " + readReservation.getSeats());;
        for (Seat seat : reservation.getSeats()) {
            System.out.println("####### Seat number: " + seat.getSeatNumber() + " is taken: " + seat.isTaken() );
        }

    }

    @Test
    void add_reservation_test3() {
        //given
        Reservation reservation = new Reservation(5);
//        reservation.bookSeats(1, List.of(1, 2, 3));
        Reservation savedReservation = reservationService.save(reservation);

        //when
        Reservation readReservation = reservationService.findById(savedReservation.getId());
        readReservation.bookSeats(1, List.of(1, 2, 3));
        Reservation modifiedReservation = reservationService.save(readReservation);

        Reservation foundReservation = reservationService.findById(savedReservation.getId());

        //then
        System.out.println("####### Seats: " + readReservation.getSeats());;
        for (Seat seat : foundReservation.getSeats()) {
            System.out.println("####### Seat number: " + seat.getSeatNumber() + " is taken: " + seat.isTaken() );
        }

    }
}