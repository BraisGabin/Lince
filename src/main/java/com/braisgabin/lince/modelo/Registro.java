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
package com.braisgabin.lince.modelo;

import com.braisgabin.lince.datos.ControladorArchivos;
import com.braisgabin.lince.modelo.instrumentoobservacional.Categoria;
import com.braisgabin.lince.modelo.instrumentoobservacional.Criterio;
import com.braisgabin.lince.modelo.instrumentoobservacional.InstrumentoObservacional;
import com.braisgabin.lince.modelo.instrumentoobservacional.NodoInformacion;
import com.braisgabin.lince.utiles.Tiempo;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Brais
 */
public class Registro extends ModeloDeTablaLince implements Observer {

    private static final String NOMBRE_INSTRUMENTO_OBSERVACIONAL = "nombreInstrumentoObservacional";
    private static final String CRITERIOS = "criterios";
    private static final String DATOS_FIJOS = "datosEstaticos";
    private static final String DATOS_VARIABLES = "datosVariables";
    private static Registro instance = null;
    private Map<NodoInformacion, String> datosFijos;
    private transient boolean necesarySave;
    private transient File path;

    public static void loadNewInstance() {
        cambiarInstancia(new Registro());
    }

    public static void loadIntance(File f) throws RegistroException {
        //TODO mejorar la codificación de esta funcion
        List<String> exceptions = new ArrayList<String>();
        Registro registro = cargarRegistro(f, exceptions);
        cambiarInstancia(registro);
        if (!exceptions.isEmpty()) {
            String string = "";
            for (String exception : exceptions) {
                string += exception + "\n";
            }
            throw new RegistroException(string);
        }
    }

