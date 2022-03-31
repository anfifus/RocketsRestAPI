package com.aleixalvarez.rockets.rocketsrestapi;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RocketRepository extends CrudRepository<Rocket,Long> {
    @Query(value = "SELECT SUM(pro.actualPower) FROM Rocket ro INNER JOIN Propellant pro ON ro.id = pro.rocket.id")
    int getTotalVelocityOfRocket();
}
