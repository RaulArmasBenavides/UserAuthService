package com.maestria.springmvcstock.model;

// import javax.persistence.*;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
@Entity
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;  // ID del cliente (referencia a query-service)
    // Otros campos de la venta

     @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SalesItem> items;
}
