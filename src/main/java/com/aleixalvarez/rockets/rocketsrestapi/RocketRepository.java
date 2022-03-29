package com.aleixalvarez.rockets.rocketsrestapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketRepository extends CrudRepository<Rocket,Long> {
}
