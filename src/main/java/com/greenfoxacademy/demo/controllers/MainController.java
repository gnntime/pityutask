package com.greenfoxacademy.demo.controllers;

import com.greenfoxacademy.demo.modells.Human;
import com.greenfoxacademy.demo.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
  private HumanService humanService;

  @Autowired
  public MainController(HumanService humanService) {
    this.humanService = humanService;
  }

  @GetMapping({"/list-humans","/"})
  public String getHumans(Model model,
                          @RequestParam(required = false, value = "fail") boolean fail,
                          @RequestParam(required = false, value = "name") String name) {
    if (!fail) {
      model.addAttribute("human", new Human());
      model.addAttribute("humans", humanService.getAllHumans());
      return "index";
    }
    model.addAttribute("humans", humanService.getAllHumans());
    model.addAttribute("fail", true);
    model.addAttribute("human", humanService.getHumanByName(name));
    return "index";
  }

  @PostMapping("/add-human")
  public String addHuman(@ModelAttribute Human human) {
    if (human.getName().equals("") || human.getAge() == 0 || humanService.doesHumanExistsByName(human.getName())) {
      return "redirect:/list-humans?fail=true&name=" +human.getName();
    }
    humanService.createHuman(human);
    return "redirect:/list-humans";
  }

  @GetMapping("/delete/{id}")
  public Object deleteHuman(@PathVariable(value = "id") Long id) {
   if (humanService.getHumanById(id) == null) {
     return new ResponseEntity(HttpStatus.BAD_REQUEST);
   }
   humanService.deleteHumanById(id);
    return "redirect:/list-humans";
  }

  @GetMapping("/edit/{id}")
  public Object getEditHuman(@PathVariable(value = "id") Long id,
                             Model model) {
    if (humanService.getHumanById(id) == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("human", humanService.getHumanById(id));
    return "edithuman";
  }

  @PostMapping("/edit/{id}")
  public String postEditHuman(@ModelAttribute Human human) {
    humanService.modifyHuman(human);
    return "redirect:/list-humans";
  }
}
