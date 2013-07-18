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
package lince.controladores.registro;

import java.util.Map;
import javax.swing.Action;

import lince.Command;
import lince.modelo.Registro;
import lince.modelo.InstrumentoObservacional.Categoria;
import lince.modelo.InstrumentoObservacional.Criterio;
import lince.modelo.InstrumentoObservacional.NodoInformacion;
import lince.registro.PanelBotonera;
import lince.reproductor.Reproductor;

/**
 *
 * @author Brais
 */
public class AddRegistro extends Command {

    private Reproductor reproductor;
    private PanelBotonera panelBotoneraRegistro;

    public AddRegistro(Reproductor reproductor, PanelBotonera panelBotoneraRegistro) {
        this.reproductor = reproductor;
        this.panelBotoneraRegistro = panelBotoneraRegistro;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AÑADIR REGISTRO"));
        putValue(Action.ACTION_COMMAND_KEY, "AnadirRegistro");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AÑADE UN NUEVO REGISTRO."));
    }

    @Override
    public void execute() {
        Map<Criterio, Categoria> categoriasSeleccionadas = panelBotoneraRegistro.getSelected();
        Map<NodoInformacion, String> datosMixtos = panelBotoneraRegistro.getDatosMixtos();
        int milis = (int) reproductor.getTime();
        Registro.getInstance().addRow(milis, categoriasSeleccionadas, datosMixtos);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
