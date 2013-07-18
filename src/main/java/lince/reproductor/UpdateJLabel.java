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

package lince.reproductor;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author Brais
 */
public class UpdateJLabel extends JLabel implements Observer {

    public UpdateJLabel() {
    }

    public UpdateJLabel(String text) {
        super(text);
    }

    public UpdateJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public void update(Observable o, Object arg) {
        setText("x" + arg);
    }
}
