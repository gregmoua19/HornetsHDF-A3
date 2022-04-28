package org.csc133.a3.views;

import com.codename1.ui.Container;
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
        for(GameObject go : gw.getGameObjectCollection()) {
           go.draw(g, new Point(this.getX(), this.getY()));
        }
        update();
    }

    public void update() {
        repaint();
    }
}
