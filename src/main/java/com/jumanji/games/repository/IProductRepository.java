package com.jumanji.games.repository;

import com.jumanji.games.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT s.product FROM Shopping s WHERE s.client.id = :clientId")
    List<Product> getShoppingByClientId(@Param("clientId") Long clientId);

}
