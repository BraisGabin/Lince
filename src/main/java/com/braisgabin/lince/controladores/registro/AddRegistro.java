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
import com.braisgabin.lince.modelo.instrumentoobservacional.Categoria;
import com.braisgabin.lince.modelo.instrumentoobservacional.Criterio;
import com.braisgabin.lince.modelo.instrumentoobservacional.NodoInformacion;
import com.braisgabin.lince.registro.PanelBotonera;
import com.braisgabin.lince.reproductor.Reproductor;
import java.util.Map;
import javax.swing.Action;

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
