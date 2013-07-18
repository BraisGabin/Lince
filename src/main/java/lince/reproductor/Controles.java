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
package lince.reproductor;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lince.controladores.reproductor.*;
import lince.utiles.Tiempo;
import uk.co.caprica.vlcj.binding.LibVlcConst;

/**
 *
 * @author Brais
 */
class Controles extends JPanel {

    private JSlider volumen;
    private JSlider positionSlider;
    private JLabel time;
    private UpdateJLabel speed;
    private ChangeVolume actionChangeVolume;
    private Reproductor reproductor;
    private ChangePosition actionChangePosition;
    private Timer updater;
    private boolean bloquearPosition = false;
    private PlayPause actionPlayPause;

    public Controles(Reproductor reproductor) {
        this.reproductor = reproductor;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        JPanel pControls = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel pInfo = new JPanel(new GridBagLayout());

        JPanel pPosition = new JPanel(new BorderLayout());

        time = new JLabel();
        time.setFont(time.getFont().deriveFont(Font.PLAIN));
        speed = new UpdateJLabel("x1.0");
        speed.setFont(speed.getFont().deriveFont(Font.PLAIN));
        speed.setToolTipText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("VELOCIDAD DE REPRODUCCIÓN"));
        JSpinner stepSpinner = new JSpinner(new SpinnerNumberModel(0.5, 0.001, Double.MAX_EXPONENT, 0.001));
        stepSpinner.setPreferredSize(new Dimension(80, 20));
        Faster.addObservador(speed);
        Slower.addObservador(speed);

        actionPlayPause = new PlayPause(reproductor);
        reproductor.addMediaPlayerEventListener(actionPlayPause);
        JButton playPause = new JButton(actionPlayPause);
        Stop s = new Stop(reproductor);
        reproductor.addMediaPlayerEventListener(s);
        JButton stop = new JButton(new Stop(reproductor));
        JButton masRapido = new JButton(new Faster(reproductor));
        JButton masLento = new JButton(new Slower(reproductor));
        JButton MuteUnmute = new JButton(new MuteUnmute(reproductor));
        StepUp su = new StepUp(reproductor, (Double) stepSpinner.getValue());
        stepSpinner.addChangeListener(su);
        JButton stepUp = new JButton(su);
        StepDown sd = new StepDown(reproductor, (Double) stepSpinner.getValue());
        stepSpinner.addChangeListener(sd);
        JButton stepDown = new JButton(sd);
        volumen = new JSlider();
        positionSlider = new JSlider();
        actionChangeVolume = new ChangeVolume(reproductor);
        actionChangePosition = new ChangePosition(reproductor);

        Insets insets = new Insets(0, 0, 0, 0);
        playPause.setMargin(insets);
        stop.setMargin(insets);
        masRapido.setMargin(insets);
        masLento.setMargin(insets);
        MuteUnmute.setMargin(insets);
        stepUp.setMargin(insets);
        stepDown.setMargin(insets);

        MuteUnmute.setBorder(null);
        MuteUnmute.setContentAreaFilled(false);

        stepUp.setBorder(null);
        stepUp.setContentAreaFilled(false);

        stepDown.setBorder(null);
        stepDown.setContentAreaFilled(false);

        volumen.setMinimum(LibVlcConst.MIN_VOLUME);
        volumen.setMaximum(LibVlcConst.MAX_VOLUME);
        volumen.setValue(50);
        volumen.setToolTipText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("VOLUMEN"));
        volumen.setPreferredSize(new Dimension(100, 40));
        volumen.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                actionChangeVolume.setVolumen(volumen.getValue());
                actionChangeVolume.execute();
            }
        });
        positionSlider.setMinimum(0);
        positionSlider.setMaximum(ChangePosition.MAXVALOR);
        positionSlider.setValue(0);
        positionSlider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (!bloquearPosition) {
                    actionChangePosition.setPosition(positionSlider.getValue());
                    actionChangePosition.execute();
                }
            }
        });

        pControls.add(playPause);
        pControls.add(stop);
        pControls.add(masLento);
        pControls.add(masRapido);
        pControls.add(MuteUnmute);
        pControls.add(volumen);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        pInfo.add(speed, constraints);

        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 2;
        pInfo.add(time, constraints);

        JPanel stepPanel = new JPanel(new BorderLayout());
        stepPanel.add(stepDown, BorderLayout.WEST);
        stepPanel.add(stepSpinner, BorderLayout.CENTER);
        stepPanel.add(stepUp, BorderLayout.EAST);

        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        pInfo.add(stepPanel, constraints);

        //pPosition.add(stepDown, BorderLayout.WEST);
        pPosition.add(positionSlider, BorderLayout.CENTER);
        //pPosition.add(stepUp, BorderLayout.EAST);

        this.add(pControls, BorderLayout.WEST);
        this.add(pPosition, BorderLayout.NORTH);
        this.add(pInfo, BorderLayout.EAST);

        updater = new Timer("Actualizador de controles");
        updater.schedule(new UpdateControls(), 0, 990);
    }

    void playPause() {
        actionPlayPause.execute();
    }

    /*
     * TODO: optimizar esto por que muchas veces hace trabajo que no es necesario.
     */
    private final class UpdateControls extends TimerTask {

        @Override
        public void run() {
            if (!positionSlider.getValueIsAdjusting()) {
                int valor = Math.round(reproductor.getPosition() * positionSlider.getMaximum());
                bloquearPosition = true; //FIXME: puede no funcinar siempre esta ñapa de bloquearPosition
                positionSlider.setValue(valor);
                bloquearPosition = false;
            }
            time.setText(Tiempo.formatSimpleSeconds(reproductor.getTime()));
        }
    }
}
