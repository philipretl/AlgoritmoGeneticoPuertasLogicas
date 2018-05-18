/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compuertas;

import java.io.Serializable;

/**
 *
 * @author philipretl
 */
public class And extends Operacion implements Serializable{
	
	public And() {
		this.nombre="And";
		this.operador="&";
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
	public int getResultado() {
		return resultado;
	}
    @Override 
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	@Override
	public int operar(Object valor1, Object valor2) {
		int result;
		if ((Integer)valor1==(Integer)valor2 & (Integer)valor1==1){
			result = 1;
		}else{
			result = 0;
		}
		this.setResultado(result);
		return result;
	}


	
	
}
