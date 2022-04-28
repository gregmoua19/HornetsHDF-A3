package org.csc133.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.GameWorld;

public class AccelerateCommand extends Command {
    private GameWorld gw;
    public AccelerateCommand(GameWorld gw) {
        super("Accelerate");
        this.gw = gw;

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
            gw.getHelicopter().speedUp(true);
    }
}
