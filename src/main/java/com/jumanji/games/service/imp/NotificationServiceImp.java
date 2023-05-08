package com.jumanji.games.service.imp;

import com.jumanji.games.entity.Notification;
import com.jumanji.games.repository.INotificationRepository;
import com.jumanji.games.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImp implements INotificationService {
    @Autowired
    private INotificationRepository repository;

    @Override
    public Iterable<Notification> getAll() {
        return repository.findAll();
    }

    @Override
    public Notification getById(Long id) {
        Optional<Notification> notification = repository.findById(id);
        return notification.orElse(null);
    }

    @Override
    public void insert(Notification notification) {
        repository.save(notification);
    }

    @Override
    public void update(Notification notification, Long id) {
        Optional<Notification> not = repository.findById(id);
        if(not.isPresent()){
            not.get().setId(id);
            repository.save(notification);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Notification> notification = repository.findById(id);
        notification.ifPresent(value -> repository.delete(value));
    }
}
