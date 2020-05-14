package com.greenfoxacademy.demo.repositories;

import com.greenfoxacademy.demo.modells.Pet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
  List<Pet> findAll();
  Optional<Pet> findByName(String name);
}