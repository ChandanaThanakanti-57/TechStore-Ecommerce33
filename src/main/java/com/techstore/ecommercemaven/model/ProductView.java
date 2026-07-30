package com.techstore.ecommercemaven.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ProductView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private User user;


    @ManyToOne
    private Product product;


    private LocalDateTime viewedAt;
}