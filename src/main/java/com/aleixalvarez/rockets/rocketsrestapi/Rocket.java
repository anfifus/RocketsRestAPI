package com.aleixalvarez.rockets.rocketsrestapi;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Rocket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String code;
    @OneToMany(mappedBy = "rocket")
    @JsonManagedReference
    private List<Propellant> propellantList = new ArrayList<>();

    public Rocket() {
    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    private void checkCode(String code) throws Exception {
        if (code.length() != 8) throw new Exception("The format of the code is incorrect");
    }

    public String getCode() {
        return code;
    }

    public void addPropellant(Propellant propellant) throws Exception {
            propellant.setRocket(this);
            propellantList.add(propellant);
    }
    public void increasePower(){
        for (Propellant currentPropellant:propellantList) {
            currentPropellant.updateActualPower(10);
        }
    }
    public void decreasePower(){
        for (Propellant currentPropellant:propellantList) {
            currentPropellant.updateActualPower(-10);
        }
    }

    public List<Propellant> getPropellantList() {
        return propellantList;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
