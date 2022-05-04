package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a3.Game;

public class Helipad extends Fixed{

    public Helipad(){
        init();
    }

    public void init(){

        this.color = ColorUtil.GRAY;
        //set the location of the helipad equal
        //to middle of screen at the bottom (9/10 of the way down)
        point = new Point(Game.DISP_W/2,Game.DISP_H/4);
        dim = new Dimension(Game.DISP_W/20,Game.DISP_W/20);
    }

    public void draw(Graphics g) {

    }

    //need this specific getter to make sure
    //the helicopter and helipad spawn with each other

    @Override
    public boolean collidesWith(GameObject other) {

        return (other.getPoint().getY() <= point.getY())
                && (other.getPoint().getY() + other.getDim().getHeight() >=
                point.getY())
                && (other.getPoint().getX() <= point.getX())
                && (other.getPoint().getX() + other.getDim().getWidth() >=
                point.getX());
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void rotate(double degrees) {
        rotate.rotate((float) Math.toRadians(degrees),0,0);
    }

    @Override
    public void scale(double sx, double sy) {
        scale.scale((float) sx, (float) sy);
    }

    @Override
    public void translate(double tx, double ty) {
        translate.translate((float) tx, (float) ty);
    }

    @Override
    public String toString() {
        return "Helipad";
    }

    @Override
    public void draw(Graphics g, Point containerOrigin, Point screenOrigin) {
        g.setColor(color);
        int offX = point.getX() + containerOrigin.getX();
        int offY = point.getY() + containerOrigin.getY();
        g.drawRect(offX ,offY, dim.getWidth(),dim.getHeight());
        g.drawArc(offX,offY,
                dim.getWidth(), dim.getHeight(), 0, 360);
    }
}

