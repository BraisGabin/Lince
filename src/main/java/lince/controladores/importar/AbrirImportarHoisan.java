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
package lince.controladores.importar;

import lince.Command;
import lince.plugins.HoisanTool;
import lince.utiles.FiltroArchivos;
import lince.utiles.PathArchivos;
import lince.utiles.ResourceBundleHelper;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brais
 */
public class AbrirImportarHoisan extends Command {
    public static final String IMPORT_HOISAN_COMMAND_ID = "AbrirImportarHoisan";
    public AbrirImportarHoisan() {
        putValue(Action.NAME, ResourceBundleHelper.getI18NLabel("HOISAN"));
        putValue(Action.ACTION_COMMAND_KEY, IMPORT_HOISAN_COMMAND_ID);
        putValue(Action.SHORT_DESCRIPTION, ResourceBundleHelper.getI18NLabel("actions.import.Hoisan"));
    }

    @Override
    public void execute() {
        List<FileFilter> fileFilters = new ArrayList<FileFilter>();
        fileFilters.add(new FiltroArchivos("mdb", ResourceBundleHelper.getI18NLabel("HOISAN")));
        File f = PathArchivos.getPathArchivoAbrir(fileFilters, null, null);
        HoisanTool hoisanConnector = new HoisanTool();
        hoisanConnector.importFile(f);
    }


    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
