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
package lince.controladores.reproductor;

import java.util.Observable;
import java.util.Observer;
import javax.swing.Action;
import javax.swing.ImageIcon;

import lince.Command;
import lince.reproductor.Reproductor;
import lince.utiles.MyObservable;

/**
 *
 * @author Brais
 */
public class Slower extends Command {

    private Reproductor reproductor;
    private static Observable observable = new MyObservable();

    public Slower(Reproductor reproductor) {
        this.reproductor = reproductor;
        //putValue(Action.NAME, "Más lento");
        putValue(Action.ACTION_COMMAND_KEY, "MasLento");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("MÁS LENTO"));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x32/player_rew.png")));
    }

    @Override
    public void execute() {
        float f = reproductor.getRate();
        if (f <= 0.25f) {
            // TODO: mostrar velocidad demasiado baja
        } else if (f <= 1) {
            f = reproductor.getRate() - 0.25f;
        } else if (f <= 5) {
            f = reproductor.getRate() - 0.5f;
        }
        reproductor.setRate(f);
        observable.notifyObservers(f);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported");
    }

    public static void addObservador(Observer observer) {
        observable.addObserver(observer);
    }

    public static void removeObservador(Observer observer) {
        observable.deleteObserver(observer);
    }
}
