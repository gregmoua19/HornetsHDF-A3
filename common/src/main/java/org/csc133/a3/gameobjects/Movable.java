package org.csc133.a3.gameobjects;

import org.csc133.a3.interfaces.Steerable;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
public abstract class Movable extends GameObject implements Steerable {

    public int speed;
    public int heading;

    @Override
    public abstract void init();

    @Override
    public abstract boolean collidesWith(GameObject other);


    public abstract Dimension getSize(Dimension d);

    @Override
    public abstract void draw(Graphics g, Point containerOrigin);

    @Override
    public abstract void steerLeft();

    @Override
    public abstract void steerRight();
}
