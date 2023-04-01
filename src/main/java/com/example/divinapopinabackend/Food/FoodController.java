package com.example.divinapopinabackend.Food;


import com.example.divinapopinabackend.Category.FoodCategory;
import com.example.divinapopinabackend.Category.FoodCategoryServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    FoodServices foodServices;
    @Autowired
    JasyptConfig jasyptConfig;

@Autowired
    FoodCategoryServices foodCategoryServices;


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
    public ResponseEntity createfood(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        try{

            double price=0;

            if(payLoad.get("price") instanceof String){
                price=Double.parseDouble((String)payLoad.get("price"));
            }

            else if(payLoad.get("price") instanceof Integer){
                int number=(Integer)payLoad.get("price");
;
                price=number;
            }
            else{
                price=(Double)payLoad.get("price");
            }

            Food food=new Food(
                    (String)payLoad.get("name"),
                    (String)payLoad.get("description"),
                    price, (String)payLoad.get("categoryName"));


        foodServices.saveFood(food);}
        catch(Exception e){
        System.out.println(e);
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createFoodByExistence(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException {

        long id=(Integer)payLoad.get("id");
        Food food=foodServices.getFoodById(id);
        food.setName((String)payLoad.get("name"));
        food.setDescription((String)payLoad.get("description"));
        food.setFoodCategory((String)payLoad.get("categoryName"));
        if(payLoad.get("price") instanceof String){
            food.setPrice(Double.parseDouble((String)payLoad.get("price")));
        }
        else if(payLoad.get("price") instanceof Integer){
            food.setPrice(Double.valueOf((Integer)payLoad.get("price")));
        }
        else{

            food.setPrice((Double)payLoad.get("price"));
        }




        foodServices.saveFood(food);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        foodServices.removeFood(id);
        return ResponseEntity.ok().build();
    }
}
