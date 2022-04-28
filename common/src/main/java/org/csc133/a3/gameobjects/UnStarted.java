package org.csc133.a3.gameobjects;

public class UnStarted implements FireState{
    private static UnStarted instance = new UnStarted();

    private UnStarted(){};

    public static UnStarted instance() {
        return instance;
    }

    public String toString() {
        return "UnStarted";
    }
    @Override
    public void updateState(Fire fire) {
        fire.setState(Burning.instance());
    }
}