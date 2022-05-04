package org.csc133.a3.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FlightPath extends GameObject{

    @Override
    public void init() {

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
        return null;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin, Point screenOrigin) {

    }

    class BezierCurve {}
}
