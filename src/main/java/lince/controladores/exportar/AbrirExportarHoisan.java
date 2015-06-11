
/*
 *  Lince - Automatizacion de datos observacionales
 *  Copyright (C) 2105 Alberto Soto Fernández
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
import lince.plugins.HoisanTool;
import lince.utiles.FiltroArchivos;
import lince.utiles.ResourceBundleHelper;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;

/**
 * @author Alberto Soto
 */
public class AbrirExportarHoisan extends Command {

    public static final String EXPORT_HOISAN_COMMAND_ID = "AbrirExportarHoisan";
    private Logger log = Logger.getLogger(AbrirExportarHoisan.class.getName());

    private JFileChooser fc;
    public AbrirExportarHoisan() {
        putValue(Action.NAME, ResourceBundleHelper.getI18NLabel("HOISAN"));
        putValue(Action.ACTION_COMMAND_KEY, EXPORT_HOISAN_COMMAND_ID);
        putValue(Action.SHORT_DESCRIPTION, ResourceBundleHelper.getI18NLabel("actions.export.Hoisan"));
    }

    @Override
    public void execute() {
        if (fc == null) {
            fc = new JFileChooser();
            //Add a custom file filter and disable the default
            fc.addChoosableFileFilter(new FiltroArchivos("mdb",ResourceBundleHelper.getI18NLabel("HOISAN")));
            fc.setAcceptAllFileFilterUsed(false);
        }
        //Show it.
        int returnVal = fc.showDialog(LinceFrame.getInstance(),"Attach");
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            log.warn("Init export to Hoisan file: " + file.getName() + ".");
            HoisanTool hoisan = new HoisanTool();
            hoisan.exportFile(file);
        } else {
            log.warn("Hoisan export cancelled by user.");
        }
        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
