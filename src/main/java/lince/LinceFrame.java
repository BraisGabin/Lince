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
package lince;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import lince.controladores.CerrarAplicacion;
import lince.reproductor.Reproductor;

/**
 *
 * @author Brais Gabin Moreira
 */
public class LinceFrame extends JFrame {

    public static LinceFrame getInstance() {
        if (instance == null) {
            instance = new LinceFrame();
        }
        return instance;
    }
    private static LinceFrame instance = null;
    private LinceMenuBar linceMenuBar;
    private LinceMainPanel linceMainPanel;

    private LinceFrame() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"));
        setName(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"));
        setIconImage(new ImageIcon(getClass().getResource("/iconos/imagenes/icono.png")).getImage());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapterLince());

        setMinimumSize(new Dimension(800, 600));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Reproductor reproductor = new Reproductor(this);
        linceMainPanel = new LinceMainPanel(reproductor);
        linceMenuBar = new LinceMenuBar(reproductor);
//        linceStatusBar = new LinceStatusBar();

        setJMenuBar(linceMenuBar);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(linceMainPanel, BorderLayout.CENTER);
//        contentPane.add(linceStatusBar, BorderLayout.SOUTH);
    }

    public void cerrar() {
        linceMainPanel.release();
        Runtime.getRuntime().exit(0);
    }

    private void setLookAndFeel(String lookAndFeelClassName) {
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class WindowAdapterLince extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            (new CerrarAplicacion()).execute();
        }
    }

    public static Locale[] getLocales() {
        return new Locale[]{
                    new Locale("es"),
                    new Locale("en")
                };
    }
}
