package com.maestria.springmvcstock.model;
import java.util.Date;
import lombok.Data;
import jakarta.persistence.*;
@Entity
@Data
@Table(name = "kardex")
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String operationType; // Could be "IN" or "OUT"
    private Long productId;
    private int quantity;
    private String description;
}