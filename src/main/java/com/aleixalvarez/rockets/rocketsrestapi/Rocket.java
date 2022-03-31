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
    @OneToMany(mappedBy = "rocket",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Propellant> propellantList = new ArrayList<>();
    private int currentPowerOfRocket = 0;
    private int MaxVelocityOfRocket = 0;
    public Rocket() {
    }

    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public Long getId() {
        return id;
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


    public List<Propellant> getPropellantList() {
        return propellantList;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }

    public int getCurrentPowerOfRocket() {
        return currentPowerOfRocket;
    }

    public void updateCurrentTotalPower(int currentPowerOfRocket,int powerOfPropellant) throws Exception {
        checkCurrentPower(powerOfPropellant);
        this.currentPowerOfRocket += currentPowerOfRocket;
    }

    private void checkCurrentPower(int currentPowerOfRocket) throws Exception {
        if(currentPowerOfRocket < 0) throw new Exception("The power must be superior of 0");

        if( MaxVelocityOfRocket < currentPowerOfRocket) throw new Exception("The rocket is in danger too much velocity");
    }

    public int getMaxVelocityOfRocket() {
        return MaxVelocityOfRocket;
    }

    public void setMaxVelocityOfRocket(int maxVelocityOfRocket) {
        MaxVelocityOfRocket = maxVelocityOfRocket;
    }
}
