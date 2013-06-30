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
package com.braisgabin.lince.controladores.importar;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.modelo.instrumentoobservacional.*;
import com.braisgabin.lince.utiles.FiltroArchivos;
import com.braisgabin.lince.utiles.PathArchivos;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Brais
 */
public class AbrirImportarHoisan extends Command {

    public AbrirImportarHoisan() {
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("HOISAN"));
        putValue(Action.ACTION_COMMAND_KEY, "AbrirImportarHoisan");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("IMPORATAR DESDE HOISAN"));
    }

    @Override
    public void execute() {
        List<FileFilter> fileFilters = new ArrayList<FileFilter>();
        fileFilters.add(new FiltroArchivos("mdb", java.util.ResourceBundle.getBundle("i18n.Bundle").getString("HOISAN")));
        File f = PathArchivos.getPathArchivoAbrir(fileFilters, null, null);
        if (f != null) {
            try {
                Database db = Database.open(f);

                Table criterios = db.getTable("Criterios");
                Table categorias = db.getTable("Categorias");
                InstrumentoObservacional instrumentoObservacional = generateInstrumentObservacional(criterios, categorias);


            } catch (IOException ex) {
                Logger.getLogger(AbrirImportarHoisan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private InstrumentoObservacional generateInstrumentObservacional(Table criterios, Table categorias) {
        InstrumentoObservacional.loadNewInstance();
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();
        RootInstrumentoObservacional root = (RootInstrumentoObservacional) instrumentoObservacional.getModel().getRoot();
        DefaultMutableTreeNode fijo = (DefaultMutableTreeNode) root.getChildAt(0);
        DefaultMutableTreeNode variable = (DefaultMutableTreeNode) root.getChildAt(2);

        for (Map<String, Object> row : criterios) {
            String type = (String) row.get("Tipo");
            if (type.equals("Fijo")) {
                InstrumentoObservacional.getInstance().addHijo(fijo, (String) row.get("Nombre_Criterio"));

                NodoInformacion[] datos = instrumentoObservacional.getDatosFijos();
                datos[datos.length - 1].setDescripcion((String) row.get("Descripcion"));
            } else if (type.equals("Normal")) {
                InstrumentoObservacional.getInstance().addHijo(variable, (String) row.get("Nombre_Criterio"));

                Criterio[] cs = instrumentoObservacional.getCriterios();
                Criterio criterio = cs[cs.length - 1];
                criterio.setDescripcion((String) row.get("Descripcion"));

                generateCategorias(criterio, (Integer) row.get("ID_Criterio"), categorias);
            } else {
                // Ignore
            }
        }
        return null;
    }

    private void generateCategorias(Criterio criterio, Integer creterioId, Table categorias) {
        for (Map<String, Object> row : categorias) {
            if (row.get("ID_Criterio_Referencial").equals(creterioId)) {
                InstrumentoObservacional.getInstance().addHijo(criterio, (String) row.get("Descripcion"));
                
                Categoria categoria = (Categoria) criterio.getChildAt(criterio.getChildCount() - 1);
                categoria.setCodigo((String) row.get("Nombre_Categoria"));
                categoria.setDescripcion((String) row.get("Nucleo_Categorial"));
            }
        }
    }
}
