package com.maestria.springmvcstock.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SalesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;  // ID del producto (referencia a query-service)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    @JsonBackReference
    private Sales sales;

}
