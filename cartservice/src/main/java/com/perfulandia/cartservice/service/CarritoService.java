package com.perfulandia.cartservice.service;

import com.perfulandia.cartservice.client.ProductoClient;
import com.perfulandia.cartservice.client.UsuarioClient;
import com.perfulandia.cartservice.model.Carrito;
import com.perfulandia.cartservice.model.ItemCarrito;
import com.perfulandia.cartservice.model.Producto;
import com.perfulandia.cartservice.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarritoService {

    private final ProductoClient productoClient;
    private final UsuarioClient usuarioClient;

    // Aquí guardamos los carritos en memoria (usuarioId -> Carrito)
    private final Map<Long, Carrito> carritos = new HashMap<>();

    public CarritoService(ProductoClient productoClient, UsuarioClient usuarioClient) {
        this.productoClient = productoClient;
        this.usuarioClient = usuarioClient;
    }

    // Obtener carrito por usuario (si no existe, crear uno nuevo)
    public Carrito obtenerCarrito(Long usuarioId) {
        return carritos.computeIfAbsent(usuarioId, Carrito::new);
    }

    // Agregar producto al carrito (ahora con verificación de usuario)
    public boolean agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Usuario usuario = usuarioClient.buscarUsuario(usuarioId);
        if (usuario == null) return false; // Usuario no existe

        Producto producto = productoClient.buscarProducto(productoId);
        if (producto == null) return false; // Producto no existe

        if (producto.getStock() < cantidad) return false; // No hay stock suficiente

        Carrito carrito = obtenerCarrito(usuarioId);

        // Ver si ya existe el producto en el carrito, para sumar cantidades
        for (ItemCarrito item : carrito.getItems()) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(item.getCantidad() + cantidad);
                return true;
            }
        }

        // Si no estaba, agregar nuevo item
        carrito.agregarItem(new ItemCarrito(producto, cantidad));
        return true;
    }

    // Eliminar producto del carrito
    public boolean eliminarProducto(Long usuarioId, Long productoId) {
        Carrito carrito = obtenerCarrito(usuarioId);
        int sizeAntes = carrito.getItems().size();
        carrito.eliminarItem(productoId);
        return carrito.getItems().size() < sizeAntes;
    }

    // Calcular total del carrito
    public double calcularTotal(Long usuarioId) {
        Carrito carrito = obtenerCarrito(usuarioId);
        return carrito.calcularTotal();
    }
}
