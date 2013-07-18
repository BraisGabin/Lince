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
package lince.registro;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lince.LinceFrame;
import lince.controladores.CloseWindow;
import lince.controladores.Macro;
import lince.controladores.registro.UpdateDatosFijos;

/**
 *
 * @author Brais
 */
public class DialogRegistroDatosFijos extends JDialog {

    private PanelRegistroDatosFijos mainPanel;

    public DialogRegistroDatosFijos() {
        super(LinceFrame.getInstance());
        initComponents();
    }

    private void initComponents() {
        //FIXME que bloquee lo de atras. Esperaar al dispose();
        setTitle(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("DATOS FIJOS"));
        setModalityType(DEFAULT_MODALITY_TYPE);
        this.mainPanel = new PanelRegistroDatosFijos();
        setMinimumSize(mainPanel.getMinimumSize());
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(new JLabel(), BorderLayout.NORTH);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Macro macro = new Macro();
        macro.add(new UpdateDatosFijos(mainPanel), true);
        macro.add(new CloseWindow(this));
        panelBotones.add(new JButton(macro));
        container.add(panelBotones, BorderLayout.SOUTH);
    }
}
