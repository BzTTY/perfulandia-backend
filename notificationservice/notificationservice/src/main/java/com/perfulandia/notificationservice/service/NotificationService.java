package com.perfulandia.notificationservice.service;

import com.perfulandia.notificationservice.model.Notification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final List<Notification> notifications = new ArrayList<>();

    public List<Notification> listar() {
        return notifications;
    }

    public Notification guardar(Notification notification) {
        notifications.add(notification);
        return notification;
    }

    public Notification buscarPorId(long id) {
        return notifications.stream()
                .filter(notification -> notification.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void eliminar(long id) {
        notifications.removeIf(notification -> notification.getId() == id);
    }
}