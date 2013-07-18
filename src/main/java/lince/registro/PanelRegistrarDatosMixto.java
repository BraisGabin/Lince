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
package lince.registro;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import lince.modelo.InstrumentoObservacional.NodoInformacion;

/**
 *
 * @author Brais
 */
public class PanelRegistrarDatosMixto extends JPanel {

    private Map<NodoInformacion, JTextField> mixtos;

    public PanelRegistrarDatosMixto(NodoInformacion mixtos[]) {
        this.mixtos = new HashMap<NodoInformacion, JTextField>();
        for (NodoInformacion ni : mixtos) {
            this.mixtos.put(ni, new JTextField());
        }
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(1, 0, 4, 0));

        TitledBorder border = BorderFactory.createTitledBorder(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("DATOS MIXTOS"));
        border.setTitleFont(new Font("Tahoma", Font.BOLD, 11));
        setBorder(border);

        for (NodoInformacion mixto : mixtos.keySet()) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(mixtos.get(mixto), BorderLayout.CENTER);
            panel.add(new Label(mixto.getNombre()), BorderLayout.WEST);

            this.add(panel);
        }
    }

    Map<NodoInformacion, String> getDatosMixtosYValores() {
        Map<NodoInformacion, String> map = new HashMap<NodoInformacion, String>();
        for (NodoInformacion mixto : mixtos.keySet()) {
            String text = mixtos.get(mixto).getText();
            if (!text.equals("")) {
                map.put(mixto, text);
            }
        }
        return map;
    }
}
