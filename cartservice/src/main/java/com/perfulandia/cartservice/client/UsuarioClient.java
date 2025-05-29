package com.perfulandia.cartservice.client;

import com.perfulandia.cartservice.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081/api/usuarios";

    public UsuarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Usuario buscarUsuario(Long id) {
        try {
            return restTemplate.getForObject(baseUrl + "/" + id, Usuario.class);
        } catch (Exception e) {
            return null;
        }
    }
}
