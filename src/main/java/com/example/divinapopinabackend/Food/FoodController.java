package com.example.divinapopinabackend.Food;


import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    FoodServices foodServices;
    @Autowired
    JasyptConfig jasyptConfig;




    @GetMapping
    public List<Food> getfoods() {
        return foodServices.getfoods();
    }

    @GetMapping("/{id}")
    public Food getfood(@PathVariable Long id) throws MessagingException, IOException {
        Food food=foodServices.getFoodById(id);
        return food;
    }
    @GetMapping("/byName/{name}")
    public Food getfoodByName(@PathVariable String name) throws MessagingException, IOException {
        Food food=foodServices.getFoodByName(name);
        return food;
    }
    @PostMapping
    public ResponseEntity createfood(@RequestParam  String name,@RequestParam  String description,@RequestParam  String url,@RequestParam  float price) throws URISyntaxException {
        Food food=new Food(name,description,url,price);
        foodServices.saveFood(food);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createfood(@RequestParam  long id,@RequestParam  String name,@RequestParam  String description,@RequestParam  String url,@RequestParam  float price) throws URISyntaxException {
        Food food=foodServices.getFoodById(id);
        food.setName(name);
        food.setDescription(description);
        food.setUrl(url);
        food.setPrice(price);
        foodServices.saveFood(food);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        foodServices.removeFood(id);
        return ResponseEntity.ok().build();
    }
}
