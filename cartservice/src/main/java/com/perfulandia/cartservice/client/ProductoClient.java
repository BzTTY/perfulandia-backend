package com.perfulandia.cartservice.client;

import com.perfulandia.cartservice.model.Producto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8082/api/v1/productos";

    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Producto buscarProducto(Long id) {
        try {
            return restTemplate.getForObject(baseUrl + "/" + id, Producto.class);
        } catch (Exception e) {
            return null;
        }
    }
}
