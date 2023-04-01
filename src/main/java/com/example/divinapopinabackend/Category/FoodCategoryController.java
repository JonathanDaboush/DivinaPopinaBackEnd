package com.example.divinapopinabackend.Category;

import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Food.FoodServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/foodCategory")
@CrossOrigin
public class FoodCategoryController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    FoodServices foodServices;
    @Autowired
    JasyptConfig jasyptConfig;

    @Autowired
    FoodCategoryServices foodCategoryServices;


    @GetMapping
    public List<FoodCategory> getFoodCategorys() {
        List<FoodCategory> foodCategory=foodCategoryServices.getfoodCategorys();
        return foodCategory;
    }

    @GetMapping("/{id}")
    public FoodCategory getFoodCategory(@PathVariable Long id) throws MessagingException, IOException {
        FoodCategory foodCategory=foodCategoryServices.getfoodCategoryById(id);
        return foodCategory;
    }
    @GetMapping("/byName/{name}")
    public FoodCategory getFoodCategoryByName(@PathVariable String name) throws MessagingException, IOException {
        FoodCategory foodCategory=foodCategoryServices.getFoodCategoryByName(name);
        return foodCategory;
    }
    @PostMapping
    public ResponseEntity createfoodCategory(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        try{
            FoodCategory foodCategory=new FoodCategory();
            foodCategory.setName((String)payLoad.get("name"));


            foodCategoryServices.savefoodCategory(foodCategory);}
        catch(Exception e){

        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createFoodCategoryByExistence(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException {

        long id=(Integer)payLoad.get("id");
        FoodCategory foodCategory=foodCategoryServices.getfoodCategoryById(id);
        foodCategory.setName((String)payLoad.get("name"));
        foodCategoryServices.savefoodCategory(foodCategory);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        foodCategoryServices.removefoodCategory(id);
        return ResponseEntity.ok().build();
    }
}

