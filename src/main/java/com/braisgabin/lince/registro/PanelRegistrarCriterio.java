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

import com.braisgabin.lince.modelo.instrumentoobservacional.Categoria;
import com.braisgabin.lince.modelo.instrumentoobservacional.Criterio;
import com.braisgabin.lince.utiles.ButtonGroupNoExclusive;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Brais
 */
public class PanelRegistrarCriterio extends JPanel {

    private Criterio criterio;
    private ButtonGroup buttonGroup;

    public PanelRegistrarCriterio(Criterio criterio) {
        this.criterio = criterio;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(1, 0, 4, 0));

        buttonGroup = new ButtonGroupNoExclusive();

        TitledBorder border = BorderFactory.createTitledBorder(criterio.getNombre());
        border.setTitleFont(new Font("Tahoma", Font.BOLD, 11));
        setBorder(border);

        Categoria categorias[] = criterio.getCategoriasHijo();

        for (Categoria categoria : categorias) {
            addBottons(categoria);
        }
    }

    private void addBottons(Categoria categoriaRaiz) {
        if (categoriaRaiz.isLeaf()) {
            String codigoCategoria = categoriaRaiz.getCodigo();
            JToggleButton boton = new JToggleButton(codigoCategoria);
            boton.setName(codigoCategoria);
            boton.setToolTipText(categoriaRaiz.getNombre());
            boton.setMargin(new Insets(0, 0, 0, 0));
            boton.setBackground(Color.red);
            buttonGroup.add(boton);
            add(boton);
        } else {
            Categoria categorias[] = categoriaRaiz.getCategoriasHijo();
            for (Categoria categoria : categorias) {
                addBottons(categoria);
            }
        }
    }

    public Categoria getSelectedCategoria() {
        Categoria categoria = null;
        Enumeration<AbstractButton> ab = buttonGroup.getElements();
        while (ab.hasMoreElements()) {
            AbstractButton button = ab.nextElement();
            if (button.isSelected()) {
                String codigo = button.getName();
                categoria = criterio.getCategoriaByCodigo(codigo);
                break;
            }
        }
        return categoria;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    void cleanSelected() {
        buttonGroup.clearSelection();
    }

    void seudoCleanSelected() {
        if (!criterio.isPersistente()) {
            buttonGroup.clearSelection();
        }
    }
}
