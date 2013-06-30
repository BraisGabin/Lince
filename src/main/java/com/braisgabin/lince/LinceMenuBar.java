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
package com.braisgabin.lince;

import com.braisgabin.lince.controladores.CambiarIdioma;
import com.braisgabin.lince.controladores.CerrarAplicacion;
import com.braisgabin.lince.controladores.MostrarAcercaDe;
import com.braisgabin.lince.controladores.calidad.AbrirCalcularKappa;
import com.braisgabin.lince.controladores.exportar.*;
import com.braisgabin.lince.controladores.importar.*;
import com.braisgabin.lince.controladores.instrumentoobservacional.LoadInstrumentoObservacional;
import com.braisgabin.lince.controladores.instrumentoobservacional.NewInstrumentoObservacional;
import com.braisgabin.lince.controladores.instrumentoobservacional.SaveInstrumentoObservacional;
import com.braisgabin.lince.controladores.instrumentoobservacional.SaveInstrumentoObservacionalAs;
import com.braisgabin.lince.controladores.registro.LoadRegistro;
import com.braisgabin.lince.controladores.registro.NewRegistro;
import com.braisgabin.lince.controladores.registro.SaveRegistro;
import com.braisgabin.lince.controladores.registro.SaveRegistroAs;
import com.braisgabin.lince.controladores.reproductor.*;
import com.braisgabin.lince.reproductor.Reproductor;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author Brais
 */
public class LinceMenuBar extends JMenuBar {

    private JMenu archivo;
    private JMenu archivoInstrumentoObservacional;
    private JMenuItem archivoInstrumentoObservacionalNuevo;
    private JMenuItem archivoInstrumentoObservacionalCargar;
    private JMenuItem archivoInstrumentoObservacionalGuardar;
    private JMenuItem archivoInstrumentoObservacionalGuardarComo;
    private JMenu archivoRegistro;
    private JMenuItem archivoRegistroNuevo;
    private JMenuItem archivoRegistroCargar;
    private JMenuItem archivoRegistroGuardar;
    private JMenuItem archivoRegistroGuardarComo;
    private JMenu archivoExportar;
    private JMenu archivoExportarTheme;
    private JMenuItem archivoExportarTheme5;
    private JMenuItem archivoExportarTheme6;
    private JMenu archivoExportarSdisGseq;
    private JMenuItem archivoExportarSdisGseqEvento;
    private JMenuItem archivoExportarSdisGseqEstado;
    private JMenuItem archivoExportarSdisGseqEventoConTiempo;
    private JMenuItem archivoExportarSdisGseqIntervalo;
    private JMenuItem archivoExportarSdisGseqMultievento;
    private JMenuItem archivoExportarSas;
    private JMenuItem archivoExportarCsv;
    private JMenu archivoImportar;
    private JMenuItem archivoImportarHoisan;
    private JMenuItem archivoAbrirVideo;
    private JMenuItem archivoSalir;
    //private JMenu edicion;
    private JMenu menuReproductor;
    private JMenuItem reproductorPlayPause;
    private JMenuItem reproductorStop;
    private JMenuItem reproductorMuteUnmute;
    private JMenuItem reproductorFaster;
    private JMenuItem reproductorSlower;
    private JMenu calidadDelDato;
    private JMenuItem calidadDelDatoKappa;
    //private JMenu ver;
    //private JMenuItem verFullScreen;
    private JMenu idioma;
    private JMenu ayuda;
    private JMenuItem ayudaAcercaDe;
    private Reproductor reproductor;

    public LinceMenuBar(Reproductor reproductor) {
        this.reproductor = reproductor; // TODO quitar esto

        initComponents();

        this.reproductor = null;
    }

    private void initComponents() {
        initMenuArchivo();
        //initMenuEdicion();
        initMenuReproductor();
        initCalidadDelDato();
        initIdioma();
        //initMenuVer();
        initMenuAyuda();
    }

    private void initMenuArchivo() {
        archivo = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("ARCHIVO"));

        archivoAbrirVideo = new JMenuItem(new AbrirCalcularKappa());
        archivoAbrirVideo = new JMenuItem(new CargarVideo(reproductor));
        archivoSalir = new JMenuItem(new CerrarAplicacion());

