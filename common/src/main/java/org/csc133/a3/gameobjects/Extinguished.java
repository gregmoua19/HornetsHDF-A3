package org.csc133.a3.gameobjects;

public class Extinguished implements FireState{

    private static Extinguished instance = new Extinguished();

    private Extinguished() {};

    public static Extinguished instance(){
        return instance;
    }

    public String toString() {
        return "Extinguished";
    }

    @Override
    public void updateState(Fire fire) {
    }
}
