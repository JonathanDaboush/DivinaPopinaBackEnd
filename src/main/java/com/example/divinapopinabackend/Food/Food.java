package com.example.divinapopinabackend.Food;

import com.example.divinapopinabackend.Order.Order;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator= JSOGGenerator.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private float price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "food_entree",
            joinColumns = @JoinColumn(
                    name = "ofTarget_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "Side_id", referencedColumnName = "id"))

    private Collection<Food> food;


    @OneToMany(mappedBy="food", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Food(String name, String description, String url, float price) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
    }
}