        archivoInstrumentoObservacional = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("INSTRUMENTO OBSERVACIONAL"));
        archivoInstrumentoObservacionalNuevo = new JMenuItem(new NewInstrumentoObservacional());
        archivoInstrumentoObservacionalCargar = new JMenuItem(new LoadInstrumentoObservacional());
        archivoInstrumentoObservacionalGuardar = new JMenuItem(new SaveInstrumentoObservacional());
        archivoInstrumentoObservacionalGuardarComo = new JMenuItem(new SaveInstrumentoObservacionalAs());

        archivoInstrumentoObservacional.add(archivoInstrumentoObservacionalNuevo);
        archivoInstrumentoObservacional.add(new JSeparator());
        archivoInstrumentoObservacional.add(archivoInstrumentoObservacionalCargar);
        archivoInstrumentoObservacional.add(new JSeparator());
        archivoInstrumentoObservacional.add(archivoInstrumentoObservacionalGuardar);
        archivoInstrumentoObservacional.add(archivoInstrumentoObservacionalGuardarComo);

        archivoRegistro = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REGISTRO"));
        archivoRegistroNuevo = new JMenuItem(new NewRegistro());
        archivoRegistroCargar = new JMenuItem(new LoadRegistro());
        archivoRegistroGuardar = new JMenuItem(new SaveRegistro());
        archivoRegistroGuardarComo = new JMenuItem(new SaveRegistroAs());

        archivoRegistro.add(archivoRegistroNuevo);
        archivoRegistro.add(new JSeparator());
        archivoRegistro.add(archivoRegistroCargar);
        archivoRegistro.add(new JSeparator());
        archivoRegistro.add(archivoRegistroGuardar);
        archivoRegistro.add(archivoRegistroGuardarComo);

        archivoExportar = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EXPORTAR"));
        archivoExportarTheme = new JMenu("Theme");
        archivoExportarTheme5 = new JMenuItem(new AbrirExportarTheme5());
        archivoExportarTheme6 = new JMenuItem(new AbrirExportarTheme6());
        archivoExportarSdisGseq = new JMenu("SDIS-GSEQ");
        archivoExportarSdisGseqEvento = new JMenuItem(new AbrirExportarSdisGseqEvento());
        archivoExportarSdisGseqEstado = new JMenuItem(new AbrirExportarSdisGseqEstado());
        archivoExportarSdisGseqEventoConTiempo = new JMenuItem(new AbrirExportarSdisGseqEventoConTiempo());
        archivoExportarSdisGseqIntervalo = new JMenuItem(new AbrirExportarSdisGseqIntervalo());
        archivoExportarSdisGseqMultievento = new JMenuItem(new AbrirExportarSdisGseqMultievento());
        archivoExportarSas = new JMenuItem(new AbrirExportarSas());
        archivoExportarCsv = new JMenuItem(new AbrirExportarCsv());

        archivoExportarTheme.add(archivoExportarTheme6);
        archivoExportarTheme.add(archivoExportarTheme5);

        archivoExportarSdisGseq.add(archivoExportarSdisGseqEvento);
        archivoExportarSdisGseq.add(archivoExportarSdisGseqEstado);
        archivoExportarSdisGseq.add(archivoExportarSdisGseqEventoConTiempo);
        archivoExportarSdisGseq.add(archivoExportarSdisGseqIntervalo);
        archivoExportarSdisGseq.add(archivoExportarSdisGseqMultievento);

        archivoExportar.add(archivoExportarTheme);
        archivoExportar.add(archivoExportarSdisGseq);
        archivoExportar.add(archivoExportarSas);
        archivoExportar.add(archivoExportarCsv);
        
        archivoImportar = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("IMPORTAR"));
        archivoImportarHoisan = new JMenuItem(new AbrirImportarHoisan());
        archivoImportar.add(archivoImportarHoisan);

        archivo.add(archivoInstrumentoObservacional);
        archivo.add(archivoRegistro);
        archivo.add(new JSeparator());
        archivo.add(archivoExportar);
        archivo.add(archivoImportar);
        archivo.add(new JSeparator());
        archivo.add(archivoAbrirVideo);
        archivo.add(new JSeparator());
        archivo.add(archivoSalir);

        add(archivo);
    }

    /*private void initMenuEdicion() {
    edicion = new JMenu("Edición");

    add(edicion);
    }*/
    private void initMenuReproductor() {
        menuReproductor = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REPRODUCTOR"));

        reproductorPlayPause = new JMenuItem(new PlayPause(reproductor));
        reproductorPlayPause.setText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("PLAY/PAUSE"));
        reproductorStop = new JMenuItem(new Stop(reproductor));
        reproductorStop.setText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("STOP"));
        reproductorMuteUnmute = new JMenuItem(new MuteUnmute(reproductor));
        reproductorMuteUnmute.setText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("SILENCIEAR/ACTIVAR SONIDO"));
        reproductorFaster = new JMenuItem(new Faster(reproductor));
        reproductorFaster.setText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("MÁS RÁPIDO"));
        reproductorSlower = new JMenuItem(new Slower(reproductor));
        reproductorSlower.setText(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("MÁS LENTO"));

        menuReproductor.add(reproductorPlayPause);
        menuReproductor.add(reproductorStop);
        menuReproductor.add(reproductorMuteUnmute);
        menuReproductor.add(reproductorFaster);
        menuReproductor.add(reproductorSlower);

        add(menuReproductor);
    }

    private void initCalidadDelDato() {
        calidadDelDato = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("CALIDAD DEL DATO"));

        calidadDelDatoKappa = new JMenuItem(new AbrirCalcularKappa());

        calidadDelDato.add(calidadDelDatoKappa);

        add(calidadDelDato);
    }

    /*private void initMenuVer() {
    ver = new JMenu("Ver");

    verFullScreen = new JMenuItem(new FullScreenMode());

    ver.add(verFullScreen);

    add(ver);
    }*/
    private void initIdioma() {
        idioma = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("IDIOMA"));

        ButtonGroup bg = new ButtonGroup();

        for (Locale locale : LinceFrame.getLocales()) {
            JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(new CambiarIdioma(locale));
            bg.add(radioButton);
            if(locale.equals(Locale.getDefault())) {
                radioButton.setSelected(true);
            }
            idioma.add(radioButton);
        }

        add(idioma);
    }

    private void initMenuAyuda() {
        ayuda = new JMenu(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("AYUDA"));

        ayudaAcercaDe = new JMenuItem(new MostrarAcercaDe());

        ayuda.add(ayudaAcercaDe);

        add(ayuda);
    }
}
