package com.aleixalvarez.rockets.rocketsrestapi.service;

import com.aleixalvarez.rockets.rocketsrestapi.domain.Propellant;
import com.aleixalvarez.rockets.rocketsrestapi.domain.Rocket;
import com.aleixalvarez.rockets.rocketsrestapi.repository.PropellantRepository;
import com.aleixalvarez.rockets.rocketsrestapi.repository.RocketRepository;
import com.aleixalvarez.rockets.rocketsrestapi.rest.dto.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RocketService {
    private static final String ACCELERATE = "ACCELERATE";
    private static final int ACCELERATE_POTENCY = 10;
    private static final String SLOWDOWN = "SLOWDOWN";
    private static final int SLOWDOWN_POTENCY = -10;
    private RocketRepository rocketRepository;
    private PropellantRepository propellantRepository;


    public RocketService(RocketRepository rocketRepository, PropellantRepository propellantRepository) {
        this.rocketRepository = rocketRepository;
        this.propellantRepository = propellantRepository;
    }

    public List<Rocket> getAllRockets() {
        return (List<Rocket>) rocketRepository.findAll();
    }

    public Rocket addRocket(Rocket rocketToAdd) {
        return  rocketRepository.save(rocketToAdd);
    }

    public void deleteAllRockets() {
        rocketRepository.deleteAll();
    }

    public Rocket getRocketByID(long id) {
        return rocketRepository.findById(id).get();
    }

    public Rocket updateRocket(long id, Rocket rocketUpdate) throws Exception {
        Rocket rocketToBeUpdated = getRocketByID(id);
        rocketToBeUpdated.setCode(rocketUpdate.getCode());
        return rocketRepository.save(rocketToBeUpdated);
    }

    public void deleteRocketByID(long id) {
        rocketRepository.deleteById(id);
    }

    public Rocket changeVelocityByID(long id, Movement typeMovement) throws Exception {
        Rocket rocketToChange = getRocketByID(id);
        rocketToChange.setMaxVelocityOfRocket(getMaxVelocityOfRocket(rocketToChange));
        if(typeMovement.getTypeMovement().equalsIgnoreCase(ACCELERATE)){
            accelerate(rocketToChange, typeMovement.getTimesMove());
        }
        else if(typeMovement.getTypeMovement().equalsIgnoreCase(SLOWDOWN)){
            slowDown(rocketToChange, typeMovement.getTimesMove());
        }
        rocketRepository.save(rocketToChange);
        return rocketToChange;
    }

    private int getMaxVelocityOfRocket(Rocket rocketToChange) {
        int maxVelocityOfRocket = 0;
        for (Propellant currentPropellant: rocketToChange.getPropellantList()) {
            maxVelocityOfRocket +=currentPropellant.getMaxPower();
        }
        return maxVelocityOfRocket;
    }

    private void slowDown(Rocket rocket, int timesSlowdown) throws Exception {
        for (int i = 0; i < timesSlowdown; i++) {
            for (Propellant currentPropellant: rocket.getPropellantList()) {
                currentPropellant.updateActualPower(SLOWDOWN_POTENCY);
                rocket.updateCurrentTotalPower(SLOWDOWN_POTENCY,currentPropellant.getActualPower());

            }
        }
    }

    private void accelerate(Rocket rocket, int times) throws Exception {
        for (int i = 0; i < times; i++) {
            for (Propellant currentPropellant: rocket.getPropellantList()) {
                currentPropellant.updateActualPower(ACCELERATE_POTENCY);
                rocket.updateCurrentTotalPower(ACCELERATE_POTENCY,currentPropellant.getActualPower());
            }

        }
    }

    public List<Propellant> addPropellantToRocket(long id, Propellant propellantToAdd) throws Exception {
        Rocket rocketToAddPropellant = getRocketByID(id);
        rocketToAddPropellant.addPropellant(propellantToAdd);
        return (List<Propellant>) propellantRepository.saveAll(rocketToAddPropellant.getPropellantList());
    }

    public List<Propellant> getAllPropellantsOfTheRocket(long id) {
        Rocket rocket = getRocketByID(id);
        return rocket.getPropellantList();
    }

    public void deleteAllPropellantsByRocketID(long id) {
        Rocket rocket = getRocketByID(id);
        propellantRepository.deleteAll(rocket.getPropellantList());
    }

    public Propellant getPropellantById(long id, long idPropellant) throws Exception {
        Rocket rocket = getRocketByID(id);
        for (Propellant currentPropellant:rocket.getPropellantList()) {
            if(currentPropellant.getId() == idPropellant){
                return currentPropellant;
            }
        }
        throw new Exception("Error we didn't have that propellant");
    }

    public Propellant updatePropellantById(Long id, Long idPropellant,Propellant propellantUpdated) throws Exception {
        Propellant propellant = getPropellantById(id,idPropellant);
        propellant.setMaxPower(propellantUpdated.getMaxPower());
        return propellantRepository.save(propellant);
    }

    public void deletePropellantsById(Long id, Long idPropellant) throws Exception {
        Propellant propellantToDelete = getPropellantById(id, idPropellant);
        propellantRepository.delete(propellantToDelete);
    }
}
