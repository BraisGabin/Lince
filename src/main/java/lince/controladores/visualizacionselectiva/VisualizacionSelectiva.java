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
package lince.controladores.visualizacionselectiva;

import java.util.Map;
import javax.swing.Action;
import javax.swing.JTable;

import lince.Command;
import lince.modelo.ModeloDeTablaLince;
import lince.modelo.Registro;
import lince.modelo.InstrumentoObservacional.Categoria;
import lince.modelo.InstrumentoObservacional.Criterio;
import lince.registro.PanelBotonera;

/**
 *
 * @author Brais
 */
public class VisualizacionSelectiva extends Command {

    private JTable tablaDeRegistros;
    private PanelBotonera panelBotoneraRegistro;

    public VisualizacionSelectiva(JTable tablaDeRegistros, PanelBotonera panelBotoneraRegistro) {
        this.tablaDeRegistros = tablaDeRegistros;
        this.panelBotoneraRegistro = panelBotoneraRegistro;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("BUSCAR"));
        putValue(Action.ACTION_COMMAND_KEY, "Buscar");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("BUSCA COINCIDENCIAS EN EL REGISTRO."));
    }

    @Override
    public void execute() {
        Map<Criterio, Categoria> seleccion = panelBotoneraRegistro.getSelected();
        tablaDeRegistros.setModel(new ModeloDeTablaLince(Registro.getInstance().getFilasQueConcuerdan(seleccion)));
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
