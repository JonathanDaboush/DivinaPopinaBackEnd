package com.example.divinapopinabackend.Order;
import com.fasterxml.jackson.annotation.*;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator= JSOGGenerator.class)

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Order {
    /**
     * Transactions is the table holding ordering info.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column
    private String note;

    @Column(nullable = false,name="food")
    String food;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    public Order(int quantity, String note, String food, Reservation reservation) {
        this.quantity = quantity;
        this.note = note;
        this.food = food;
        this.reservation = reservation;
    }
}
