package com.perfulandia.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUsuarioId(Long usuarioId);
}
