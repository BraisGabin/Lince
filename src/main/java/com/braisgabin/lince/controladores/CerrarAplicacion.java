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
package com.braisgabin.lince.controladores;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.LinceFrame;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Brais
 */
public class CerrarAplicacion extends Command {

    public CerrarAplicacion() {
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SALIR"));
        putValue(Action.ACTION_COMMAND_KEY, "Salir");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SALIR DE LA APLICACIÓN"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/iconos/x16/exit.png")));
    }

    @Override
    public void execute() {
        NecesarioGuardar necesarioGuardar = NecesarioGuardar.getInstance();
        boolean continuar = necesarioGuardar.saveInstrumentObservacional();
        if (continuar) {
            continuar = necesarioGuardar.saveRegistro();
            if (continuar) {
                LinceFrame.getInstance().cerrar();
            }
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
