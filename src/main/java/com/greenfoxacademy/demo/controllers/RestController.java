package com.greenfoxacademy.demo.controllers;

import com.greenfoxacademy.demo.dtos.ErrorMessage;
import com.greenfoxacademy.demo.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {
  private HumanService humanService;

  @Autowired
  public RestController(HumanService humanService) {
    this.humanService = humanService;
  }

  @GetMapping("/api/human/{id}")
  public ResponseEntity<?> getHumans(@PathVariable(value = "id") Long id) {
    if (humanService.getHumanById(id) == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body(new ErrorMessage("No human on the given index " + id));
    }
    return ResponseEntity.ok().body(humanService.getAllHumans());
  }

  @DeleteMapping("/api/human/{id}")
  public ResponseEntity<?> deleteHuman(@PathVariable(value = "id") Long id) {
    if (humanService.getHumanById(id) == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ErrorMessage("No human on the given index " + id));
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
