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
package lince.controladores.instrumentoobservacional;

import java.io.File;
import javax.swing.Action;
import javax.swing.ImageIcon;

import lince.Command;
import lince.modelo.InstrumentoObservacional.InstrumentoObservacional;
import lince.utiles.PathArchivos;

/**
 *
 * @author Brais
 */
public class SaveInstrumentoObservacional extends Command {

    public SaveInstrumentoObservacional() {
        initAction();
    }

    private void initAction() {
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("GUARDAR INSTR. OBSERVACIONAL"));
        putValue(Action.ACTION_COMMAND_KEY, "GuardarInstrumentObservacional");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("GUARDA UN INSTRUMENTO OBSERVACIONAL"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/iconos/x16/filesave.png")));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x32/filesave.png")));
    }

    @Override
    public void execute() {
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();

        File path = instrumentoObservacional.getPath();
        if (path == null) {
            // TODO: agregar filtros
            path = PathArchivos.getPathArchivoGuardar(null, null, null, "ilince");
            if (path != null) {
                instrumentoObservacional.setPath(path);
            } else {
                return;
            }
        }

        instrumentoObservacional.save();
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
