package com.maestria.springmvcstock.model;
import jakarta.persistence.*;
// import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
}