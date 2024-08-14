package com.example.petappspringboot.users;

import com.example.petappspringboot.pets.PetsDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserDtoConverter userDtoConverter;
    private final PetsDtoConverter petsDtoConverter;

    public UserController(
            UserService userService,
            UserDtoConverter userDtoConverter,
            PetsDtoConverter petsDtoConverter
    ) {
        this.userService = userService;
        this.userDtoConverter = userDtoConverter;
        this.petsDtoConverter = petsDtoConverter;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {
        var savedUser = userService.createUser(userDtoConverter.toUser(userDto));
        return ResponseEntity.ok(userDtoConverter.toDto(savedUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long id) {
        var user = userService.getUser(id);
        return ResponseEntity.ok(userDtoConverter.toDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody @Validated UserDto userDto
    ) {
        var userToUpdate = new User(
                id,
                userDto.name(),
                userDto.email(),
                userDto.age(),
                userDto.pets().stream().map(petsDtoConverter::toPet).toList()
        );
        var updatedUser = userService.updateUser(userToUpdate);
        return ResponseEntity.ok(userDtoConverter.toDto(updatedUser));
    }
}
