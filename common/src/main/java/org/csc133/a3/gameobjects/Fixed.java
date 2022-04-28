package org.csc133.a3.gameobjects;

import org.csc133.a3.interfaces.Drawable;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Dimension;


public abstract class Fixed extends GameObject{

    @Override
    public abstract void init();

    @Override
    public abstract boolean collidesWith(GameObject other);

    @Override
    public abstract void draw(Graphics g, Point containerOrigin);

}
