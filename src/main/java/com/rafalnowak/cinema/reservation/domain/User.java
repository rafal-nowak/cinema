package com.rafalnowak.cinema.reservation.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@AllArgsConstructor
@ToString
public class User {
    Integer id;
    UserRole role;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(role, user.role)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
