package com.example.petappspringboot.pets;

public record Pet(
        Long id,
        String name,
        Long userId
) {
}
