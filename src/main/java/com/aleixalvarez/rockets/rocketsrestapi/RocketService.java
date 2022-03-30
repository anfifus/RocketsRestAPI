package com.aleixalvarez.rockets.rocketsrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RocketService {
    private static final String ACCELERATE = "ACCELERATE";
    private static final String SLOWDOWN = "SLOWDOWN";
    private RocketRepository rocketRepository;
    private PropellantRepository propellantRepository;


    @Autowired
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

    public Rocket getRocketByID(Long id) {
        return rocketRepository.findById(id).get();
    }

    public Rocket updateRocket(Long id, Rocket rocketUpdate) throws Exception {
        Rocket rocketToBeUpdated = getRocketByID(id);
        rocketToBeUpdated.setCode(rocketUpdate.getCode());
        return rocketRepository.save(rocketToBeUpdated);
    }

    public void deleteRocketByID(Long id) {
        Rocket rocketToBeDeleted = getRocketByID(id);
        rocketRepository.delete(rocketToBeDeleted);
    }

    public Rocket changeVelocityByID(Long id, Movement typeMovement) throws Exception {
        Rocket rocketToChange = getRocketByID(id);

        if(typeMovement.getTypeMovement().equalsIgnoreCase(ACCELERATE)){
            accelerate(rocketToChange, typeMovement.getTimesMove());
        }
        else if(typeMovement.getTypeMovement().equalsIgnoreCase(SLOWDOWN)){
            slowDown(rocketToChange, typeMovement.getTimesMove());
        }
        rocketRepository.save(rocketToChange);
        return rocketToChange;
    }
    private static void slowDown(Rocket rocket, int timesSlowdown) throws Exception {
        for (int i = 0; i < timesSlowdown; i++) {
            for (Propellant currentPropellant: rocket.getPropellantList()) {
                rocket.setCurrentPowerOfRocket(currentPropellant.updateActualPower(-10));
            }
        }
    }

    private static void accelerate(Rocket rocket, int times) throws Exception {
        for (int i = 0; i < times; i++) {
            for (Propellant currentPropellant: rocket.getPropellantList()) {
              rocket.setCurrentPowerOfRocket(currentPropellant.updateActualPower(10));
            }
        }
    }

    public List<Propellant> addPropellantToRocket(Long id, Propellant propellantToAdd) throws Exception {
        Rocket rocketToAddPropellant = getRocketByID(id);
        rocketToAddPropellant.addPropellant(propellantToAdd);
        return (List<Propellant>) propellantRepository.saveAll(rocketToAddPropellant.getPropellantList());
    }

    public List<Propellant> getAllPropellantsOfTheRocket(Long id) {
        Rocket rocket = getRocketByID(id);
        return rocket.getPropellantList();
        //return propellantRepository.getAllByRocket(rocket);
    }

    public void deleteAllPropellantsByRocketID(Long id) {
        Rocket rocket = getRocketByID(id);
        propellantRepository.deleteAll(rocket.getPropellantList());
    }

    public Propellant getPropellantById(Long id, Long idPropellant) throws Exception {
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
        Propellant propellantToDelete = getPropellantById(id,idPropellant);
        propellantRepository.delete(propellantToDelete);
    }
}
