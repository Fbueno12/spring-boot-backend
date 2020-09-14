package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.ItemDemand;

@Repository
public interface ItemDemandRepository extends JpaRepository<ItemDemand, Integer> {

}