    private static Registro cargarRegistro(File f, List<String> exceptions) throws RegistroException {
        try {
            Map<String, Object> datosGuardados = (Map<String, Object>) ControladorArchivos.getInstance().cargar(f);
            String nombre = (String) datosGuardados.get(NOMBRE_INSTRUMENTO_OBSERVACIONAL);
            if (!nombre.equalsIgnoreCase(InstrumentoObservacional.getInstance().getName())) {
                throw new RegistroException(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EL INSTRUMENTO OBSERVACIONAL ACTUALMENTE ABIERTO NO CORRESPONDE CON EL DE ESTE REGISTRO. ABRA EL INTSRUMENTO OBSERVACIONAL ") + nombre + java.util.ResourceBundle.getBundle("i18n.Bundle").getString(" Y VUELVA A INTENTAR ABRIRLO."));
            }

            Map<NodoInformacion, String> datosFijosGuardados = (Map<NodoInformacion, String>) datosGuardados.get(DATOS_FIJOS);
            Map<NodoInformacion, String> datosFijos = cargarDatosFijos(datosFijosGuardados, exceptions);

            comprobarCriterios((Criterio[]) datosGuardados.get(CRITERIOS), exceptions);

            List<FilaRegistro> datosVariablesGuardados = (List<FilaRegistro>) datosGuardados.get(DATOS_VARIABLES);
            List<FilaRegistro> datosVariables = cargarDatos(datosVariablesGuardados, exceptions);
            return new Registro(datosVariables, datosFijos, exceptions.isEmpty() ? f : null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstrumentoObservacional.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Map<NodoInformacion, String> cargarDatosFijos(Map<NodoInformacion, String> map, List<String> exceptions) {
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();
        Map<NodoInformacion, String> datosFijos = new HashMap<NodoInformacion, String>(map.size());
        Set<NodoInformacion> setDeKeys = map.keySet();
        NodoInformacion nodosInformacion[] = instrumentoObservacional.getDatosFijos();
        for (NodoInformacion nodoInformacionProvisional : setDeKeys) {
            boolean encontrado = false;
            for (NodoInformacion nodoInformacionBienReferenciado : nodosInformacion) {
                if (nodoInformacionBienReferenciado.getNombre().equals(nodoInformacionProvisional.getNombre())) {
                    encontrado = true;
                    datosFijos.put(nodoInformacionBienReferenciado, map.get(nodoInformacionProvisional));
                    break;
                }
            }
            if (!encontrado) {
                exceptions.add(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("NO SE HA ENCONTRADO REFERENCIA PARA EL DATO ESTÁTICO: ") + nodoInformacionProvisional + java.util.ResourceBundle.getBundle("i18n.Bundle").getString(" EN EL INSTRUMENTO OBSERVACIONAL ACTUAL."));
            }
        }
        return datosFijos;
    }

    /**
     * Comprueba que en los criterios que hay guardados dentro del archivo cargado
     * existan en el instrumento observacional actualmente cargado.
     * @param criterios
     * @param exceptions
     */
    private static void comprobarCriterios(Criterio[] criterios, List<String> exceptions) {
        Criterio criteriosActuales[] = InstrumentoObservacional.getInstance().getCriterios();

        for (Criterio criterio : criterios) {
            boolean encontrado = false;
            for (Criterio criterioActual : criteriosActuales) {
                if (criterio.getNombre().equals(criterioActual.getNombre())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                exceptions.add(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("EN EL INSTUMENTO ACTUAL NO SE ENCUENTRA EL CRITERIO ") + criterio + ".");
            }
        }
    }

    private static List<FilaRegistro> cargarDatos(List<FilaRegistro> list, List<String> exceptions) {
        List<FilaRegistro> datos = new ArrayList<FilaRegistro>();
        for (FilaRegistro filaRegistro : list) {
            datos.add(FilaRegistro.getFilaRegistroCorrecta(filaRegistro, exceptions));
        }

        return datos;
    }

    private static void cambiarInstancia(Registro newInstance) {
        TableModelListener listeners[] = instance.getTableModelListeners();
        for (TableModelListener modelListener : listeners) {
            ((JTable) modelListener).setModel(newInstance);
        }
        InstrumentoObservacional.removeObservador(instance);
        instance = newInstance;
    }

    public static synchronized Registro getInstance() {
        if (instance == null) {
            instance = new Registro();
        }
        return instance;
    }

    private Registro() {
        initRegistro(new ArrayList<FilaRegistro>(), new HashMap<NodoInformacion, String>(), null);
    }

    private Registro(List<FilaRegistro> datosVariables, Map<NodoInformacion, String> datosFijos, File path) {
        initRegistro(datosVariables, datosFijos, path);
    }

    private void initRegistro(List<FilaRegistro> datosVariables, Map<NodoInformacion, String> datosFijos, File path) {
        InstrumentoObservacional.addObservador(this);
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();
        this.datos = instrumentoObservacional.getCriterios();
        this.datosMixtos = instrumentoObservacional.getDatosMixtos();

        this.datosVariables = datosVariables;
        this.datosFijos = datosFijos;
        this.path = path;
        this.necesarySave = false;
    }

    public String getValorFijo(NodoInformacion nodoInformacion) {
        return datosFijos.get(nodoInformacion);
    }

    public void setValorFijo(NodoInformacion nodoInformacion, String valor) {
        datosFijos.put(nodoInformacion, valor);
        necesarySave = true;
    }

    public void update(Observable o, Object arg) {
        InstrumentoObservacional instrumentoObservacional = InstrumentoObservacional.getInstance();
        this.datos = instrumentoObservacional.getCriterios();
        this.datosMixtos = instrumentoObservacional.getDatosMixtos();
        fireTableStructureChanged();
    }

    @Override
    protected void necesarioSave() {
        necesarySave = true;
    }

    public boolean isNecesarySave() {
        return necesarySave;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    private String[] getColumna(Criterio criterio) {
        String[] valores = new String[datosVariables.size()];
        int i = 0;
        for (FilaRegistro filaRegistro : datosVariables) {
            Categoria categoria = filaRegistro.getCategoria(criterio);
            valores[i++] = categoria == null ? "" : categoria.getCodigo();
        }
        return valores;
    }

    public void save() {
        try {
            Map<String, Object> map = new HashMap<String, Object>(3);
            map.put(NOMBRE_INSTRUMENTO_OBSERVACIONAL, InstrumentoObservacional.getInstance().getName());
            map.put(CRITERIOS, datos);
            map.put(DATOS_FIJOS, datosFijos);
            map.put(DATOS_VARIABLES, datosVariables);
            ControladorArchivos.getInstance().guardar(path, map);
            necesarySave = false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve el nombre del registro
     * @return Actualmente devuelve el valor del primer valor fijo o "registro"
     * si no existen valores fijos o el valor de este es null o "".
     */
    public String getName() { //TODO: Mejorar la busqueda del nombre del registro.
        NodoInformacion nodosInformacion[] = InstrumentoObservacional.getInstance().getDatosFijos();
        NodoInformacion nodoInformacion;
        String nombreRegistro;
        if (nodosInformacion.length > 0) {
            nodoInformacion = nodosInformacion[0];
            nombreRegistro = datosFijos.get(nodoInformacion);
            if (nombreRegistro == null || nombreRegistro.equals("")) {
                nombreRegistro = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REGISTRO");
            }
        } else {
            nombreRegistro = java.util.ResourceBundle.getBundle("i18n.Bundle").getString("REGISTRO");
        }
        return nombreRegistro;
    }

    public String exportToSdisGseqEvento(List<Criterio> criterios) {
        String contenido = "Event\r\n";
        contenido += InstrumentoObservacional.getInstance().exportToSdisGseq(criterios);
        contenido += exportRegistroSdisGseqEvento(criterios);
        return contenido;
    }

    public String exportToSdisGseqMultievento(List<Criterio> criterios) {
        String contenido = "Multievent\r\n";
        contenido += InstrumentoObservacional.getInstance().exportToSdisGseq(criterios);
        contenido += exportRegistroSdisGseqMultivento(criterios);
        return contenido;
    }

    public String exportToSdisGseqEventoConTiempo(List<Criterio> criterios) {
        String contenido = "Timed\r\n";
        contenido += InstrumentoObservacional.getInstance().exportToSdisGseq(criterios);
        contenido += exportRegistroSdisGseqEventoConTiempo(criterios);
        return contenido;
    }

    public String exportToSdisGseqIntervalo(List<Criterio> criterios) {
        String contenido = "Interval\r\n";
        contenido += InstrumentoObservacional.getInstance().exportToSdisGseq(criterios);
        contenido += exportRegistroSdisGseqIntervalo(criterios);
        return contenido;
    }

    public String exportToSdisGseqEstado(List<Criterio> criterios) {
        String contenido = "State\r\n";
        contenido += InstrumentoObservacional.getInstance().exportToSdisGseq(criterios);
        contenido += exportRegistroSdisGseqState(criterios);
        return contenido;
    }

    private String exportRegistroSdisGseqEvento(List<Criterio> criterios) {
        String contenido = "";

        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, " ");

            contenido += "\r\n" + cont;
        }
        contenido += "/\r\n";
        return contenido;
    }

    private String exportRegistroSdisGseqMultivento(List<Criterio> criterios) {
        String contenido = "";

        boolean isFirst = true;
        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, " ");

            if (isFirst) {
                contenido += "\r\n" + cont;
                isFirst = false;
            } else {
                contenido += ".\r\n" + cont;
            }
        }
        contenido += "/\r\n";
        return contenido;
    }

    private String exportRegistroSdisGseqEventoConTiempo(List<Criterio> criterios) {
        /*
         * TODO comprobar si el tiempo ha de estar siempre en forma m:ss
         *  -   Ver si permite milisegundos
         *  -   Ver si permite horas
         */

        String contenido = "";

        FilaRegistro filaAnterior = null;
        String tiempo = "";
        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, "+");

            // Si la anterior es diferente de null toca terminar la lina anterior.
            if (filaAnterior != null) {
                contenido += "-" + Tiempo.formatSimpleSeconds(filaRegistro.getMilis());
                tiempo = null;
            }

            tiempo = Tiempo.formatSimpleSeconds(filaRegistro.getMilis());
            contenido += "\r\n" + cont + "," + tiempo;

            filaAnterior = filaRegistro;
        }
        if (tiempo != null) {
            contenido += "-" + tiempo;
        }
        contenido += "/\r\n";

        return contenido;
    }

    private String exportRegistroSdisGseqIntervalo(List<Criterio> criterios) {
        String contenido = "";

        FilaRegistro filaAnterior = null;
        int tiempoAnterior = -1;
        boolean isFirst = true;
        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, " ");

            // Si la anterior es diferente de null toca terminar la lina anterior.
            if (filaAnterior != null) {
                contenido += "*" + (filaRegistro.getMilis() - tiempoAnterior);
            }

            if (isFirst) {
                contenido += "\r\n" + cont;
                isFirst = false;
            } else {
                contenido += ",\r\n" + cont;
            }

            filaAnterior = filaRegistro;
            tiempoAnterior = filaRegistro.getMilis();
        }
        contenido += "/\r\n";

        return contenido;
    }

