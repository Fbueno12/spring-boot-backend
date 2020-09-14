package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
