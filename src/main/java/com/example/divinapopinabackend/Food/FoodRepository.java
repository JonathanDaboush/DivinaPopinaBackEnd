package com.example.divinapopinabackend.Food;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository  extends JpaRepository<Food, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM food  "
            +  " WHERE name = :key "
            , nativeQuery = true)
    List<Food> findByFoodName(String key);
}
