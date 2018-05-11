/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compuertas;

/**
 *
 * @author philipretl
 */
public abstract class Operacion<T>{
	protected String nombre;
	protected String operador;
	protected int resultado;

	public Operacion() {
		
	}
	
	public abstract String getNombre();

	public abstract void setNombre(String nombre);
	
	public abstract String getOperador();

	public abstract void setOperador(String operador);
	
	public abstract int operar(T valor1,T valor2);
	
	public abstract int getResultado();
	
	public abstract void setResultado(int resultado);
	
}
