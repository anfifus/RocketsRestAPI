package com.aleixalvarez.rockets.rocketsrestapi.repository;

import com.aleixalvarez.rockets.rocketsrestapi.domain.Propellant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropellantRepository extends CrudRepository<Propellant,Long> {
}
