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

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Brais
 */
public class LinceStatusBar extends JPanel {


    public LinceStatusBar() {
        initComponents();
    }

    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(0, 25));
        setMinimumSize(new java.awt.Dimension(0, 25));
        setMaximumSize(new java.awt.Dimension(0, 25));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(160, 160, 160)),
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(255, 255, 255))));
    }
}
