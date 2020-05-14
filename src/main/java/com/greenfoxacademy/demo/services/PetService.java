package com.greenfoxacademy.demo.services;

import com.greenfoxacademy.demo.modells.Human;
import com.greenfoxacademy.demo.modells.Pet;
import com.greenfoxacademy.demo.repositories.HumanRepository;
import com.greenfoxacademy.demo.repositories.PetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
  private PetRepository petRepository;
  private HumanRepository humanRepository;

  @Autowired
  public PetService(PetRepository petRepository, HumanRepository humanRepository) {
    this.petRepository = petRepository;
    this.humanRepository = humanRepository;
  }

  public List<Pet> getAllPets() {
    return petRepository.findAll();
  }

  public void createPet(Pet pet, Long humanId) {
    Human ownerChoice = humanRepository.findById(humanId).get();
    pet.setOwner(ownerChoice);
    ownerChoice.addPet(pet);
    petRepository.save(pet);
    humanRepository.save(ownerChoice);
  }

  public void modifyPet(Pet pet) {
    petRepository.save(pet);
  }

  public Pet getPetByName(String name) {
    return petRepository.findByName(name).orElse(null);
  }

  public boolean doesPetByNameExists(String name) {
    return petRepository.findByName(name).isPresent();
  }
}
