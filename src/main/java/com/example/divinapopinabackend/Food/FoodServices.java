package com.example.divinapopinabackend.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodServices {
    @Autowired
    FoodRepository foodRepository;

    public Food getFoodById(long id){
        return foodRepository.getReferenceById(id);
    }

    public Food getFoodByName(String name){
        return foodRepository.findByFoodName(name).get(0);
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
