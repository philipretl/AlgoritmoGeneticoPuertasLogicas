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
public class Not extends Operacion {

	public Not() {
		this.nombre="Not";
		this.operador="!";
		
	}



	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	@Override
	public String getOperador() {
		return operador;
	}

	@Override
	public void setOperador(String operador) {
		this.operador=operador;
	}

	@Override
	public void operar(int valor1, int valor2) {
		
	}
	
}
