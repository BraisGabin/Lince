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
package com.braisgabin.lince.modelo.instrumentoobservacional;

import com.braisgabin.lince.datos.ControladorArchivos;
import com.braisgabin.lince.utiles.MyObservable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Brais
 */
public class InstrumentoObservacional implements TreeModelListener {

    private static InstrumentoObservacional instance = null;
    private static Observable observable = new MyObservable();
    private RootInstrumentoObservacional raiz;
    private transient File path;
    private transient boolean necesarySave;
    private transient DefaultTreeModel modelo = null;

    public static synchronized InstrumentoObservacional getInstance() {
        if (instance == null) {
            loadNewInstance();
        }
        return instance;
    }

    public static void loadNewInstance() {
        instance = new InstrumentoObservacional(getNewRootInstrumentObservacional(), null);
        observable.notifyObservers("new");
    }

    public static void loadIntance(File f) {
        try {
            RootInstrumentoObservacional arbol = (RootInstrumentoObservacional) ControladorArchivos.getInstance().cargar(f);
            instance = new InstrumentoObservacional(arbol, f);
            observable.notifyObservers("load");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstrumentoObservacional.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static RootInstrumentoObservacional getNewRootInstrumentObservacional() {
        RootInstrumentoObservacional r = new RootInstrumentoObservacional(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("NUEVO I. OBSERVACIONAL"));
        r.add(new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("FIJOS")));
        r.add(new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("MIXTOS")));
        r.add(new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("i18n.Bundle").getString("VARIABLES")));
        return r;
    }

    public InstrumentoObservacional(RootInstrumentoObservacional arbol, File path) {
        this.raiz = arbol;
        this.path = path;
        this.necesarySave = false;
        this.modelo = null;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public static void addObservador(Observer observer) {
        observable.addObserver(observer);
    }

    public static void removeObservador(Observer observer) {
        observable.deleteObserver(observer);
    }

    public Criterio[] getCriterios() {
        TreeNode variables = raiz.getChildAt(2);
        int numCriterios = variables.getChildCount();
        Criterio criterios[] = new Criterio[numCriterios];
        for (int i = 0; i < numCriterios; i++) {
            criterios[i] = (Criterio) variables.getChildAt(i);
        }
        return criterios;
    }

    public void save() {
        try {
            ControladorArchivos.getInstance().guardar(path, raiz);
            necesarySave = false;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InstrumentoObservacional.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNecesarySave() {
        return necesarySave;
    }

    public DefaultTreeModel getModel() {
        if (modelo == null) {
            modelo = new DefaultTreeModel(raiz);
            modelo.addTreeModelListener(this);
        }
        return modelo;
    }

    public void treeNodesChanged(TreeModelEvent e) {
        necesarySave = true;
        observable.notifyObservers();
    }

    public void treeNodesInserted(TreeModelEvent e) {
        necesarySave = true;
        observable.notifyObservers();
    }

    public void treeNodesRemoved(TreeModelEvent e) {
        necesarySave = true;
        observable.notifyObservers();
    }

    public void treeStructureChanged(TreeModelEvent e) {
        necesarySave = true;
        observable.notifyObservers();
    }

    public NodoInformacion[] getDatosFijos() {
        TreeNode variables = raiz.getChildAt(0);
        int numNodoInformacion = variables.getChildCount();
        NodoInformacion nodoInformacion[] = new NodoInformacion[numNodoInformacion];
        for (int i = 0; i < numNodoInformacion; i++) {
            nodoInformacion[i] = (NodoInformacion) variables.getChildAt(i);
        }
        return nodoInformacion;
    }

    public NodoInformacion[] getDatosMixtos() {
        TreeNode variables = raiz.getChildAt(1);
        int numNodoInformacion = variables.getChildCount();
        NodoInformacion nodoInformacion[] = new NodoInformacion[numNodoInformacion];
        for (int i = 0; i < numNodoInformacion; i++) {
            nodoInformacion[i] = (NodoInformacion) variables.getChildAt(i);
        }
        return nodoInformacion;
    }

    public String getName() {
        return raiz.getNombre();
    }

    public String exportToSdisGseq(List<Criterio> criterios) {
        String contenido = "";
        for (Criterio criterio : criterios) {
            contenido += "\r\n($" + criterio.getNombre() + " ="; //FIXME hay que quitar espacios y seguro que mas cosas
            List<Categoria> categorias = criterio.getCategoriasHoja();
            for (Categoria categoria : categorias) {
                contenido += " " + categoria.getCodigo();
            }
            contenido += ")";
        }
        return contenido + ";\r\n";
    }

    public String exportToTheme(List<Criterio> criterios) {
        String contenido = "";
        for (Criterio criterio : criterios) {
            contenido += criterio.getNombre() + "\r\n";
            List<Categoria> categorias = criterio.getCategoriasHoja();
            for (Categoria categoria : categorias) {
                contenido += " " + categoria.getCodigo() + "\r\n";
            }
        }

        return contenido;
    }

    public void addHijo(DefaultMutableTreeNode parent, String nombre) {
        if (modelo.getPathToRoot(parent).length > 2) {
            modelo.insertNodeInto(new Categoria(nombre), parent, parent.getChildCount());
        } else {
            int index = raiz.getIndex(parent);
            switch (index) {
                case 0:
                case 1:
                    modelo.insertNodeInto(new NodoInformacion(nombre), parent, parent.getChildCount());
                    break;
                case 2:
                    modelo.insertNodeInto(new Criterio(nombre), parent, parent.getChildCount());
            }
        }
    }

    public void removeNodo(DefaultMutableTreeNode node) {
        modelo.removeNodeFromParent(node);
    }
}
