package com.jumanji.games.Interfaces;

public interface IProductSubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void inStockNotifyObservers();
    void outOfStockNotifyObservers();
}
