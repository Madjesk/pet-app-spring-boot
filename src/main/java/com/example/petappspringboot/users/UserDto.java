package com.example.petappspringboot.users;

import com.example.petappspringboot.pets.PetDto;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import sorokin.java.course.pets.PetDto;

import java.util.List;

public record UserDto(
        Long id,
        @NotBlank(message = "Name must be not empty")
        String name,
        @ Email(message = "Email must be valid")
        String email,
        @Min(value = 0, message = "Age must be more than 0")
        Integer age,
        @Nonnull
        List<PetDto> pets
) {
}
