package com.rafalnowak.cinema.reservation.domain;

public enum UserRole {

    ADMIN ("ADMIN"),
    VIP ("VIP"),
    USER ("USER");


    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}