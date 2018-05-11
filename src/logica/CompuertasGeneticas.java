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
		//arbol = new ArbolGeneticoPuertas(operaciones);
	}
	
	private void iniciarComp(){
		Operacion and = new And();
		Operacion not = new Not();
		Operacion or = new Or();
		Operacion xor = new Xor();
		
		operaciones.add(not);
		operaciones.add(and);
		operaciones.add(and);
		operaciones.add(or);
		operaciones.add(xor);
		operaciones.add(or);
		operaciones.add(xor);
		operaciones.add(xor);
		operaciones.add(and);
		
		/*for (int i = 0; i < 1000; i++) {
			operaciones.add(operaciones.get((int) (Math.random() *3))); 
			
		}*/
	
	}
	
	public void arrancar(){
	
		while(true){
			arbol = new ArbolGeneticoPuertas(operaciones);
			arbol.addNodo(4);
			arbol.recorridoPreorden2();
			System.out.println("cant:" + arbol.getCont());
			if(arbol.getCont()<=10& arbol.getCont()>7){
				break;
			}
		}
		
		
			Operacion oper = (Operacion) arbol.getRaiz().getDatos();
			System.out.print("Raiz"+"/"+oper.getNombre() + " "); 
		
		
		
		System.out.println("Preorden");	
		arbol.recorridoPreorden();
		System.out.println("\nInorden");
		arbol.recorridoInorden();
		System.out.println("\ncontador: " + arbol.getCont());
		
		}
	
	
	
}
