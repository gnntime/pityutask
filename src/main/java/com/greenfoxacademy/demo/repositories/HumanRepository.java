package com.greenfoxacademy.demo.repositories;

import com.greenfoxacademy.demo.modells.Human;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface HumanRepository extends CrudRepository<Human, Long> {
  List<Human> findAll();
}