/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author philipretl
 */
public class Nodo <T>{
//miembros de acceso
    
    T datos;
    Nodo <T> nododerecho;
    Nodo<T> nodoizquierdo;

    //iniciar dato y hacer de este nodo un nodo hoja
    //public Nodo(T datosNodo)
    public Nodo(){
	//datos = datosNodo;
	nodoizquierdo = nododerecho = null; //el nodo no tiene hijos
    }

	public T getDatos() {
		return datos;
	}

	public void setDatos(T datos) {
		this.datos = datos;
	}

    
	Object getHojaIzquierda() {
		return nodoizquierdo;
	}

	void setHojaIzquierda(Nodo nodo) {
		nodoizquierdo=nodo;
	}

	Object getHojaDerecha() {
		return nododerecho;
	}

	void setHojaDerecha(Nodo nodo) {
		nododerecho=nodo;
	}

}
