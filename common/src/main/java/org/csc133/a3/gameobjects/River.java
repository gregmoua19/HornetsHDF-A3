package org.csc133.a3.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a3.Game;


public class River extends Fixed{

    Transform scale, rotate, translate;

    public River(){
        init();
    }

    public void init() {
        scale = Transform.makeIdentity();
        rotate = Transform.makeIdentity();
        translate = Transform.makeIdentity();
        dim = new Dimension(Game.DISP_W,Game.DISP_W/10);
        this.color = ColorUtil.BLUE;
        point = new Point(0,
                5 * Game.DISP_H / 7);

    }

    @Override
    public boolean collidesWith(GameObject other) {
        return false;
    }

    @Override
    public int getSize() {
        return this.getDim().getHeight();
    }


    public void rotate(double degrees) {
        rotate.rotate((float) Math.toRadians(degrees),0,0);
    }

    public void scale(double sx, double sy) {
        scale.scale((float) sx, (float) sy);
    }

    public void translate(double tx, double ty) {
        translate.translate((float) tx, (float) ty);
    }



    @Override
    public String toString() {
        return "River";
    }

    @Override
    public void draw(Graphics g, Point containerOrigin, Point screenOrigin) {

        Transform heliTrans = Transform.makeIdentity();
        g.getTransform(heliTrans);
        Transform heliTransOrig = heliTrans.copy();

        heliTrans.translate(screenOrigin.getX(),screenOrigin.getY());

        heliTrans.translate(translate.getTranslateX(),
                translate.getTranslateY());

        heliTrans.concatenate(rotate);
        heliTrans.scale(scale.getScaleX(), scale.getScaleY());

        heliTrans.translate(-screenOrigin.getX(),-screenOrigin.getY());
        g.setTransform(heliTrans);


        //.out.println("River container origin X: "
        //+ containerOrigin.getX());
        //System.out.println("River container origin Y: "
        //+ containerOrigin.getY());
        g.setColor(color);
        int offX = point.getX() + containerOrigin.getX();
        int offY = point.getY() - containerOrigin.getY();
        g.drawRect(offX, offY, Game.DISP_W,Game.DISP_H / 10);

        g.setTransform(heliTransOrig);

    }
}
