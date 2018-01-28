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
package lince;

import lince.datos.ControladorArchivos;
import lince.utiles.IOController;
import org.apache.commons.lang.StringUtils;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsRuntimeUtil;

import javax.swing.*;
import java.io.File;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Brais Gabin Moreira
 */
public class LinceApp {

    private static Logger logger = Logger.getLogger(LinceApp.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        String vlcPath = StringUtils.EMPTY;
        try {
            if (RuntimeUtil.isWindows()) {
                vlcPath = WindowsRuntimeUtil.getVlcInstallDir();
            /*if (StringUtils.isEmpty(vlcPath)){
                vlcPath= "C:\\Program Files (x86)\\VideoLAN";
            }*/
                System.setProperty("jna.library.path", vlcPath);
                setI18n();
            }
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    LinceFrame.getInstance();
                }
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inicializando main. vlcPath@" + vlcPath, e);
        }
    }

    /**
     *
     */
    private static void setI18n() {
        try {
            File file = new File(IOController.pathAppdata() + "i18n.locale");
            Locale locale = (Locale) ControladorArchivos.getInstance().cargarSerializable(file);
            Locale.setDefault(locale);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error Setting I18N for Lince", e);
        }
    }
}
