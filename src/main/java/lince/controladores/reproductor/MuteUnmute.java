/*
 *  Lince - Automatizacion de datos observacionales
 *  Copyright (C) 2010  Brais Gabin Moreira
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lince.controladores.reproductor;

import javax.swing.Action;
import javax.swing.ImageIcon;

import lince.Command;
import lince.reproductor.Reproductor;

/**
 *
 * @author Brais
 */
public class MuteUnmute extends Command {

    private Reproductor reproductor;
    private ImageIcon soundIcon;
    private ImageIcon muteIcon;

    public MuteUnmute(Reproductor reproductor) {
        soundIcon = new ImageIcon(getClass().getResource("/iconos/x32/audio-volume-medium.png"));
        muteIcon = new ImageIcon(getClass().getResource("/iconos/x32/audio-volume-muted.png"));
        this.reproductor = reproductor;
        putValue(Action.ACTION_COMMAND_KEY, "MuteUnmute");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SILENCIAR"));
        putValue(Action.LARGE_ICON_KEY, soundIcon);
    }

    @Override
    public void execute() {
        if (reproductor.isMute()) {
            putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("DESILENCIAR"));
            putValue(Action.LARGE_ICON_KEY, soundIcon);
        } else {
            putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SILENCIAR"));
            putValue(Action.LARGE_ICON_KEY, muteIcon);
        }
        reproductor.mute();
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported");
    }
}
