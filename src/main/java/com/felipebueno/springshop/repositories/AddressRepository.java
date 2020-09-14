package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
