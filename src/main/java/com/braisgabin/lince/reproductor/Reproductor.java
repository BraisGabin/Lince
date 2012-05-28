/*
 * Lince - Automatizacion de datos observacionales
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
package com.braisgabin.lince.reproductor;

import com.sun.awt.AWTUtilities;
import com.sun.jna.platform.WindowUtils;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import uk.co.caprica.vlcj.player.AudioOutput;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.DefaultFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsCanvas;

/**
 *
 * @author Brais
 */
public class Reproductor extends JPanel {

    private Controles controles;
    private Canvas videoSurface;
    private EmbeddedMediaPlayer mediaPlayer;
    private long retraso = 15;

    public Reproductor(Window window) {
        initComponents(window);

        //TODO: Borrar esto para que no se cargue automaticamente este video
        //mediaPlayer.prepareMedia("C:\\Users\\Brais\\Desktop\\I+D+I\\VIDEOS CAMPEONATO ESPAÑA ABSOLUTO\\SOLO LIBRE\\06-judith-requena-granollers.avi");
    }

    private void initComponents(Window window) {
        setLayout(new BorderLayout());

        setBackground(Color.white);

        initMediaPlayer(window);

        controles = new Controles(this);

        add(videoSurface, BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);
    }

    private void initMediaPlayer(Window mainFrame) {
        if (RuntimeUtil.isWindows()) {
            // If running on Windows and you want the mouse/keyboard event hack...
            videoSurface = new WindowsCanvas();
        } else {
            videoSurface = new Canvas();
        }

        videoSurface.setBackground(Color.black);
        videoSurface.setMinimumSize(new Dimension(440, 100)); // Necesario para que funcione el JSplitPane

        // Since we're mixing lightweight Swing components and heavyweight AWT
        // components this is probably a good idea
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        List<String> vlcArgs = new ArrayList<String>();

        // vlcArgs.add("--ffmpeg-hw"); // <--- if your system supports it, this might be useful
        vlcArgs.add("--no-plugins-cache");
        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--no-snapshot-preview");
        vlcArgs.add("--quiet");
        vlcArgs.add("--quiet-synchro");
        vlcArgs.add("--intf");
        vlcArgs.add("dummy");

        uk.co.caprica.vlcj.logger.Logger.debug("vlcArgs={}", vlcArgs);


        FullScreenStrategy fullScreenStrategy = new DefaultFullScreenStrategy(mainFrame);

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");

        List<AudioOutput> audioOutputs = mediaPlayerFactory.getAudioOutputs();
        uk.co.caprica.vlcj.logger.Logger.debug("audioOutputs={}", audioOutputs);

        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(fullScreenStrategy);
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        mediaPlayer.setPlaySubItems(true);

        mediaPlayer.setEnableKeyInputHandling(false);
        mediaPlayer.setEnableMouseInputHandling(false);

        // Won't work with OpenJDK or JDK1.7, requires a Sun/Oracle JVM (currently)
        boolean transparentWindowsSupport = true;
        try {
            Class.forName("com.sun.awt.AWTUtilities");
        } catch (Exception e) {
            transparentWindowsSupport = false;
        }

        if (transparentWindowsSupport) {
            final Window test = new Window(null, WindowUtils.getAlphaCompatibleGraphicsConfiguration()) {

                private static final long serialVersionUID = 1L;

                public void paint(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;

                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

                    g.setColor(Color.white);
                    g.fillRoundRect(100, 150, 100, 100, 32, 32);

                    g.setFont(new Font("Sans", Font.BOLD, 32));
                    g.drawString("Heavyweight overlay test", 100, 300);
                }
            };

            AWTUtilities.setWindowOpaque(test, false); // Doesn't work in full-screen exclusive
            // mode, you would have to use 'simulated'
            // full-screen - requires Sun/Oracle JDK
            test.setBackground(new Color(0, 0, 0, 0)); // This is what you do in JDK7

            // mediaPlayer.setOverlay(test);
            // mediaPlayer.enableOverlay(true);
        }

        mediaPlayerFactory.release();
    }

    public void release() {
        if (videoSurface instanceof WindowsCanvas) {
            ((WindowsCanvas) videoSurface).release();
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playPause() {
        controles.playPause();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public long getTime() {
        return mediaPlayer.getTime();
    }

    public void setTime(long l) {
        mediaPlayer.setTime(l - retraso);
    }

    public float getRate() {
        return mediaPlayer.getRate();
    }

    public void setRate(float f) {
        mediaPlayer.setRate(f);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void play() {
        mediaPlayer.play();
    }

    public boolean isMute() {
        return mediaPlayer.isMute();
    }

    public void mute() {
        mediaPlayer.mute();
    }

    public void setVolume(int volumen) {
        mediaPlayer.setVolume(volumen);
    }

    public void setPosition(float f) {
        mediaPlayer.setPosition(f);
    }

    public void prepareMedia(String absolutePath) {
        mediaPlayer.prepareMedia(absolutePath);
        fixRetraso();
    }

    public void addMediaPlayerEventListener(MediaPlayerEventListener mediaPlayerEventListener) {
        mediaPlayer.addMediaPlayerEventListener(mediaPlayerEventListener);
    }

    public float getPosition() {
        return mediaPlayer.getPosition();
    }

    public void skip(long time) {
        mediaPlayer.skip(time - retraso);
    }

    private void fixRetraso() {
        new Reproductor.Prueba().start();
    }

    private class Prueba extends Thread {

        private static final int ESPERA = 500;

        @Override
        public synchronized void run() {
            try {
                boolean mute = true;
                wait(ESPERA);
                if (!mediaPlayer.isMute()) {
                    mediaPlayer.mute();
                    mute = false;
                }
                mediaPlayer.play();
                wait(ESPERA);
                if (!mute) {
                    mediaPlayer.mute();
                }
                mediaPlayer.pause();
                mediaPlayer.skip(1);
                long time2 = mediaPlayer.getTime();
                long time3 = time2;
                for (int i = 0; i < 100 && time2 == time3; i++) {
                    wait(100);
                    time3 = mediaPlayer.getTime();
                }
                mediaPlayer.stop();
                retraso = time3 - time2;
            } catch (InterruptedException ex) {
                Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
