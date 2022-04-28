package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a3.Game;


public class River extends Fixed{

    public River(){
        init();
    }

    public void init() {
        dim = new Dimension(Game.DISP_W,Game.DISP_W/10);
        this.color = ColorUtil.BLUE;
        point = new Point(0,
                Game.DISP_H / 5);

    }

    @Override
    public boolean collidesWith(GameObject other) {
        return false;
    }

    @Override
    public int getSize() {
        return this.getDim().getHeight();
    }

    @Override
    public String toString() {
        return "River";
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        //.out.println("River container origin X: "
        //+ containerOrigin.getX());
        //System.out.println("River container origin Y: "
        //+ containerOrigin.getY());
        g.setColor(color);
        int offX = point.getX() + containerOrigin.getX();
        int offY = point.getY() + containerOrigin.getY();
        g.drawRect(offX, offY, Game.DISP_W,Game.DISP_H / 10);
    }
}
