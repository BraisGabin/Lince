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
package lince.instrumentoobservacional;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import lince.controladores.instrumentoobservacional.*;
import lince.modelo.InstrumentoObservacional.*;

/**
 *
 * @author Brais
 */
public class MainPanelInstrumentoObservacional extends JPanel implements TreeSelectionListener, Observer {

    private JTree jTree;
    private JPanel panelDeTrabajo;
    private Component componenteActual = null;
    private JPanel panelDeBotones;

    public MainPanelInstrumentoObservacional() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        jTree = new JTree(InstrumentoObservacional.getInstance().getModel());
        jTree.addTreeSelectionListener(this);

        add(new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.WEST);

        panelDeBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelDeTrabajo = new JPanel(new BorderLayout());
        panelDeTrabajo.add(panelDeBotones, BorderLayout.NORTH);

        add(panelDeTrabajo, BorderLayout.CENTER);

        InstrumentoObservacional.addObservador(this);
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();

        if (componenteActual != null) {
            panelDeTrabajo.remove(componenteActual);
            componenteActual = null;
        }

        panelDeBotones.removeAll();

        if (node == null) {
            return;
        }

        if (node instanceof Criterio) {
            componenteActual = new PanelCriterio((Criterio) node);

            panelDeBotones.add(new JButton(new AddCategoria(node)));
            panelDeBotones.add(new JButton(new RemoveCriterio(node)));
            panelDeBotones.add(new JButton(new MoveRuleAction(node,jTree,true)));
            panelDeBotones.add(new JButton(new MoveRuleAction(node,jTree,false)));
        } else if (node instanceof Categoria) {
            componenteActual = new PanelCategoria((Categoria) node);

            panelDeBotones.add(new JButton(new AddCategoria(node)));
            panelDeBotones.add(new JButton(new RemoveCategoria(node)));
            panelDeBotones.add(new JButton(new MoveRuleAction(node,jTree,true)));
            panelDeBotones.add(new JButton(new MoveRuleAction(node,jTree,false)));
        } else if (node instanceof RootInstrumentoObservacional) {
            componenteActual = new PanelInstrumentoObservacional((RootInstrumentoObservacional) node);
        } else if (node instanceof NodoInformacion) {
            componenteActual = new PanelNodoInformacion((NodoInformacion) node);

            panelDeBotones.add(new JButton(new RemoveCriterio(node)));
        } else {
            panelDeBotones.add(new JButton(new AddCriterio(node)));
        }

        if (componenteActual != null) {
            panelDeTrabajo.add(componenteActual, BorderLayout.CENTER);
        }
        //jTree = new JTree(InstrumentoObservacional.getInstance().getModel());
        repaint();
        validate();
    }

    public void update(Observable o, Object arg) {
        if (arg != null) {
            jTree.setModel(InstrumentoObservacional.getInstance().getModel());
        }
    }
}
