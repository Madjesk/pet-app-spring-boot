package com.example.petappspringboot.pets;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pets")
@RestController
public class PetsController {

    private final PetsService petsService;
    private final PetsDtoConverter petsDtoConverter;

    public PetsController(PetsService petsService, PetsDtoConverter petsDtoConverter) {
        this.petsService = petsService;
        this.petsDtoConverter = petsDtoConverter;
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody @Validated PetDto petDto) {
        var savedPet = petsService.createPet(petsDtoConverter.toPet(petDto));
        return ResponseEntity.ok(petsDtoConverter.toDto(savedPet));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto> getPet(@PathVariable("petId") Long id) {
        var pet = petsService.getPet(id);
        return ResponseEntity.ok(petsDtoConverter.toDto(pet));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable("petId") Long id) {
        petsService.deletePet(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> updatePet(
            @PathVariable("petId") Long id,
            @RequestBody @Validated PetDto pet
    ) {
        var petToUpdate = new Pet(
                id,
                pet.name(),
                pet.userId()
        );
        var updatedPet = petsService.updatePet(petToUpdate);
        return ResponseEntity.ok(petsDtoConverter.toDto(updatedPet));
    }

}
