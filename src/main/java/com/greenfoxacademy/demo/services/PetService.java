package com.greenfoxacademy.demo.services;

import com.greenfoxacademy.demo.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
  private PetRepository petRepository;

  @Autowired
  public PetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }


}
