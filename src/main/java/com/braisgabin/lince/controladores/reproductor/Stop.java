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
package com.braisgabin.lince.controladores.reproductor;

import com.braisgabin.lince.Command;
import com.braisgabin.lince.reproductor.Reproductor;
import javax.swing.Action;
import javax.swing.ImageIcon;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

/**
 *
 * @author Brais
 */
public class Stop extends Command implements MediaPlayerEventListener {

    private Reproductor reproductor;

    public Stop(Reproductor reproductor) {
        this.reproductor = reproductor;
        //putValue(Action.NAME, "Stop");
        putValue(Action.ACTION_COMMAND_KEY, "Stop");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("STOP"));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getResource("/iconos/x32/player_stop.png")));
    }

    @Override
    public void execute() {
        reproductor.stop();
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void mediaChanged(MediaPlayer mp, libvlc_media_t l, String string) {
    }

    @Override
    public void opening(MediaPlayer mp) {
    }

    @Override
    public void buffering(MediaPlayer mp, float f) {
    }

    @Override
    public void playing(MediaPlayer mp) {
    }

    @Override
    public void paused(MediaPlayer mp) {
    }

    @Override
    public void stopped(MediaPlayer mp) {
    }

    @Override
    public void forward(MediaPlayer mp) {
    }

    @Override
    public void backward(MediaPlayer mp) {
    }

    @Override
    public void finished(MediaPlayer mp) {
        execute();
    }

    @Override
    public void timeChanged(MediaPlayer mp, long l) {
    }

    @Override
    public void positionChanged(MediaPlayer mp, float f) {
    }

    @Override
    public void seekableChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void pausableChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void titleChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void snapshotTaken(MediaPlayer mp, String string) {
    }

    @Override
    public void lengthChanged(MediaPlayer mp, long l) {
    }

    @Override
    public void videoOutput(MediaPlayer mp, int i) {
    }

    @Override
    public void error(MediaPlayer mp) {
    }

    @Override
    public void mediaMetaChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void mediaSubItemAdded(MediaPlayer mp, libvlc_media_t l) {
    }

    @Override
    public void mediaDurationChanged(MediaPlayer mp, long l) {
    }

    @Override
    public void mediaParsedChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void mediaFreed(MediaPlayer mp) {
    }

    @Override
    public void mediaStateChanged(MediaPlayer mp, int i) {
    }

    @Override
    public void newMedia(MediaPlayer mp) {
    }

    @Override
    public void subItemPlayed(MediaPlayer mp, int i) {
    }

    @Override
    public void subItemFinished(MediaPlayer mp, int i) {
    }

    @Override
    public void endOfSubItems(MediaPlayer mp) {
    }

}
