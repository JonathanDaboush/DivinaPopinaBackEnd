package com.example.divinapopinabackend.Category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodCategoryServices {
    @Autowired FoodCategoryRepository foodCategoryRepository;
    public FoodCategory getfoodCategoryById(long id){
        FoodCategory foodCategory=foodCategoryRepository.getReferenceById(id);
        return foodCategory;
    }

    public FoodCategory getFoodCategoryByName(String name){
        List<FoodCategory> foodCategory=foodCategoryRepository.findByFoodCategoryName(name);
        return foodCategory.get(0);
    }
    public void savefoodCategory(FoodCategory foodCategory){
        foodCategoryRepository.save(foodCategory);
    }
    public void removefoodCategory(long id){
        foodCategoryRepository.deleteById(id);
    }
    public List<FoodCategory> getfoodCategorys(){
        return foodCategoryRepository.findAll();
    }
}
