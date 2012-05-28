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

import com.braisgabin.lince.Command;
import com.braisgabin.lince.reproductor.Reproductor;

/**
 *
 * @author Brais
 */
public class ChangePosition extends Command {

    public static final int MAXVALOR = 250;
    private Reproductor reproductor;
    private float position;

    public ChangePosition(Reproductor reproductor) {
        this.reproductor = reproductor;
    }

    @Override
    public void execute() {
        reproductor.setPosition(position / MAXVALOR);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
