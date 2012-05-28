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
package com.braisgabin.lince.controladores.registro;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.registro.PanelBotonera;
import javax.swing.Action;

/**
 *
 * @author Brais
 */
public class CleanPanelRegistro extends Command {

    private PanelBotonera anadirRegistro;

    public CleanPanelRegistro(PanelBotonera anadirRegistro) {
        this.anadirRegistro = anadirRegistro;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LIMPIAR SELECCIÓN"));
        putValue(Action.ACTION_COMMAND_KEY, "LimpiarSeleccion");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LIMPIA LA SELECCION."));
    }

    @Override
    public void execute() {
        anadirRegistro.cleanSelected();
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
