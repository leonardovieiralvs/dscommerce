package com.lsouzadev.dscommerce.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String imgUrl;

    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
