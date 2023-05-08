package com.jumanji.games.repository;

import com.jumanji.games.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
}
