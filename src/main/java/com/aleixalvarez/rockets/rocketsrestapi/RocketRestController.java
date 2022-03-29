package com.aleixalvarez.rockets.rocketsrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService rocketService;

    @Autowired
    public RocketRestController(RocketService rocketService) {
        this.rocketService = rocketService;
    }

    @GetMapping("/rockets")
    public List<Rocket> getAllRockets(){
        return rocketService.getAllRockets();
    }
    @PostMapping("/rockets")
    public Rocket addRocket(@RequestBody Rocket rocketToAdd){
        return rocketService.addRocket(rocketToAdd);
    }
    @DeleteMapping("/rockets")
    public void deleteAllRockets(){
        rocketService.deleteAllRockets();
    }

    @GetMapping("/rockets/{id}")
    public Rocket getRocketByID(@PathVariable Long id){
        return rocketService.getRocketByID(id);
    }
    @PutMapping("/rockets/{id}")
    public Rocket updateRocketByID(@PathVariable Long id,@RequestBody Rocket rocketUpdate){
        return rocketService.updateRocket(id,rocketUpdate);
    }
    @DeleteMapping("/rockets/{id}")
    public void deleteRocketByID(@PathVariable Long id){
        rocketService.deleteRocketByID(id);
    }
    @PostMapping("/rockets/{id}/movement")
    public Rocket changeVelocity(@PathVariable Long id,@RequestBody Movement typeMovement){
        return rocketService.changeVelocityByID(id,typeMovement);
    }
    @PostMapping("/rockets/{id}/propellants")
    public List<Propellant> addPropellantToRocket(@PathVariable Long id, @RequestBody Propellant propellantToAdd) throws Exception {
        return rocketService.addPropellantToRocket(id,propellantToAdd);
    }
    @GetMapping("/rockets/{id}/propellants")
    public List<Propellant> getAllPropellantsOfTheRocket(@PathVariable Long id) throws Exception {
        return rocketService.getAllPropellantsOfTheRocket(id);
    }
    @DeleteMapping("/rockets/{id}/propellants")
    public void deleteAllPropellantsByRocketID(@PathVariable Long id){
        rocketService.deleteAllPropellantsByRocketID(id);
    }
    @GetMapping("/rockets/{id}/propellants/{idPropellant}")
    public Propellant getPropellantById(@PathVariable Long id,@PathVariable Long idPropellant) throws Exception {
        return  rocketService.getPropellantById(id,idPropellant);
    }
    @PutMapping("/rockets/{id}/propellants/{idPropellant}")
    public Propellant updatePropellantById(@PathVariable Long id,@PathVariable Long idPropellant,@RequestBody Propellant propellant) throws Exception {
        return  rocketService.updatePropellantById(id,idPropellant,propellant);
    }
    @DeleteMapping("/rockets/{id}/propellants/{idPropellant}")
    public void deleteAllPropellantsByRocketID(@PathVariable Long id,@PathVariable Long idPropellant) throws Exception {
        rocketService.deletePropellantsById(id,idPropellant);
    }
}
