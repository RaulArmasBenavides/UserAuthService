package com.maestria.springmvcstock.infrastructure.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 

@Service
public class QueryServiceClient {

    @Autowired
    private RestTemplate restTemplate;
    private final String SERVICE_URL = "http://StockQueryService/api/v1";
    // private final String API_GATEWAY_BASE_URL = "http://localhost:8080/api/v1"; //http://localhost:8080/api/v1

    // public Customer getCustomerById(Long customerId) {
    //     return restTemplate.getForObject(SERVICE_URL + "/customers/" + customerId, Customer.class);
    // }

    // public Product getProductById(Long productId) {
    //     return restTemplate.getForObject(SERVICE_URL + "/products/" + productId, Product.class);
    // }

    // public Supplier getSupplierById(Long supplierId) {
    //     return restTemplate.getForObject(SERVICE_URL + "/suppliers/" + supplierId, Supplier.class);
    // }

    // public List<Customer> getCustomers() {
    //     System.out.println("pbt");
    //     ResponseEntity<List<Customer>> response =
    //         restTemplate.exchange(SERVICE_URL + "/customers", HttpMethod.GET, null,
    //         new ParameterizedTypeReference<List<Customer>>() {});
    //     return response.getBody();
    // }
}