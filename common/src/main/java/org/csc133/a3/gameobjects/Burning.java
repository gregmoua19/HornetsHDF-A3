package org.csc133.a3.gameobjects;

public class Burning implements FireState {

    private static Burning instance = new Burning();

    private Burning(){}

    public static Burning instance(){
        return instance;
    }

    public String toString() {
        return "Burning";
    }
    @Override
    public void updateState(Fire fire) {
        fire.setState(Extinguished.instance());
    }

}

