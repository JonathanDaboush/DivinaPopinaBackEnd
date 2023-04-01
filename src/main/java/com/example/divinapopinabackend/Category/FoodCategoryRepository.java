package com.example.divinapopinabackend.Category;

import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository  extends JpaRepository<FoodCategory, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM food_category  "
            +  " WHERE name = :key "
            , nativeQuery = true)
    List<FoodCategory> findByFoodCategoryName(String key);
}
