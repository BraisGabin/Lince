/*
 * LinceApp - Automatizacion de datos observacionales
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

import com.braisgabin.lince.datos.ControladorArchivos;
import com.braisgabin.lince.utiles.IOController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.LibVlcFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsRuntimeUtil;

/**
 *
 * @author Brais Gabin Moreira
 */
public class LinceApp {

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

        if (RuntimeUtil.isWindows()) {
            System.setProperty("jna.library.path", WindowsRuntimeUtil.getVlcInstallDir());
            setI18n();
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                LinceFrame.getInstance();
            }
        });
    }

    private static void setI18n() {
        try {
            File file = new File(IOController.pathAppdata() + "i18n.locale");
            Locale locale = (Locale) ControladorArchivos.getInstance().cargarSerializable(file);
            Locale.setDefault(locale);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LinceApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
