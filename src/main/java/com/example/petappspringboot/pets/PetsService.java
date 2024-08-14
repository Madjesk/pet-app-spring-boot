package com.example.petappspringboot.pets;

import com.example.petappspringboot.users.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PetsService {

    private final UserService userService;
    private final AtomicLong petIdSequence;

    public PetsService(UserService userService) {
        this.userService = userService;
        this.petIdSequence = new AtomicLong();
    }

    public Pet createPet(Pet pet) {
        if (pet.id() != null) {
            throw new IllegalArgumentException("Id for pet should not be provided");
        }
        var petToSave = new Pet(petIdSequence.incrementAndGet(), pet.name(), pet.userId());
        userService.getUser(pet.userId())
                .pets()
                .add(petToSave);
        return petToSave;
    }

    public Pet getPet(Long id) {
        return findPetById(id)
                .orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(id)));
    }

    public void deletePet(Long id) {
        var pet = findPetById(id)
                .orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(id)));
        var user = userService.getUser(pet.userId());
        user.pets().remove(pet);
    }

    public Pet updatePet(Pet pet) {
        if (pet.id() == null) {
            throw new IllegalArgumentException("No pet id passed");
        }

        var foundPet = findPetById(pet.id())
                .orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(pet.id())));
        var updatedPet = new Pet(foundPet.id(), pet.name(), foundPet.userId());

        var user = userService.getUser(pet.userId());
        user.pets().remove(foundPet);
        user.pets().add(updatedPet);
        return updatedPet;
    }

    private Optional<Pet> findPetById(Long id) {
        return userService.getAllUsers().stream()
                .flatMap(user -> user.pets().stream())
                .filter(pet -> pet.id().equals(id))
                .findAny();
    }
}
