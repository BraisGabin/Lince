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

import javax.swing.JOptionPane;

import lince.LinceFrame;
import lince.controladores.instrumentoobservacional.SaveInstrumentoObservacional;
import lince.controladores.registro.SaveRegistro;
import lince.modelo.Registro;
import lince.modelo.InstrumentoObservacional.InstrumentoObservacional;

/**
 *
 * @author Brais
 */
public class NecesarioGuardar {

    private static NecesarioGuardar instance;

    public synchronized static NecesarioGuardar getInstance() {
        if (instance == null) {
            instance = new NecesarioGuardar();
        }
        return instance;
    }

    private NecesarioGuardar() {
    }

    /**
     *
     * @param titulo
     * @return Devuelve si el usuario desea continuar
     */
    public boolean saveRegistro() {
        boolean continuar;
        Registro registro = Registro.getInstance();
        if (registro.isNecesarySave()) {
            int respuesta = JOptionPane.showConfirmDialog(LinceFrame.getInstance(), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("¿DESEA GUARDAR LOS CAMBIOS DEL REGISTRO ACTUALMENTE ABIERTO? TODO LO QUE NO HAYA SIDO GUARDADO SE PERDERÁ."), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"), JOptionPane.YES_NO_CANCEL_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    (new SaveRegistro()).execute();
                    continuar = !registro.isNecesarySave();
                    break;
                case JOptionPane.NO_OPTION:
                    continuar = true;
                    break;
                default:
                    continuar = false;
            }
        } else {
            continuar = true;
        }
        return continuar;
    }

    /**
     *
     * @param texto
     * @param titulo
     * @return Devuelve si el usuario desea continuar
     */
    public boolean saveInstrumentObservacional() {
        boolean continuar;
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();
        if (instrumentoObservacional.isNecesarySave()) {
            int respuesta = JOptionPane.showConfirmDialog(LinceFrame.getInstance(), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("¿DESEA GUARDAR LOS CAMBIOS DEL INSTRUMENTO OBSERVACIONAL ACTUAL?"), java.util.ResourceBundle.getBundle("i18n.Bundle").getString("LINCE"), JOptionPane.YES_NO_CANCEL_OPTION);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    (new SaveInstrumentoObservacional()).execute();
                    continuar = !instrumentoObservacional.isNecesarySave();
                    break;
                case JOptionPane.NO_OPTION:
                    continuar = true;
                    break;
                default:
                    continuar = false;
            }
        } else {
            continuar = true;
        }
        return continuar;
    }
}
