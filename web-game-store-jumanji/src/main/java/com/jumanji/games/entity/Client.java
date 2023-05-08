package com.jumanji.games.entity;

import com.jumanji.games.Interfaces.IObserver;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Client implements IObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @Column(unique = true)
    protected String email;
    protected String password;
    @ManyToOne
    protected Address address;
    @ManyToMany
    @JoinTable(
            name = "shopping",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    protected List<Product> shopping;

    public Client() {
    }

    public Client(String name, String email, String password, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getShopping(){
        return shopping;
    }

    public void setShopping(Product product){
        this.shopping.add(product);
    }
    public void removeShopping(Product product){
        this.shopping.remove(product);
    }

    public void showShopping(){
        System.out.println("Lista de compras");

        for(Product prod : shopping){
            System.out.println(prod.name + " preço " + prod.price);
            System.out.println();
        }
    }
}
