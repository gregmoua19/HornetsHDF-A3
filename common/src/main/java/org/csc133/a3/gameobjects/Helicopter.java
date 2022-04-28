package org.csc133.a3.gameobjects;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point;
import org.csc133.a3.Game;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.Graphics;
import org.csc133.a3.gameobjects.Helipad;

public class Helicopter extends Movable{
    private int fuel;
    private int water;
    private Helipad helipad;
    private Point lineLocation;
    private double radianHeading;
    public Helicopter(){
        init();
    }

    public void init(){
        this.color = ColorUtil.YELLOW;
        int height = Game.DISP_H / 50;
        int width = Game.DISP_W / 30;
        dim = new Dimension(width, height);
        radianHeading = 0;
        fuel = 25000;
        speed = 0;
        water = 0;
        helipad = new Helipad();
        heading = 0;

        point = new Point(
                helipad.getPoint().getX() + width-40,
                helipad.getPoint().getY() + height-10);
        lineLocation = new Point(
                point.getX() + 25,
                point.getY() - 100
        );
    }

    public int getFuel() { return fuel;}

    public int getSpeed() {return speed;}

    public int getHeading() {
        return heading;
    }

    public void walk(){

        //take angle
        //360/15 = 24
        //that means there's only 24 places you can draw the line
        fuel = fuel - ((speed * speed) + 5);

        //4 if statements based off quadrants
        //take into account that y is flipped
        //1 to 89
        //91 to 179
        //181 to 269
        //271 to 359 AKA 0-1
        //specific conditionals for 0,90,180,270 implemented
        int decX = (int)(point.getX() - speed*2*Math.cos(radianHeading));
        int decY = (int)(point.getY() - speed*2*Math.sin(radianHeading));
        if(heading > 0 && heading < 90) {
            point.setX(decX);
            point.setY(decY);
        } else if (heading > 90 && heading < 180) {
            point.setX(decX);
            point.setY(decY);
        } else if (heading > 180 && heading < 270) {
            point.setX(decX);
            point.setY(decY);
        } else if (heading > 270){
            point.setX(decX);
            point.setY(decY);
        } else if (heading == 0){
            point.setY(decY);
        } else if (heading == 90) {
            point.setX(decX);
        } else if (heading == 180) {
            point.setY(decY);
        } else if (heading == 270) {
            point.setX(decX);
        }

        lineLocation.setX((int)((point.getX() + 25)
                - 80 * Math.cos(radianHeading)));
        lineLocation.setY((int)((point.getY() + 25)
                - 80 * Math.sin(radianHeading)));

    }

    @Override
    public boolean collidesWith(GameObject other) {
        if(other.toString() == "River") {
            return collidesWithRiver((River)other);
        }

        if(other.toString() == "Fire") {
            return collidesWithFire((Fire)other);
        }

        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String toString() {
        return "Helicopter";
    }

    @Override
    public Dimension getSize(Dimension d) {
        return d;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int x = containerOrigin.getX() +
                point.getX();
        int y = containerOrigin.getY() +
                point.getY();
        g.setColor(color);


        //drawing a filled circle and line relative to its location
        g.fillArc(x,y,50,50,0,360);
        g.drawLine(x + 25,y + 25,

                //x1 y1 guarantees that the line starts
                //in the center of the circle but the
                //x2 y2 are dictated by the angle of the heading
                lineLocation.getX() + containerOrigin.getX(),
                lineLocation.getY() + containerOrigin.getY());
        g.drawString("Water: " + water,x,y  + 100);

        g.drawString("Fuel: " + fuel, x,y + 60);
    }

    @Override
    public void steerLeft() {
        if (heading == 0 || heading == 360) {
            heading = 345;
        } else {
            heading -= 15;
        }
        radianHeading = Math.toRadians(360 / 24 * (heading / 15) + 90);

    }

    @Override
    public void steerRight() {
        if (heading == 360 || heading == 0) {
            heading = 15;
        } else {
            heading += 15;
        }
        radianHeading = Math.toRadians(360 / 24 * (heading / 15) + 90);

    }

    public void speedUp(boolean direction) {
        if(direction && speed < 10) {
            this.speed += 1;
        } else if (direction == false && speed > 0){
            this.speed -= 1;
        }
    }

    public void drink() {
        if(water < 1000 && speed <= 2) {
            this.water += 100;
        }

        if(water > 1000) {
            water = 1000;
        }
    }

    public void fight(GameObject fire) {
        if (collidesWithFire(fire)) {
            water -= fire.getSize();
            fire.setSize(-water);
            if (water < 0) {
                water = 0;
            }
            if(fire.getSize() <= 0) {
                fire.setState(Extinguished.instance());
            }
        }
    }
    public boolean collidesWithRiver(River river) {
        int YRiver = river.getPoint().getY();
        return (YRiver <= point.getY()) &&
                YRiver+ river.getDim().getHeight()>= point.getY();
    }

    public boolean collidesWithFire(GameObject fire) {
        int fireXLoc = fire.getPoint().getX();
        int fireYLoc = fire.getPoint().getY();

        //return statement that checks for all
        //four corners of bounding rectangle
        return ((fireYLoc <= point.getY())

                //top left corner
                && (fireYLoc + fire.getSize() >= point.getY())
                && (fireXLoc <= point.getX())
                && (fireXLoc + fire.getSize() >= point.getX())

                //top right corner
                || (fireYLoc <= point.getY())
                && (fireYLoc + fire.getSize() >= point.getY())
                && (fireXLoc <= dim.getWidth())
                && (fireXLoc + fire.getSize() >= dim.getWidth())

                //bottom left corner
                ||(fireYLoc <= dim.getHeight())
                && (fireYLoc + fire.getSize() >= dim.getHeight())
                && (fireXLoc <= point.getX())
                && (fireXLoc + fire.getSize() >= point.getX())

                //bottom right corner
                ||(fireYLoc <= dim.getHeight())
                && (fireYLoc + fire.getSize() >= dim.getHeight())
                && (fireXLoc <= dim.getWidth())
                && (fireXLoc + fire.getSize() >= dim.getWidth()));
    }
}
