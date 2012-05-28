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
package com.braisgabin.lince.controladores.instrumentoobservacional;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.modelo.instrumentoobservacional.InstrumentoObservacional;
import com.braisgabin.lince.utiles.PathArchivos;
import java.io.File;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Brais
 */
public class SaveInstrumentoObservacionalAs extends Command {

    //private InstrumentoObservacional instrumentoObservacional = null;
    public SaveInstrumentoObservacionalAs() {
        initAction();
    }

    private void initAction() {
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("GUARDAR INSTR. OBSERVACIONAL COMO..."));
        putValue(Action.ACTION_COMMAND_KEY, "GuardarInstrumentObservacionalComo");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("GUARDA UN INSTRUMENTO OBSERVACIONAL COMO..."));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/iconos/x16/filesaveas.png")));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x32/filesaveas.png")));
    }

    @Override
    public void execute() {
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();

        File path = PathArchivos.getPathArchivoGuardar(null, instrumentoObservacional.getPath(), null, "ilince");
        if (path != null) {
            instrumentoObservacional.setPath(path);
            instrumentoObservacional.save();
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
