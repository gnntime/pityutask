package com.greenfoxacademy.demo.controllers;

import com.greenfoxacademy.demo.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;

public class RestController {
  private HumanService humanService;

  @Autowired
  public RestController(HumanService humanService) {
    this.humanService = humanService;
  }
}
