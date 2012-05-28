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
package com.braisgabin.lince.controladores.reproductor;

import com.braisgabin.lince.reproductor.Reproductor;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Brais
 */
public class StepDown extends Step {

    private Reproductor reproductor;

    public StepDown(Reproductor reproductor, double step) {
        super(step);
        this.reproductor = reproductor;
        putValue(Action.ACTION_COMMAND_KEY, "Atrasar");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("ATRASAR EL VÍDEO"));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x16/player_skip_backward.png")));
    }

    @Override
    public void execute() {
        reproductor.skip(-Math.round(getStep() * 1000));
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported");
    }
}
