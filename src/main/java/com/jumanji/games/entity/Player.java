package com.jumanji.games.entity;

import com.jumanji.games.Interfaces.IObserver;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Player extends Client{
    @OneToMany
    public List<Notification> notifications = new LinkedList<>();
    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> favorites;


    public Player() {
    }

    public Player(String name, String email, String password, Address address) {
        super(name, email, password, address);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotifications(Notification notification) {
        this.notifications.add(notification);
    }

    public void viewNotifications(){
        for(Notification notif : this.notifications){
            System.out.println("Notificação " + notif.getArrival());
            System.out.println(notif.getName() + " " + notif.getMessage());
            System.out.println();
        }
    }

    public List<Game> getFavorites(){
        return favorites;
    }

    public void setFavorite(Game game){
        favorites.add(game);
    }

    public void removeFavorite(Product product){
        favorites.remove(product);
    }

    @Override
    public void update(Product product) {
        Notification not = (Notification) this.notifications.stream()
                .peek(x -> System.out.println(x.getArrival() + product.getName() + " : " + x.getMessage()));
        if(not.getName().equals("inStock") && !this.shopping.contains(product)){
            this.setShopping(product);
            System.out.println("Produto adicionado ao carrinho");
        }
    }

    @Override
    @Transient
    public void getNotification(IObserver observer, Notification notification) {
        Player pl = (Player) observer;
        pl.notifications.add(notification);
    }

}
