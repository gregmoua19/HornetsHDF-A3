package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Dimension;
import org.csc133.a3.Game;

import java.util.Random;

public class Fire extends Fixed{
    private FireState currentState;
    private int size;
    private int building;

    public Fire(FireState currentState){

        this.currentState = currentState;
        init();
    }

    public void init(){
        this.color = ColorUtil.MAGENTA;
        size = 1;
        dim = new Dimension(size,size);
        point = new Point(new Random().nextInt(Game.DISP_W),
                new Random().nextInt(Game.DISP_H));
    }

    public void grow(){
            size += new Random().nextInt(3);
    }

    public void setLocation(Point point){
        this.point.setX(point.getX());
        this.point.setY(point.getY());
        dim.setWidth(point.getX());
        dim.setHeight(point.getY());
    }

    public int getArea() {
        return dim.getWidth();
    }

    @Override
    public boolean collidesWith(GameObject other) {
        return (other.getPoint().getY() <= point.getY())
                && (other.getPoint().getY() + other.getDim().getHeight()
                >= point.getY())
                && (other.getPoint().getX() <= point.getX())
                && (other.getPoint().getX() + other.getDim().getWidth()
                >= point.getX());
    }


    public void setSize(int size) {

            dim.setWidth(size);
            dim.setHeight(size);
            this.size = size;
        if(size <= 0) {
            setState(Extinguished.instance());
            this.size = 0;
        }
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Fire";
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int x = containerOrigin.getX() +
                point.getX();
        int y = containerOrigin.getY() +
                point.getY();
        g.setColor(color);
        if(size > 0) {
            g.fillArc(x,y, size, size,0, 360);
            g.drawString ("" + size, x + size, y + size);
        }
    }

    public void start() {
        currentState.updateState(this);
    }

    public void setBuilding(int building){
        this.building = building;
    }

    public int getBuilding(){
        return building;
    }

    public FireState getCurrentState(){
        return currentState;
    }

    public void setState(FireState state) {
        currentState = state;
    }
}

