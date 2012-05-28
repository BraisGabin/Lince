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
package com.braisgabin.lince.exportar;

import com.braisgabin.lince.controladores.exportar.ExportarRegistroSdisGseqEstado;
import com.braisgabin.lince.modelo.instrumentoobservacional.InstrumentoObservacional;
import com.braisgabin.lince.utiles.SeleccionPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Brais
 */
public class ExportarSdisGseqEstadoPanel extends JPanel {

    public ExportarSdisGseqEstadoPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        SeleccionPanel seleccionPanel = new SeleccionPanel(InstrumentoObservacional.getInstance().getCriterios(), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("TODOS LOS CRITERIOS"), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CRITERIOS SELECCIONADOS"));

        panelBotones.add(new JButton(new ExportarRegistroSdisGseqEstado(seleccionPanel)));

        add(seleccionPanel, BorderLayout.CENTER);

        add(panelBotones, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(400, 400));
    }
}
