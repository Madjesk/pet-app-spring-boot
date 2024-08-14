package com.example.petappspringboot.errorhandling;

import java.time.LocalDateTime;

public record ServerMessageError(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}
