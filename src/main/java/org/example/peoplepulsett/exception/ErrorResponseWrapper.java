package org.example.peoplepulsett.exception;

import java.time.LocalDateTime;

public record ErrorResponseWrapper(
        Object message,
        LocalDateTime localDateTime
) {
}