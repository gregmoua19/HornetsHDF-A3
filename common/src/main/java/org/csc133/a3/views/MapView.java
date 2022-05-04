package org.csc133.a3.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Transform;
import org.csc133.a3.GameWorld;
import org.csc133.a3.gameobjects.GameObject;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class MapView extends Container {
    private GameWorld gw;
    public MapView(GameWorld gw) {
        this.gw = gw;
    }

    public void paint(Graphics g) {
        super.paint(g);

        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);

        gXform.translate(getAbsoluteX(), getAbsoluteY());
        gXform.translate(0,getHeight());
        gXform.scale(1,-1);
        gXform.translate(-getAbsoluteX(),-getAbsoluteY());
        g.setTransform(gXform);
        Point containerOrigin = new Point(this.getX(), this.getY());
        Point screenOrigin = new Point (getAbsoluteX(), getAbsoluteY());

        for(GameObject go : gw.getGameObjectCollection()) {
           go.draw(g, containerOrigin, screenOrigin);
        }

        g.resetAffine();
        update();
    }

    public void update() {
        //put helicopter update here
        repaint();
    }

}
