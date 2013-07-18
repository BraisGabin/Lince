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

import java.util.Observable;
import java.util.Observer;
import javax.swing.Action;

import lince.Command;
import lince.reproductor.Reproductor;

/**
 *
 * @author Brais
 */
public class JumpTo extends Command implements Observer {

    private Reproductor reproductor;
    private long time;

    public JumpTo(Reproductor reproductor) {
        this.reproductor = reproductor;
        putValue(Action.ACTION_COMMAND_KEY, "SaltarA");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SALTA A UN MOMENTO DETERMINADO DEL VÍDEO."));
    }

    @Override
    public void execute() {
        reproductor.setTime(time);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void update(Observable o, Object time) {
        this.time = (Long) time;
        execute();
    }
}
