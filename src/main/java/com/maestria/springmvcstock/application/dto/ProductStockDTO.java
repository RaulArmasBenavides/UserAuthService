package com.maestria.springmvcstock.application.dto;

import com.maestria.springmvcstock.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStockDTO {
    private Product product;
    private int stockQuantity;
}