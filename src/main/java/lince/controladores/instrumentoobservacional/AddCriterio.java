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

import javax.swing.Action;
import javax.swing.tree.DefaultMutableTreeNode;

import lince.Command;
import lince.modelo.InstrumentoObservacional.InstrumentoObservacional;

/**
 *
 * @author Brais
 */
public class AddCriterio extends Command {

    private DefaultMutableTreeNode parent;

    public AddCriterio(DefaultMutableTreeNode parent) {
        this.parent = parent;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AÑADIR CRITERIO"));
        putValue(Action.ACTION_COMMAND_KEY, "AnadirCriterio");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AÑADE UN NUEVO CRITERIO."));
    }

    @Override
    public void execute() {
        InstrumentoObservacional.getInstance().addHijo(parent, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("NUEVO CRITERIO"));
        //TODO: hacer que cuando creas un nodo su padre aparezca expandido
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
