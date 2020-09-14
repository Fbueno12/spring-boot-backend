package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
