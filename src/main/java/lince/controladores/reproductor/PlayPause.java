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

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import lince.Command;
import lince.reproductor.Reproductor;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

/**
 *
 * @author Brais
 */
public class PlayPause extends Command implements MediaPlayerEventListener {

    private Reproductor reproductor;
    private ImageIcon playIcon;
    private ImageIcon pauseIcon;

    public PlayPause(Reproductor reproductor) {
        playIcon = new ImageIcon(getClass().getResource("/iconos/x32/player_play.png"));
        pauseIcon = new ImageIcon(getClass().getResource("/iconos/x32/player_pause.png"));

        this.reproductor = reproductor;
        //putValue(Action.NAME, "Play");
        putValue(Action.ACTION_COMMAND_KEY, "Play/Pause");
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("PLAY"));
        putValue(Action.LARGE_ICON_KEY, playIcon);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
    }

    @Override
    public void execute() {
        if (reproductor.isPlaying()) {
            reproductor.pause();
            //putValue(Action.NAME, "Play");
            putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("PLAY"));
            putValue(Action.LARGE_ICON_KEY, playIcon);
        } else {
            reproductor.play();
            //putValue(Action.NAME, "Pause");
            putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("PAUSE"));
            putValue(Action.LARGE_ICON_KEY, pauseIcon);
        }
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
        putValue(Action.SHORT_DESCRIPTION, java.util.ResourceBundle.getBundle("i18n.Bundle").getString("PLAY"));
        putValue(Action.LARGE_ICON_KEY, playIcon);
    }

    @Override
    public void forward(MediaPlayer mp) {
    }

    @Override
    public void backward(MediaPlayer mp) {
    }

    @Override
    public void finished(MediaPlayer mp) {
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
