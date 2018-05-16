/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author philipretl
 */
public class Binario {
	int valor;
        int id;
        String letra;
        
    public Binario() {
        letra="";
        id=0;
        valor=0;
    }
    public int getId() {
        return id;
    }


    public int getValor() {
            return valor;
    }

    public void setValor(int valor) {
            this.valor = valor;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    


}
