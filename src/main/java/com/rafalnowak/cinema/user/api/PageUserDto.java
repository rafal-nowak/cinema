package com.rafalnowak.cinema.user.api;

import java.util.List;

public record PageUserDto(
        List<UserDto> users,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
