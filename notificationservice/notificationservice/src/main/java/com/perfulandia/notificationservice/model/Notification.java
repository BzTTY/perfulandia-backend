package com.perfulandia.notificationservice.model;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private long id;
    private long userId; // ID del usuario que recibe la notificación
    private String message; // Mensaje de la notificación
    private boolean isRead; // Estado de lectura de la notificación
}