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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import lince.modelo.InstrumentoObservacional.Categoria;
import lince.modelo.InstrumentoObservacional.Criterio;
import lince.utiles.ButtonGroupNoExclusive;

/**
 *
 * @author Brais
 */
public class PanelRegistrarCriterio extends JPanel /*implements MouseListener */{

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
            /*
            boton.setBackground(Color.red);
            boton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    JToggleButton elem = ((JToggleButton) evt.getSource());
                    elem.setBackground(Color.BLACK);
                    elem.revalidate();
                    //this.setBackground(Color.GREEN);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    JToggleButton elem = ((JToggleButton) evt.getSource());
                    elem.setBackground(UIManager.getColor("control"));
                    elem.revalidate();
                    //jButton1.setBackground(UIManager.getColor("control"));
                }
            });*/
            //TODO
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
/*
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JToggleButton button = ((JToggleButton) e.getSource());
        Point buttonLocation = button.getLocationOnScreen();
        Point cursor = e.getLocationOnScreen();
        double west = buttonLocation.getX();
        double east = buttonLocation.getX() + button.getWidth();
        double north = buttonLocation.getY();
        double south = buttonLocation.getY() + button.getHeight();
        boolean inRow = cursor.getX() > west && cursor.getX() < east;
        boolean inCol = cursor.getY() > north && cursor.getY() < south;
        boolean inBounds = inRow || inCol;
        button.setBackground(inBounds ? new Color(0xFFFF00) : null);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    */
}
