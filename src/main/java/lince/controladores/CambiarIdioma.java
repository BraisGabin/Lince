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
package lince.controladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import javax.swing.Action;
import javax.swing.JOptionPane;

import lince.Command;
import lince.datos.ControladorArchivos;
import lince.utiles.IOController;

/**
 *
 * @author Brais
 */
public class CambiarIdioma extends Command {

    private Locale locale;

    public CambiarIdioma(Locale locale) {
        this.locale = locale;
        String name = locale.getDisplayLanguage(locale);
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        putValue(Action.NAME, name);
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CAMBIA EL IDIOMA A ") + locale.getDisplayLanguage());
    }

    @Override
    public void execute() {
        try {
            ControladorArchivos.getInstance().guardarSerializable(new File(IOController.pathAppdata() + "i18n.locale"), locale);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle", locale);
        JOptionPane.showMessageDialog(null, bundle.getString("DIALOG IDIOMA"), bundle.getString("LINCE"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
