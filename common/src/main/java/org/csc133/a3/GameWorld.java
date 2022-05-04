package org.csc133.a3;

import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a3.gameobjects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
    private ArrayList<GameObject> allGameObjects;
    private Helicopter helicopter;
    private ArrayList<Fire> fires;
    private ArrayList<Building> buildings;
    private Helipad helipad;
    private River river;
    private int increment;
    private int NUMBER_OF_BUILDINGS;
    private int NUMBER_OF_FIRES;
    private double TOTAL_LOSS;
    private int MAX_DAMAGE;

    //constructor to make and then add everything to items
    public GameWorld(){
        NUMBER_OF_BUILDINGS = 3;
        Random rand = new Random();
        helicopter = new Helicopter();
        helicopter.translate(Game.DISP_W/2, Game.DISP_H/2);
        helipad = new Helipad();
        river = new River();
        NUMBER_OF_FIRES = rand.nextInt(3)+6;
        fires = new ArrayList<>();
        buildings = new ArrayList<>();
        TOTAL_LOSS = 0;
        MAX_DAMAGE = 0;

        //initialize fires as unstarted and give
        //them a random size but no point
        for(int i = 0; i < NUMBER_OF_FIRES;i++) {
            fires.add(new Fire(UnStarted.instance()));
            fires.get(i).setSize(rand.nextInt(5)+7);
        }

        //create buildings with randomized value
        for(int i = 0; i < NUMBER_OF_BUILDINGS;i++) {
            buildings.add(
                    new Building((rand.nextInt(10)+1) * 100));
        }

        int fireCounter = 0;

        allGameObjects = new ArrayList<>();


        //for loop for determining where the buildings will be placed
        for(int i = 0; i < NUMBER_OF_BUILDINGS;i++) {
            if(i == 0) {
                buildings.get(i).setPoint(
                        new Point(
                                Game.DISP_W/10,
                                2 * Game.DISP_H/7));
                buildings.get(i).setDim(
                        new Dimension(
                                Game.DISP_W/7,
                                2*Game.DISP_H/5));
            } else if (i == 1) {
                buildings.get(i).setPoint(
                        new Point(
                                Game.DISP_W/4,
                                9 * Game.DISP_H/10));
                buildings.get(i).setDim(
                        new Dimension(
                                2 * Game.DISP_W/ 3 ,
                                Game.DISP_H/10));
            } else {
                buildings.get(i).setPoint(
                        new Point(
                                4 * Game.DISP_W/5,
                                3 * Game.DISP_H/10 ));
                buildings.get(i).setDim(
                        new Dimension(
                                Game.DISP_W/9,
                                Game.DISP_H / 3));
            }

            allGameObjects.add(buildings.get(i));
        }

        allGameObjects.add(helicopter);
        allGameObjects.add(helipad);
        allGameObjects.add(river);
        for(int i = 0; i < NUMBER_OF_FIRES;i++) {
            //if 1/3 fire set in left building
            if(fireCounter < (NUMBER_OF_FIRES / 3) + 1) {
                fires.get(i).setBuilding(0);
                buildings.get(0).setFireinBuilding(fires.get(i));

            //else if 2/3 fire set in top  building
            } else if (fireCounter < ((NUMBER_OF_FIRES / 3) * 2) + 1) {
                fires.get(i).setBuilding(1);
                buildings.get(1).setFireinBuilding(fires.get(i));

                //else set in right building
            } else {
                fires.get(i).setBuilding(2);
                buildings.get(2).setFireinBuilding(fires.get(i));

            }

            fireCounter++;
            allGameObjects.add(fires.get(i));
        }
    }

    public void quit() {
        Display.getInstance().exitApplication();
    }

    void init() {
        new Game().show();
    }
    public void tick() {

        //if all fires out and speed is 0
        //and resting on helipad you win
        boolean allFiresOut = false;
        for(GameObject go: allGameObjects) {
            if(go instanceof Fire) {
                if (go.getSize() > 0) {
                    allFiresOut = true;
                }
            }
        }
        Building building1 = (Building)allGameObjects.get(0);
        Building building2 = (Building)allGameObjects.get(1);
        Building building3 = (Building)allGameObjects.get(2);
        if (allFiresOut == false &&
                helicopter.getSpeed() == 0 &&
                landCopter()) {
            //victory condition
            //ask to play again or quit
            int score = 0;
            for(int i = 0 ; i < 3; i ++) {
                score += allGameObjects.get(i).getValue();
            }
            if(Dialog.show("You win",
                    "Your score: " + score,
                    "Play again", "Quit")){
                init();
            }else{
                quit();
            }

            //if you run out of fuel you lose
        } else if (helicopter.getFuel() <= 0 ||
                        (building1.getValue() == 0 &&
                        building2.getValue() == 0 &&
                        building3.getValue() == 0))
         {
            //defeat
            //ask to play again or quit

            if(Dialog.show("Game over",
                    "Play Again?",
                    "Yes",
                    "No")){
                init();
            }else{
                quit();
            }
        } else {
        }

        //move around
            for (GameObject go : allGameObjects) {
                if((go instanceof Fire)) {
                    Fire fire = (Fire)go;
                    if(go.getSize() <= 0) {
                        ((Fire) go).setState(Extinguished.instance());
                    }
                }
                if(increment % 2 == 0) {
                    if (go instanceof Fire) {

                        Fire fire = (Fire) go;
                        if(fire.getCurrentState() instanceof Extinguished) {
                            continue;
                        }
                        go.grow();
                    }
                }

            }


        helicopter.walk();
        increment++;

    }

    public boolean landCopter(){

        //this conditional makes sure the location of the helicopter is
        //within the helipad
        //if copter.getX is > pad.getX but less than pad.getX + its width
        //and copter.getY is > pad.getY but less than pad.getY + its length
        if((helicopter.getPoint().getX() > helipad.getPoint().getX()
                && helicopter.getPoint().getX() <
                (helipad.getPoint().getX() + helipad.getDim().getWidth()))
                && helicopter.getPoint().getY() > helipad.getPoint().getY()
                && helicopter.getPoint().getY() <
                (helipad.getPoint().getY() + helipad.getDim().getHeight())){
            return true;
        }
        return false;
    }


    public ArrayList<GameObject> getGameObjectCollection(){
        return allGameObjects;
    }

    public String getHeading() {
        return String.valueOf(helicopter.getHeading());
    }

    public String getSpeed() {
        return String.valueOf(helicopter.getSpeed());
    }

    public String getFuel() {
        return String.valueOf(helicopter.getFuel());
    }

    public String getFires() {
        int counter = 0;
        for (GameObject go : allGameObjects) {
            if (go instanceof Fire) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    public String getFireSize() {
        int counter = 0;
        for (GameObject go : allGameObjects) {
            if (go instanceof Fire) {
                counter += go.getSize();
            }
        }
        return String.valueOf(counter);
    }

    //going to seperate fire sizes to calculate
    //average damage per building
    public String getDamage() {

        //individual size variables for each building
        double average1 = 0;
        double average2 = 0;
        double average3 = 0;

        for (GameObject go : allGameObjects) {

            //verify GameObject is Fire
            if (go instanceof Fire) {

                //Check if connected to building 0, 1, or 2
                if(go.getBuilding() == 0) {
                    average1 += go.getSize();
                } else if (go.getBuilding() == 1) {
                    average2 += go.getSize();
                } else {
                    average3 += go.getSize();
                }
            }
        }
        //size /= 1000 to calculate damage then
        //size *= 100 for percentage conversion
        //so we can instead use size /= 10
        average1 /= 10;
        average2 /= 10;
        average3 /= 10;

        //set damage for each individual building
        allGameObjects.get(0).setDamage((int)average1);
        allGameObjects.get(1).setDamage((int)average2);
        allGameObjects.get(2).setDamage((int)average3);
        int all = (int) ((average1 + average2 + average3) / 3);

        //compare to see if the new average damage
        //is greater than the previous max
        if(MAX_DAMAGE < all) {
            MAX_DAMAGE = all;
        }
        return String.valueOf(MAX_DAMAGE);
    }

    public String getLoss() {
        double average1 = 0;
        double average2 = 0;
        double average3 = 0;
        for (GameObject go : allGameObjects) {

            //verify GameObject is Fire
            if (go instanceof Fire) {

                //Check if connected to building 0, 1, or 2
                if(go.getBuilding() == 0) {
                    average1 += go.getSize();
                } else if (go.getBuilding() == 1) {
                    average2 += go.getSize();
                } else {
                    average3 += go.getSize();
                }
            }
        }

        average1 /= 100;
        average2 /= 100;
        average3 /= 100;
        Building building1 = (Building) allGameObjects.get(0);
        Building building2 = (Building) allGameObjects.get(1);
        Building building3 = (Building) allGameObjects.get(2);
        building1.takeDamage((int)average1);
        building2.takeDamage((int)average2);
        building3.takeDamage((int)average3);
        TOTAL_LOSS += average1;
        TOTAL_LOSS += average2;
        TOTAL_LOSS += average3;
        return String.valueOf((int)TOTAL_LOSS);
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }
}

