package com.example.divinapopinabackend.Food;

import com.example.divinapopinabackend.Category.FoodCategory;
import com.example.divinapopinabackend.Order.Order;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator= JSOGGenerator.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Food implements Serializable {
    /**
     * food is the table holding food Item info.
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(name="food_category",nullable = false)
    private String  foodCategory;

    public Food(String name, String description, double price,String foodCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodCategory=foodCategory;
    }
}
