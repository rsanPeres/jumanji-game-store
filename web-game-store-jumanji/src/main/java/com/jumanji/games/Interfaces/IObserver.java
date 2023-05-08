package com.jumanji.games.Interfaces;


import com.jumanji.games.entity.Notification;
import com.jumanji.games.entity.Product;

public interface IObserver {
    void update(Product product);
    void getNotification(IObserver observer, Notification notification);
}
