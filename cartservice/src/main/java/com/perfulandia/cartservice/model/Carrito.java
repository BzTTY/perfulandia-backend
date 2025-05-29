package com.perfulandia.cartservice.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private Long usuarioId;
    private List<ItemCarrito> items = new ArrayList<>();

    public Carrito() {}

    public Carrito(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Getters y setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public List<ItemCarrito> getItems() { return items; }
    public void setItems(List<ItemCarrito> items) { this.items = items; }

    // Métodos adicionales útiles

    public void agregarItem(ItemCarrito item) {
        this.items.add(item);
    }

    public void eliminarItem(Long productoId) {
        this.items.removeIf(i -> i.getProducto().getId().equals(productoId));
    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(i -> i.getProducto().getPrecio() * i.getCantidad())
                .sum();
    }
}
