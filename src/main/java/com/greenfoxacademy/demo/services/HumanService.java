package com.greenfoxacademy.demo.services;

import com.greenfoxacademy.demo.modells.Human;
import com.greenfoxacademy.demo.repositories.HumanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanService {
  private HumanRepository humanRepository;

  @Autowired
  public HumanService(HumanRepository humanRepository) {
    this.humanRepository = humanRepository;
  }

  public List<Human> getAllHumans() {
    return humanRepository.findAll();
  }

  public void createHuman(Human human) {
    humanRepository.save(human);
  }

  public void modifyHuman(Human human) {
    humanRepository.save(human);
  }

  public void deleteHumanById(Long id) {
    humanRepository.deleteById(id);
  }

  public Human getHumanById(Long id) {
    return humanRepository.findById(id).orElse(null);
  }
}
