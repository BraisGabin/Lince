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
package lince.controladores.exportar;

import lince.Command;
import lince.LinceFrame;
import lince.exportar.ExportarCsvPanel;
import lince.utiles.ResourceBundleHelper;

import javax.swing.*;

/**
 * @author Brais
 */
public class AbrirExportarHoisan extends Command {

    public static final String EXPORT_HOISAN_COMMAND_ID = "AbrirExportarHoisan";

    public AbrirExportarHoisan() {
        putValue(Action.NAME, ResourceBundleHelper.getI18NLabel("HOISAN"));
        putValue(Action.ACTION_COMMAND_KEY, EXPORT_HOISAN_COMMAND_ID);
        putValue(Action.SHORT_DESCRIPTION, ResourceBundleHelper.getI18NLabel("actions.export.Hoisan"));
    }

    @Override
    public void execute() {
        JDialog dialog = new JDialog(LinceFrame.getInstance(), ResourceBundleHelper.getI18NLabel("EXPORTAR EXCEL"), JDialog.DEFAULT_MODALITY_TYPE);
        JPanel mainPanel = new ExportarCsvPanel();
        dialog.setMinimumSize(mainPanel.getMinimumSize());
        dialog.setMaximumSize(mainPanel.getMaximumSize());
        dialog.setPreferredSize(mainPanel.getPreferredSize());
        dialog.setContentPane(mainPanel);
        dialog.setVisible(true);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
