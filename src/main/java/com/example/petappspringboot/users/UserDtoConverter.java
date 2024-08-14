package com.example.petappspringboot.users;

import com.example.petappspringboot.pets.PetsDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    private final PetsDtoConverter petsDtoConverter;

    public UserDtoConverter(PetsDtoConverter petsDtoConverter) {
        this.petsDtoConverter = petsDtoConverter;
    }

    public User toUser(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.email(),
                userDto.age(),
                userDto.pets().stream().map(petsDtoConverter::toPet).toList()
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.id(),
                user.name(),
                user.email(),
                user.age(),
                user.pets().stream().map(petsDtoConverter::toDto).toList()
        );
    }
}
