/*
 *  Lince - Automatizacion de datos observacionales
 *  Copyright (C) 2011  Brais Gabin Moreira
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
package lince.controladores.exportar;

import java.io.File;
import java.util.List;
import javax.swing.Action;
import javax.swing.JOptionPane;

import lince.Command;
import lince.datos.ControladorArchivos;
import lince.modelo.Registro;
import lince.utiles.PathArchivos;
import lince.utiles.SeleccionPanel;

/**
 *
 * @author Brais
 */
public class ExportarRegistroSas extends Command {

    private SeleccionPanel seleccionPanel;

    public ExportarRegistroSas(SeleccionPanel seleccionPanel) {
        this.seleccionPanel = seleccionPanel;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EXPORTAR REGISTRO"));
        putValue(Action.ACTION_COMMAND_KEY, "ExportarRegistroSas");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EXPORTAR REGISTRO"));
    }

    @Override
    public void execute() {
        List<Object> datos = seleccionPanel.getElementosSeleccionados();
        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SELECCIONE AL MENOS UN CRITERIO."), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Registro registro = Registro.getInstance();
        if (registro.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EL REGISTRO ACTUALMENTE ABIERTO NO TIENE NINGUNA FILA."), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        File f = PathArchivos.getPathArchivoGuardar(null, null, null, "sds");

        if (f != null) {
            String contenido = registro.exportToSas(datos);
            ControladorArchivos.getInstance().crearArchivoDeTexto(f, contenido);
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
