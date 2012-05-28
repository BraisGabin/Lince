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
package com.braisgabin.lince.registro;

import com.braisgabin.lince.modelo.Registro;
import com.braisgabin.lince.modelo.instrumentoobservacional.InstrumentoObservacional;
import com.braisgabin.lince.modelo.instrumentoobservacional.NodoInformacion;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brais
 */
public class PanelRegistroDatosFijos extends JPanel {

    private Map<NodoInformacion, JTextField> informacion = new HashMap<NodoInformacion, JTextField>();

    public PanelRegistroDatosFijos() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraintsLabel = new GridBagConstraints();
        GridBagConstraints constraintsText = new GridBagConstraints();
        NodoInformacion nodosInformacion[] = InstrumentoObservacional.getInstance().getDatosFijos();

        constraintsLabel.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsLabel.gridheight = 1;
        constraintsLabel.gridwidth = 1;
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 0;
        constraintsLabel.insets = new Insets(3, 3, 3, 3);
        constraintsLabel.ipadx = 3;
        constraintsLabel.ipady = 5;

        constraintsText.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsText.fill = 1;
        constraintsText.gridheight = 1;
        constraintsText.gridwidth = 1;
        constraintsText.gridx = 1;
        constraintsText.gridy = 0;
        constraintsText.insets = new Insets(3, 3, 3, 3);
        constraintsText.ipadx = 3;
        constraintsText.ipady = 5;
        constraintsText.weightx = 1;

        Registro registro = Registro.getInstance();

        for (NodoInformacion nodoInformacion : nodosInformacion) {
            //TODO bindear valores
            add(new JLabel(nodoInformacion.getNombre()), constraintsLabel);
            constraintsLabel.gridy++;
            JTextField textField = new JTextField(registro.getValorFijo(nodoInformacion));
            informacion.put(nodoInformacion, textField);
            add(textField, constraintsText);
            constraintsText.gridy++;
        }

        setMinimumSize(new Dimension(500, 300));
    }

    public String getValor(NodoInformacion nodoInformacion) {
        return informacion.get(nodoInformacion).getText();
    }
}
