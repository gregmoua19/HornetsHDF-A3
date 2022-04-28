package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Dimension;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Random;

public class Building extends Fixed{
    int value;
    int damage;
    int budgetArea;
    int maxDamage;
    int originalValue;
    int valuePercentage;

    public Building(int value){
        originalValue = value;
        this.value = value;
        init();
    }

    @Override
    public void init() {
        this.color = ColorUtil.rgb(255,0,0);
        damage = 0;
        valuePercentage = 100;
        maxDamage = damage;
        point = new Point(1,1);
        dim = new Dimension(1,1);
        budgetArea = 1000;
    }
    public void setBudgetArea(int budgetArea){
        this.budgetArea = budgetArea;
    }

    public int getBudgetArea(){
        return budgetArea;
    }
    @Override
    public boolean collidesWith(GameObject other) {
        return false;
    }

    @Override
    public int getSize() {
        return dim.getHeight();
    }

    @Override
    public String toString() {
        return "Building";
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage){
        if(damage >= maxDamage) {
            maxDamage = damage;
            this.damage = damage;
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void takeDamage(int damage){
        value -= damage ;
    }
    public int getValue() {
        return value;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int offX = point.getX() + containerOrigin.getX();
        int offY = point.getY() + containerOrigin.getY();
        int bottomX = offX + 10 + dim.getWidth();
        int bottomY = offY + 10 + dim.getHeight();
        g.setColor(ColorUtil.rgb(255,0,0));
        g.drawRect(offX,offY,dim.getWidth(),dim.getHeight());
        g.drawString("V: " + originalValue, bottomX , bottomY- 100);
        g.drawString("D: " + maxDamage + "%", bottomX , bottomY - 50);
    }

    public void setFireinBuilding(Fire fire) {
        //use random variable to determine size
        Random rand = new Random();

        //subtract from total area
        //was unsure of how area would work with fires
        //was it mathemtical area of all fires added together vs
        //1000 area units for building or 1000 value for fire?
        setBudgetArea(budgetArea - fire.getArea());

        //set location
        fire.setLocation(new Point(rand.nextInt(
                dim.getWidth())+
                point.getX(),rand.nextInt(
                dim.getHeight())+
                point.getY()));
        fire.start();
    }
}
