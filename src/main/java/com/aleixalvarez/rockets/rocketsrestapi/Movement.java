package com.aleixalvarez.rockets.rocketsrestapi;

public class Movement {
    private static final String ACCELERATE = "ACCELERATE";
    private static final String SLOWDOWN = "SLOWDOWN";
    private String typeMovement;
    private int timesMove;

    public Movement() {
    }

    public Movement(String typeMovement, int timesMove) throws Exception {
        checkTypeMovement(typeMovement);
        checkTimesMove(timesMove);
        this.typeMovement = typeMovement;
        this.timesMove = timesMove;
    }



    private void checkTypeMovement(String typeMovement) throws Exception {
        if(!typeMovement.equalsIgnoreCase(ACCELERATE) && !typeMovement.equalsIgnoreCase(SLOWDOWN)) throw new Exception("Error we have two type of movement 1 for accelerate and 2 to slowdown");
    }
    private void checkTimesMove(int timesMove) throws Exception {
        if(timesMove < 0) throw new Exception("The times we have to move can't be minor of 0");
    }

    public String getTypeMovement() {
        return typeMovement;
    }

    public void setTypeMovement(String typeMovement) throws Exception {
        checkTypeMovement(typeMovement);
        this.typeMovement = typeMovement;
    }

    public int getTimesMove() {
        return timesMove;
    }

    public void setTimesMove(int timesMove) throws Exception {
        checkTimesMove(timesMove);
        this.timesMove = timesMove;
    }
}
