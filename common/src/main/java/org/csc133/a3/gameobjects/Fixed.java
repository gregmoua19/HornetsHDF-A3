package org.csc133.a3.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public abstract class Fixed extends GameObject{

    @Override
    public abstract void init();

    @Override
    public abstract boolean collidesWith(GameObject other);

    @Override
    public abstract void draw(Graphics g, Point containerOrigin, Point screenOrigin);

}
