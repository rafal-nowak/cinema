package com.rafalnowak.cinema;

import com.rafalnowak.cinema.user.domain.User;
import com.rafalnowak.cinema.user.domain.UserRole;

public class TestUserFactory {

    private static int userSequence = 10;

    public static User createAdmin() {
        userSequence++;
        return new User(
                userSequence,
                "newUser" + userSequence + "@example.com",
                "User Name " + userSequence,
                "password",
                UserRole.ADMIN
        );
    }

    public static User createVIP() {
        userSequence++;
        return new User(
                userSequence,
                "newUser" + userSequence + "@example.com",
                "User Name " + userSequence,
                "password",
                UserRole.VIP
        );
    }

    public static User createUser() {
        userSequence++;
        return new User(
                userSequence,
                "newUser" + userSequence + "@example.com",
                "User Name " + userSequence,
                "password",
                UserRole.USER
        );
    }
}
