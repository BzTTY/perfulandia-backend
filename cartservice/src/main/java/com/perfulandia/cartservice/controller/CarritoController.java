package com.perfulandia.cartservice.controller;

import com.perfulandia.cartservice.model.Carrito;
import com.perfulandia.cartservice.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Obtener el carrito completo de un usuario
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long usuarioId) {
        Carrito carrito = carritoService.obtenerCarrito(usuarioId);
        return ResponseEntity.ok(carrito);
    }

    // Agregar producto al carrito
    @PostMapping("/{usuarioId}/productos/{productoId}")
    public ResponseEntity<String> agregarProducto(@PathVariable Long usuarioId,
                                                  @PathVariable Long productoId,
                                                  @RequestParam int cantidad) {
        boolean agregado = carritoService.agregarProducto(usuarioId, productoId, cantidad);
        if (agregado) {
            return ResponseEntity.ok("Producto agregado correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo agregar el producto (producto inexistente o sin stock)");
        }
    }

    // Eliminar producto del carrito
    @DeleteMapping("/{usuarioId}/productos/{productoId}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long usuarioId,
                                                   @PathVariable Long productoId) {
        boolean eliminado = carritoService.eliminarProducto(usuarioId, productoId);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado del carrito");
        } else {
            return ResponseEntity.badRequest().body("Producto no encontrado en el carrito");
        }
    }

    // Obtener total del carrito
    @GetMapping("/{usuarioId}/total")
    public ResponseEntity<Double> obtenerTotal(@PathVariable Long usuarioId) {
        double total = carritoService.calcularTotal(usuarioId);
        return ResponseEntity.ok(total);
    }
}
