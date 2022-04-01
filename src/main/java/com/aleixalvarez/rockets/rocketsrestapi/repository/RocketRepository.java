package com.aleixalvarez.rockets.rocketsrestapi.repository;

import com.aleixalvarez.rockets.rocketsrestapi.domain.Rocket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketRepository extends CrudRepository<Rocket,Long> {
}
