package com.aleixalvarez.rockets.rocketsrestapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
public interface PropellantRepository extends CrudRepository<Propellant,Long> {
    List<Propellant> getAllByRocket(Rocket rocket);
    Propellant getByRocket(Rocket rocket);
}
