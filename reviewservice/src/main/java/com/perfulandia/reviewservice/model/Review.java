package com.perfulandia.reviewservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId; // ID del producto reseñado
    private long userId; // ID del usuario que realiza la reseña
    private String comment; // Comentario de la reseña
    private int rating; // Calificación del producto (1-5)
}
