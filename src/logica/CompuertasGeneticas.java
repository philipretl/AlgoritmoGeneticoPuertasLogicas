/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import arbol.ArbolGeneticoPuertas;
import arbol.Nodo;
import compuertas.And;
import compuertas.Not;
import compuertas.Operacion;
import compuertas.Or;
import compuertas.Xor;
import java.util.ArrayList;

/**
 *
 * @author philipretl
 */
public class CompuertasGeneticas {
	private ArbolGeneticoPuertas arbol;
	private ArrayList<Operacion> operaciones;

	public CompuertasGeneticas() {
		operaciones= new ArrayList();
		iniciarComp();
		arbol = new ArbolGeneticoPuertas(operaciones);
	}
	
	private void iniciarComp(){
		Operacion and = new And();
		Operacion not = new Not();
		Operacion or = new Or();
		Operacion xor = new Xor();
		
		operaciones.add(and);
		operaciones.add(not);
		operaciones.add(or);
		operaciones.add(xor);
	
	}
	
	public void arrancar(){
		arbol.addNodo(5);
		System.out.println("Preorden");
		arbol.recorridoPreorden();
		System.out.println("Inorden");
		arbol.recorridoInorden();
		
	
	}
	
	
	
}
