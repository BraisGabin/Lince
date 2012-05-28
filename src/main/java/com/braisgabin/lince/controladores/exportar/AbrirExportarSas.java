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
package com.braisgabin.lince.controladores.exportar;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.LinceFrame;
import com.braisgabin.lince.exportar.ExportarSasPanel;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Brais
 */
public class AbrirExportarSas extends Command {

    public AbrirExportarSas() {
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SAS"));
        putValue(Action.ACTION_COMMAND_KEY, "AbrirExportarSas");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EXPORTAR A SAS"));
    }

    @Override
    public void execute() {
        JDialog dialogExportarTheme = new JDialog(LinceFrame.getInstance(), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EXPORTAR SAS"), JDialog.DEFAULT_MODALITY_TYPE);

        JPanel mainPanel = new ExportarSasPanel();

        dialogExportarTheme.setMinimumSize(mainPanel.getMinimumSize());
        dialogExportarTheme.setMaximumSize(mainPanel.getMaximumSize());
        dialogExportarTheme.setPreferredSize(mainPanel.getPreferredSize());

        dialogExportarTheme.setContentPane(mainPanel);

        dialogExportarTheme.setVisible(true);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

