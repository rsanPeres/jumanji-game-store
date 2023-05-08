package com.jumanji.games.entity;

import com.jumanji.games.Interfaces.IProductSubject;
import com.jumanji.games.entity.enumerable.ProductType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Product implements IProductSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected BigDecimal price;
    protected LocalDateTime arrival = LocalDateTime.now();
    protected Date sold;
    protected Integer amount;
    protected ProductType productType;
    @ManyToMany(mappedBy = "shopping")
    protected List<Client> buyer;

    public Product() {
    }

    public Product(String name, BigDecimal price, Integer amount, ProductType prodType) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.productType = prodType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Date getSold() {
        return sold;
    }

    public void setSold(Date sold) {
        this.sold = sold;
    }

    public Enum<ProductType> getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public abstract boolean removeFromStock(int qtd);

    public abstract void addInStock(int qtd);
}
