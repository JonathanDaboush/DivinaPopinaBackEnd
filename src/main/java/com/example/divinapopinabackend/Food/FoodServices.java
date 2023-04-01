package com.example.divinapopinabackend.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodServices {
    @Autowired
    FoodRepository foodRepository;

    public Food getFoodById(long id){
        Food food=foodRepository.getReferenceById(id);
        return food;
    }

    public Food getFoodByName(String name){
        List<Food> food=foodRepository.findByFoodName(name);
        return food.get(0);
    }
    public void saveFood(Food food){
        foodRepository.save(food);
    }
    public void removeFood(long id){
        foodRepository.deleteById(id);
    }
    public List<Food> getfoods(){
        return foodRepository.findAll();
    }
}
