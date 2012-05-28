/*
 * Lince - Automatizacion de datos observacionales
 * Copyright (C) 2010  Brais Gabin Moreira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * valong with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.braisgabin.lince;

import com.braisgabin.lince.instrumentoobservacional.MainPanelInstrumentoObservacional;
import com.braisgabin.lince.registro.MainPanelRegistro;
import com.braisgabin.lince.reproductor.Reproductor;
import javax.swing.JTabbedPane;

/**
 *
 * @author Brais
 */
public class LinceMainPanel extends JTabbedPane {

    private MainPanelInstrumentoObservacional panelInstrumentoObservacional;
    private MainPanelRegistro registro;

    public LinceMainPanel(Reproductor reproductor) {
        initComponents(reproductor);
    }

    private void initComponents(Reproductor reproductor) {
        panelInstrumentoObservacional = new MainPanelInstrumentoObservacional();
        registro = new MainPanelRegistro(reproductor);

        addTab(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("INSTRUMENTO OBSERVACIONAL"), panelInstrumentoObservacional);
        addTab(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REGISTRO"), registro);
    }

    public void release() {
        registro.release();
    }
}
