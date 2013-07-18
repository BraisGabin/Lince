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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

import lince.Command;
import lince.reproductor.Reproductor;
import lince.utiles.PathArchivos;
import uk.co.caprica.vlcj.filter.swing.SwingFileFilterFactory;

/**
 *
 * @author Brais
 */
public class CargarVideo extends Command {

    private Reproductor reproductor;

    public CargarVideo(Reproductor reproductor) {
        this.reproductor = reproductor;
        putValue(Action.NAME, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CARGAR VÍDEO"));
        putValue(Action.ACTION_COMMAND_KEY, "CargarVideo");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CARGA UN VÍDEO"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/iconos/x16/player_eject.png")));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x32/player_eject.png")));
    }

    @Override
    public void execute() {
        List<FileFilter> fileFilters = new ArrayList<FileFilter>();
        fileFilters.add(SwingFileFilterFactory.newMediaFileFilter()); // TODO: cambiar el filtro para que salga en castellano
        File directorioPredeterminado = null;

        File path = PathArchivos.getPathArchivoAbrir(fileFilters, directorioPredeterminado, null);

        if (path != null) {
            reproductor.stop();
            reproductor.prepareMedia(path.getAbsolutePath());
        }
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
