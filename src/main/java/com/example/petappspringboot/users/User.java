package com.example.petappspringboot.users;

import com.example.petappspringboot.pets.Pet;

import java.util.List;
public record User(
        Long id,
        String name,
        String email,
        Integer age,
        List<Pet> pets
) {
}
