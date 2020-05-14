package com.greenfoxacademy.demo.repositories;

import com.greenfoxacademy.demo.modells.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
