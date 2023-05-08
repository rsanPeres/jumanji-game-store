package com.jumanji.games.entity;

import com.jumanji.games.Interfaces.IObserver;
import com.jumanji.games.entity.enumerable.GameType;
import com.jumanji.games.entity.enumerable.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends Product{
    @Enumerated
    private GameType gameType;
    @ManyToMany(mappedBy = "favorites")
    private List<Player> fans;
    @Transient
    private List<IObserver> observers = new ArrayList<>();

    public Game(){}

    public Game(String name, BigDecimal price, int amount, GameType type, ProductType prodType) {
        super(name, price, amount, prodType);
        this.gameType = type;
    }

    public GameType getTypeGame() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    @Transient
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    @Transient
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void outOfStockNotifyObservers() {
        for(IObserver pl : observers){
            pl.getNotification(pl, new Notification("OutOfStock", this.name + " está fora de estoque!!"));
            pl.update(this);
        }
    }

    @Override
    public void inStockNotifyObservers() {
        for(IObserver pl : observers){
            pl.getNotification(pl, new Notification("inStock", "Novos exemplares de " + this.name + " em estoque!!"));
            pl.update(this);
        }
    }

    public boolean removeFromStock(int qtd){
        if(stockPresent()){
            this.amount = this.amount - qtd;
            return true;
        }else {
            outOfStockNotifyObservers();
            return false;
        }
    }

    public void addInStock(int qtd){
        this.amount = this.amount + qtd;
        inStockNotifyObservers();
    }

    public boolean stockPresent(){
        if(this.amount < 1){
            return false;
        }
        return true;
    }
}
