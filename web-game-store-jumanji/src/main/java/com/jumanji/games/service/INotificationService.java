package com.jumanji.games.service;

import com.jumanji.games.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public interface INotificationService {
    Iterable<Notification> getAll();
    Notification getById(Long id);
    void insert(Notification notif);
    void update(Notification notif, Long id);
    void delete(Long id);
}
