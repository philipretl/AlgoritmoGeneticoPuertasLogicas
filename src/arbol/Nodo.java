/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.io.Serializable;

/**
 *
 * @author philipretl
 */
public class Nodo <T> implements Serializable{
//miembros de acceso
    
    T datos;
    Nodo<T> nododerecho;
    Nodo<T> nodoizquierdo;
	int id;

    //iniciar dato y hacer de este nodo un nodo hoja
    //public Nodo(T datosNodo)
    public Nodo(int id){
		
		//nodoizquierdo = nododerecho = null; //el nodo no tiene hijos
		
        this.id=id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getDatos() {
		return datos;
	}

	public void setDatos(T datos) {
		this.datos = datos;
	}

    
	public Nodo getHojaIzquierda() {
		return nodoizquierdo;
	}

	void setHojaIzquierda(Nodo nodo) {
		nodoizquierdo=nodo;
	}

	public Nodo getHojaDerecha() {
		return nododerecho;
	}

	void setHojaDerecha(Nodo nodo) {
		nododerecho=nodo;
	}

}