    private String exportRegistroSdisGseqState(List<Criterio> criterios) {
        String contenido = "";

        FilaRegistro filaAnterior = null;
        int tiempoAnterior = -1;
        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, "+");

            // Si la anterior es diferente de null toca terminar la lina anterior.
            if (filaAnterior != null) {
                contenido = contenido.replaceAll("\\+", "=" + (filaRegistro.getMilis() - tiempoAnterior) + "\t") + "=" + (filaRegistro.getMilis() - tiempoAnterior);
            }

            contenido += "\r\n" + cont;

            filaAnterior = filaRegistro;

            tiempoAnterior = filaRegistro.getMilis();
        }
        contenido = contenido.replaceAll("\\+", "=1\t");
        contenido += "/\r\n";

        return contenido;
    }

    public String exportToTheme5(List<Criterio> criterios) {
        return exportRegistroToTheme(criterios, ";", true);
    }

    public String exportToTheme6(List<Criterio> criterios) {
        return exportRegistroToTheme(criterios, "\t", false);
    }

    private String exportRegistroToTheme(List<Criterio> criterios, String separator, boolean dataname) {
        final int MARCA = -2;
        String nombreRegistro = getName();

        String contenido = "";
        if (dataname) {
            contenido += "DATANAME" + separator;
        }
        contenido += "TIME" + separator + "EVENT\r\n";
        int timeFoot = MARCA;

        boolean isFirst = true;

        for (FilaRegistro filaRegistro : datosVariables) {
            String cont = exportFila(criterios, filaRegistro, ",");

            if (isFirst) {
                int time = filaRegistro.getFrames();
                if (dataname) {
                    contenido += nombreRegistro + separator;
                }
                contenido += (time - 1) + separator + ":\r\n";
                isFirst = false;
            }

            if (dataname) {
                contenido += nombreRegistro + separator;
            }
            contenido += filaRegistro.getFrames() + separator + cont + "\r\n";
            timeFoot = MARCA;
        }
        if (timeFoot == MARCA) {
            timeFoot = datosVariables.get(datosVariables.size() - 1).getFrames() + 1;
        }
        if (dataname) {
            contenido += nombreRegistro + separator;
        }
        contenido += timeFoot + separator + "&\r\n";
        return contenido;
    }

    public String exportToSas(List<Object> criterios) {
        return exportRegistroToSas(criterios);
    }

    private String exportRegistroToSas(List<Object> criterios) {
        List<NodoInformacion> fijos = Arrays.asList(InstrumentoObservacional.getInstance().getDatosFijos());
        List<NodoInformacion> mixtos = Arrays.asList(InstrumentoObservacional.getInstance().getDatosMixtos());
        String contenido = "";

        int numCriterios = criterios.size();
        List<String[]> tabla = new ArrayList<String[]>(datosVariables.size());
        for (FilaRegistro fila : datosVariables) {
            String f[] = new String[numCriterios + 1];
            f[numCriterios] = "1";
            int i = 0;
            for (Object obj : criterios) {
                if (fijos.contains(obj)) {
                    f[i++] = getValorFijo((NodoInformacion) obj);
                } else if (mixtos.contains(obj)) {
                    f[i++] = fila.getDatoMixto((NodoInformacion) obj);
                } else {
                    Categoria c = fila.getCategoria((Criterio) obj);
                    if (c != null) {
                        f[i++] = c.getCodigo();
                    }
                }
            }
            tabla.add(f);
        }

        for (int i = 0; i < tabla.size(); i++) {
            String fila[] = tabla.get(i);
            int contador = 2;
            for (int j = i + 1; j < tabla.size(); j++) {
                if (sonIguales(fila, tabla.get(j))) {
                    tabla.remove(j);
                    j--;
                    tabla.get(i)[numCriterios] = contador++ + "";
                }
            }
        }

        for (String[] sArray : tabla) {
            for (String s : sArray) {
                contenido += s + "\t";
            }
            contenido += "\r\n";
        }

        return contenido;
    }

    public String exportToCsv(List<Object> columnas) {
        String contenido = cabeceraCsv(columnas);

        int numColumnas = columnas.size();
        List<String[]> tabla = new ArrayList<String[]>(datosVariables.size());
        FilaRegistro frAnterior = null;
        for (FilaRegistro frActual : datosVariables) {
            String f[] = new String[numColumnas];
            int i = 0;
            for (Object columna : columnas) {
                //"TFrames", "DuraciónFr", "TSegundos", "DuraciónSeg", "TMilisegundos", "DuraciónMiliseg"
                if (columna instanceof String) {
                    if (columna.equals("TFrames")) {
                        f[i] = (frActual.getMilis() / 40) + "";
                    } else if (columna.equals("TSegundos")) {
                        f[i] = Tiempo.formatCompletMiliseconds(frActual.getMilis());
                    } else if (columna.equals("TMilisegundos")) {
                        f[i] = frActual.getMilis() + "";
                    } else if (columna.equals("DuraciónFr") && frAnterior != null) {
                        String fila[] = tabla.get(tabla.size() - 1);
                        fila[i] = ((frActual.getMilis() - frAnterior.getMilis()) / 40) + "";
                        f[i] = "";
                    } else if (columna.equals("DuraciónSeg") && frAnterior != null) {
                        String fila[] = tabla.get(tabla.size() - 1);
                        fila[i] = Tiempo.formatCompletMiliseconds(frActual.getMilis() - frAnterior.getMilis());
                        f[i] = "";
                    } else if (columna.equals("DuraciónMiliseg") && frAnterior != null) {
                        String fila[] = tabla.get(tabla.size() - 1);
                        fila[i] = (frActual.getMilis() - frAnterior.getMilis()) + "";
                        f[i] = "";
                    }
                } else if (columna instanceof Criterio) {
                    Categoria categoria = frActual.getCategoria((Criterio) columna);
                    if (categoria != null) {
                        f[i] = categoria.getCodigo();
                    } else {
                        f[i] = "";
                    }
                } else if (columna instanceof NodoInformacion) {
                    NodoInformacion nodoInformacion = (NodoInformacion) columna;
                    String valor = datosFijos.get(nodoInformacion);
                    if (valor == null) {
                        valor = frActual.getDatoMixto(nodoInformacion);
                        if (valor == null) {
                            valor = "";
                        }
                    }
                    f[i] = valor;
                }
                i++;
            }
            tabla.add(f);
            frAnterior = frActual;
        }

        for (String[] sArray : tabla) {
            for (String s : sArray) {
                contenido += s + ";";
            }
            contenido += "\r\n";
        }

        return contenido;
    }

    private String cabeceraCsv(List<Object> columnas) {
        String contenido = "";
        boolean isFirst = true;

        for (Object columna : columnas) {
            if (isFirst) {
                isFirst = false;
            } else {
                contenido += ";";
            }
            contenido += columna;
        }

        return contenido + "\r\n";
    }

    private boolean sonIguales(String[] fila1, String[] fila2) {
        int size = fila1.length - 1;
        boolean iguales = true;
        for (int i = 0; i < size && iguales; i++) {
            if (fila1[i] != null) {
                if (!fila1[i].equals(fila2[i])) {
                    iguales = false;
                }
            } else {
                if (fila2[i] != null) {
                    iguales = false;
                }
            }
        }
        return iguales;
    }

    private String exportFila(List<Criterio> criterios, FilaRegistro filaRegistro, String separador) {
        boolean isFirst = true;
        String cont = "";
        for (Criterio criterio : criterios) {
            Categoria categoria = filaRegistro.getCategoria(criterio);
            if (categoria != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    cont += separador;
                }
                cont += categoria.getCodigo();
            }
        }
        return cont;
    }

    public List<Double> calcularKappa(File otroRegistroFile, List<Object> criterios) throws RegistroException {
        List<String> exceptions = new ArrayList<String>();
        Registro otroRegistro = cargarRegistro(otroRegistroFile, exceptions);
        List<Double> kappas = new ArrayList<Double>(criterios.size());
        for (Object obj : criterios) {
            Criterio criterio = (Criterio) obj;
            String[] valores = this.getColumna(criterio);
            String[] otrosValores = otroRegistro.getColumna(criterio);
            int tam = valores.length < otrosValores.length ? valores.length : otrosValores.length;
            double acuerdo = acuerdo(valores, otrosValores, tam);
            double pe = probabilidad(valores, otrosValores, tam);
            double p0 = acuerdo / tam;
            double kappa = (p0 - pe) / (1 - pe);
            kappas.add(kappa);
        }
        return kappas;
    }

    private Map<String, Integer> sumarFrecuencia(String[] valores, int tam) {
        Map<String, Integer> frecuencia = new HashMap<String, Integer>();
        for (int i = 0; i < tam; i++) {
            Integer n = frecuencia.get(valores[i]);
            if (n == null) {
                n = 0;
            }
            frecuencia.put(valores[i], n + 1);
        }
        return frecuencia;
    }

    private int acuerdo(String[] valores, String[] otrosValores, int tam) {
        int acuerdo = 0;
        for (int i = 0; i < tam; i++) {
            if (valores[i].equals(otrosValores[i])) {
                acuerdo++;
            }
        }
        return acuerdo;
    }

    private double probabilidad(String[] valores, String[] otrosValores, int tam) {
        Map<String, Integer> frecuenciaThis = sumarFrecuencia(valores, tam);
        Map<String, Integer> frecuenciaOtro = sumarFrecuencia(otrosValores, tam);
        Set<String> categorias = new HashSet<String>(frecuenciaThis.keySet());
        categorias.addAll(frecuenciaOtro.keySet());
        double prob = 0;
        double tam2 = tam * tam;
        for (String categoria : categorias) {
            Integer n = frecuenciaThis.get(categoria);
            Integer m = frecuenciaOtro.get(categoria);
            if (n != null && m != null) {
                prob += n * m / tam2;
            }
        }
        return prob;
    }

    public List<FilaRegistro> getFilasQueConcuerdan(Map<Criterio, Categoria> seleccion) {
        List<FilaRegistro> filaRegistros = new ArrayList<FilaRegistro>(datosVariables);
        List<FilaRegistro> registrosABorrar = new ArrayList<FilaRegistro>();
        Set<Criterio> criteriosSeleccionados = seleccion.keySet();

        for (Criterio criterio : criteriosSeleccionados) {
            Categoria categoria = seleccion.get(criterio);
            for (FilaRegistro fr : filaRegistros) {
                if (fr.getCategoria(criterio) != categoria) {
                    registrosABorrar.add(fr);
                }
            }
            filaRegistros.removeAll(registrosABorrar);
        }
        return filaRegistros;
    }

    public void addRow(int milis, Map<Criterio, Categoria> categoriasSeleccionadas, Map<NodoInformacion, String> datosMixtos) {
        // TODO: bajar la complejidad (orden computacional)
        FilaRegistro filaRegistro = new FilaRegistro(milis, categoriasSeleccionadas, datosMixtos);
        datosVariables.add(filaRegistro);
        Collections.sort(datosVariables);
        int indice = datosVariables.lastIndexOf(filaRegistro);
        fireTableRowsInserted(indice, indice);
        necesarioSave();
        TableModelListener[] tml = getTableModelListeners();
        // TODO intentar evitar esta Ñapa para que se mantenga vea el ultimo añadido
        ((JTable) tml[0]).scrollRectToVisible(new Rectangle(((JTable) tml[0]).getCellRect(indice, 0, true)));
    }
}
