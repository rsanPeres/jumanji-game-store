package com.jumanji.games.repository;

import com.jumanji.games.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAddressRepository extends JpaRepository<Address, String> {
}
