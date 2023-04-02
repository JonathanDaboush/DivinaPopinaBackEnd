package com.example.divinapopinabackend.Transaction;

import com.example.divinapopinabackend.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Transaction {
    /**
     * Transactions is the table holding purchasing info.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "credit_card_number",nullable = false)
    private String creditCardNumber;
    @Column(nullable = false)
    private double amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;


    public Transaction(String creditCardNumber, double amount, Reservation reservation) {

        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
        this.reservation = reservation;
    }
}
