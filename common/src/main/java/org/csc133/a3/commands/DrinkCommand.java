package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.GameWorld;

public class DrinkCommand extends Command {
    GameWorld gw;
    public DrinkCommand(GameWorld gw) {
        super("Drink");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if(gw.getHelicopter().collidesWith(
                gw.getGameObjectCollection().get(5))) {
            gw.getHelicopter().drink();
        }
    }
}
