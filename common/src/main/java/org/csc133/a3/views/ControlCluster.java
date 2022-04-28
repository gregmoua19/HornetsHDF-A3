package org.csc133.a3.views;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.mig.Grid;
import org.csc133.a3.GameWorld;
import org.csc133.a3.commands.*;

import javax.swing.border.Border;

public class ControlCluster extends Container {
    GameWorld gw;
    public ControlCluster(GameWorld gw) {
        this.gw = gw;
        this.getAllStyles().setBgTransparency(255);

        Container left = new Container(new GridLayout(1,3));
        Container middle = new Container(new GridLayout(1,1));
        Container right = new Container(new GridLayout(1,3));

        Button exitButton = new Button("Exit");
        Button accelerateButton = new Button("Accelerate");
        Button brakeButton = new Button("Brake");
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");
        Button fightButton = new Button("Fight");
        Button drinkButton = new Button("Drink");

        exitButton.setCommand(new ExitCommand(gw));
        accelerateButton.setCommand(new AccelerateCommand(gw));
        brakeButton.setCommand(new BrakeCommand(gw));
        leftButton.setCommand(new TurnLeftCommand(gw));
        rightButton.setCommand(new TurnRightCommand(gw));
        fightButton.setCommand(new FightCommand(gw));
        drinkButton.setCommand(new DrinkCommand(gw));

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.EAST, left);
        this.add(BorderLayout.CENTER, middle);
        this.add(BorderLayout.WEST, right);

        left.add(leftButton);
        left.add(rightButton);
        left.add(fightButton);

        middle.add(exitButton);

        right.add(drinkButton);
        right.add(brakeButton);
        right.add(accelerateButton);
    }

    public void update(){
        repaint();
    }
}
