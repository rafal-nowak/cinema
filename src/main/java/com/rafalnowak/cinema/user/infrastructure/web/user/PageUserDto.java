package com.rafalnowak.cinema.user.infrastructure.web.user;

import java.util.List;

public record PageUserDto(
        List<UserDto> users,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
