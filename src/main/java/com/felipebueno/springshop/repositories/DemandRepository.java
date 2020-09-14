package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer>{

}
