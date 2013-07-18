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

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lince.controladores.Macro;
import lince.controladores.registro.*;
import lince.controladores.reproductor.JumpTo;
import lince.controladores.reproductor.Play;
import lince.controladores.visualizacionselectiva.VisualizacionSelectiva;
import lince.modelo.ModeloDeTablaLince;
import lince.modelo.Registro;
import lince.reproductor.Reproductor;
import lince.utiles.MyObservable;

/**
 *
 * @author Brais
 */
public class MainPanelRegistro extends JPanel implements ChangeListener {

    private Reproductor reproductor;
    private PanelBotonera panelBotoneraRegistro;
    private PanelBotonera panelBotoneraVisualizacionSelectiva;
    private JPanel botonesRegistro;
    private JPanel botonesVisualizacionSelectiva;
    private JTable tablaDeRegistros;
    private Observable observable = new MyObservable();
    private Macro guardarRegistro;

    public MainPanelRegistro(Reproductor reproductor) {
        this.reproductor = reproductor;
        initComponents();
    }

    private void initComponents() {
        long eventMask = AWTEvent.MOUSE_EVENT_MASK;
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

            public void eventDispatched(AWTEvent e) {
                if (e.getID() == 500) { //Es un click
                    String parametros = e.paramString();
                    if (parametros.contains("button=3")) {
                        reproductor.playPause();
                    } else if (parametros.contains("button=2")) {
                        guardarRegistro.execute();
                    }
                }
            }
        }, eventMask);

        setLayout(new BorderLayout());

        JSplitPane splitPanePrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane splitPaneSecundario = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        initTable();
        splitPaneSecundario.setTopComponent(reproductor);
        JScrollPane j = new JScrollPane(tablaDeRegistros);
        tablaDeRegistros.setAutoscrolls(true);

        splitPaneSecundario.setBottomComponent(j);

        splitPanePrincipal.setLeftComponent(splitPaneSecundario);
        JTabbedPane panelDerecha = new JTabbedPane();

        panelDerecha.addChangeListener(this);

        JPanel panelDerechaRegistro = new JPanel(new BorderLayout());
        panelBotoneraRegistro = new PanelBotonera();
        panelDerechaRegistro.add(new JScrollPane(panelBotoneraRegistro), BorderLayout.CENTER);
        initBotonesRegistro();
        panelDerechaRegistro.add(botonesRegistro, BorderLayout.SOUTH);
        panelDerecha.addTab(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REGISTRO"), panelDerechaRegistro);

        JPanel panelDerechaVisualizacionSelectiva = new JPanel(new BorderLayout());
        panelBotoneraVisualizacionSelectiva = new PanelBotonera(false);
        panelDerechaVisualizacionSelectiva.add(panelBotoneraVisualizacionSelectiva, BorderLayout.CENTER);
        initBotonesVisualizacionSelectiva();
        panelDerechaVisualizacionSelectiva.add(botonesVisualizacionSelectiva, BorderLayout.SOUTH);
        panelDerecha.addTab(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("VISUALIZACION SELECTIVA"), panelDerechaVisualizacionSelectiva);

        splitPanePrincipal.setRightComponent(panelDerecha);

        add(splitPanePrincipal, BorderLayout.CENTER);

        splitPanePrincipal.setDividerLocation(750);
        splitPaneSecundario.setDividerLocation(550);
    }

    public void release() {
        reproductor.release();
    }

    private void initTable() {
        tablaDeRegistros = new JTable(Registro.getInstance());

        observable.addObserver(new JumpTo(reproductor));

        tablaDeRegistros.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (tablaDeRegistros.getSelectedColumn() <= 1) {
                        observable.notifyObservers(((ModeloDeTablaLince) tablaDeRegistros.getModel()).getMilisOfRow(tablaDeRegistros.getSelectedRow()));
                    }
                }
            }
        });

        /* TODO Conseguir que el tiempo donde se toma un registro este siempre
         * alineado a la derecha.
         */
    }

    private void initBotonesRegistro() {
        botonesRegistro = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        botonesRegistro.add(new JButton(new AbrirEditorDatosFijos()));
        botonesRegistro.add(new JButton(new RemoveRegistro(tablaDeRegistros)));
        botonesRegistro.add(new JButton(new CleanPanelRegistro(panelBotoneraRegistro)));

        guardarRegistro = new Macro();
        guardarRegistro.add(new AddRegistro(reproductor, panelBotoneraRegistro), true);
        guardarRegistro.add(new SeudoCleanPanelRegistro(panelBotoneraRegistro));
        guardarRegistro.add(new Play(reproductor));
        botonesRegistro.add(new JButton(guardarRegistro));
    }

    public void stateChanged(ChangeEvent e) {
        int index = ((javax.swing.JTabbedPane) e.getSource()).getSelectedIndex();
        if (index == 0) { // estamos en registro
            tablaDeRegistros.setModel(Registro.getInstance());
        }
    }

    private void initBotonesVisualizacionSelectiva() {
        botonesVisualizacionSelectiva = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        botonesVisualizacionSelectiva.add(new JButton(new CleanPanelRegistro(panelBotoneraVisualizacionSelectiva)));
        botonesVisualizacionSelectiva.add(new JButton(new VisualizacionSelectiva(tablaDeRegistros, panelBotoneraVisualizacionSelectiva)));
    }
}
