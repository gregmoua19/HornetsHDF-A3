package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a3.commands.*;
import org.csc133.a3.gameobjects.GameObject;
import org.csc133.a3.views.ControlCluster;
import org.csc133.a3.views.GlassCockpit;
import org.csc133.a3.views.MapView;

import javax.swing.border.Border;

public class Game extends Form implements Runnable{
    private GameWorld gw;
    private GlassCockpit gcp;
    private MapView mv;
    private ControlCluster cc;

    public final static int DISP_W = Display.getInstance().getDisplayWidth();
    public final static int DISP_H = Display.getInstance().getDisplayHeight();

    public Game(){
        gw = new GameWorld();
        gcp = new GlassCockpit(gw);
        mv = new MapView(gw);
        cc = new ControlCluster(gw);

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, gcp);
        this.add(BorderLayout.CENTER, mv);
        this.add(BorderLayout.SOUTH, cc);

        addKeyListener('Q', new ExitCommand(gw));
        addKeyListener( -91, new AccelerateCommand(gw));
        addKeyListener(-92, new BrakeCommand(gw));
        addKeyListener(-93, new TurnLeftCommand(gw));
        addKeyListener(-94, new TurnRightCommand(gw));
        addKeyListener('f', new FightCommand(gw));
        addKeyListener('d', new DrinkCommand(gw));



        UITimer timer = new UITimer(this);
        timer.schedule(200, true, this);
        this.getAllStyles().setBgColor(ColorUtil.BLACK);
        this.show();
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        repaint();
        gcp.update();
        mv.update();
        cc.update();
        gw.tick();
        //mv.update();
    }
}

