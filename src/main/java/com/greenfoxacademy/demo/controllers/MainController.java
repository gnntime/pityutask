package com.greenfoxacademy.demo.controllers;

import com.greenfoxacademy.demo.modells.Human;
import com.greenfoxacademy.demo.modells.Pet;
import com.greenfoxacademy.demo.services.HumanService;
import com.greenfoxacademy.demo.services.PetService;
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
  private PetService petService;

  @Autowired
  public MainController(HumanService humanService, PetService petService) {
    this.humanService = humanService;
    this.petService = petService;
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

  @GetMapping("/list-pets")
  public String getPets(Model model,
                        @RequestParam(required = false, value = "fail") boolean fail,
                        @RequestParam(required = false, value = "name") String name) {
    if (!fail) {
      model.addAttribute("pet", new Pet());
      model.addAttribute("pets", petService.getAllPets());
      model.addAttribute("humans", humanService.getAllHumans());
      return "pets";
    }
    model.addAttribute("pets", petService.getAllPets());
    model.addAttribute("fail", true);
    model.addAttribute("pet", petService.getPetByName(name));
    model.addAttribute("humans", humanService.getAllHumans());
    return "pets";
  }

  @PostMapping("/add-pet")
  public String addPet(@ModelAttribute Pet pet, @RequestParam(value = "ownerid") Long humanId) {
    if (pet.getName().equals("") || petService.doesPetByNameExists(pet.getName())) {
      return "redirect:/list-pets?fail=true&name=" +pet.getName();
    }
    petService.createPet(pet, humanId);
    return "redirect:/list-pets";
  }

}
