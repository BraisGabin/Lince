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
import com.braisgabin.lince.modelo.Registro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.JTable;

/**
 *
 * @author Brais
 */
public class RemoveRegistro extends Command {

    private JTable tablaDeRegistros;

    public RemoveRegistro(JTable tablaDeRegistros) {
        this.tablaDeRegistros = tablaDeRegistros;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("ELIMINAR REGISTRO"));
        putValue(Action.ACTION_COMMAND_KEY, "EliminarRegistro");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("ELIMINA LOS REGISTROS SELECCIONADOS."));
    }

    @Override
    public void execute() {
        int filas[] = tablaDeRegistros.getSelectedRows();
        List<Integer> fil = new ArrayList<Integer>();
        for (int fila : filas) {
            fil.add(fila);
        }
        Collections.sort(fil, Collections.reverseOrder());
        for (Integer indice : fil) {
            Registro.getInstance().removeRow(indice);
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
