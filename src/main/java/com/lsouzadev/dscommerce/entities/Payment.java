package com.lsouzadev.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    @OneToOne
    @MapsId
    private Order order;

    public Payment(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public Payment() {
    }
}
