package com.felipebueno.springshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipebueno.springshop.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
