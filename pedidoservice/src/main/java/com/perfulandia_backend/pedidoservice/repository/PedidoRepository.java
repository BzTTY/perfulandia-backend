package com.perfulandia_backend.pedidoservice.repository;


import com.perfulandia_backend.pedidoservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
