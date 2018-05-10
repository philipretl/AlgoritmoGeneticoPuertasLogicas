package arbol;

import java.util.ArrayList;
import compuertas.*;
import logica.Binario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author philipretl
 */
public class ArbolGeneticoPuertas {

    private Nodo raiz;
    private ArrayList<Operacion> operaciones;
     
    //construir un arbol vacio
    public ArbolGeneticoPuertas(ArrayList<Operacion> operaciones)
    {	this.operaciones=operaciones;
        raiz = null;
    }
    
  private void addNodo( Nodo nodo, int altura) {
        /* 2.- Partiendo de la raíz preguntamos: Nodo == null ( o no existe ) ? */
	Operacion oper = null;
	int numRand=0;
	int biRand=0;
	boolean flag = nodo.getDatos() instanceof Operacion;
	
	if(altura==0){
		return;
	}else{
	
		if(flag & altura==0){//falta agregar el valor del nodo;
			biRand = (int) (Math.random()* 2); 
		   //System.out.println("bi rand: " + biRand);
		   nodo.setDatos(biRand); 
		   return;

		}else{
			if ( this.raiz == null ) {
				this.setRaiz(nodo);
				addNodo(raiz,altura);
			}
			else{

				if(nodo.getDatos() instanceof Not){// si es una instancia de not no se iria por las dos caminos
						biRand = (int) (Math.random()* 2); 
						Nodo nodo2= new Nodo();
						if(biRand==0){

							numRand = (int) (Math.random() * operaciones.size());  
							oper= operaciones.get(numRand);   
							nodo2.setDatos(oper);

						}else{

							biRand = (int) (Math.random()* 2); 
							//System.out.println("bi rand: " + biRand);

							nodo2.setDatos(biRand);

						}

						nodo.setHojaIzquierda(nodo2);
						addNodo(nodo2,altura-1);


				}else{// fue un operador binario
					biRand = (int) (Math.random()* 2); 
					Nodo nodo2= new Nodo();
					if(biRand==0){

						numRand = (int) (Math.random() * operaciones.size());  
						oper= operaciones.get(numRand);   
						nodo2.setDatos(oper);

					}else{

						biRand = (int) (Math.random()* 2); 
						//System.out.println("bi rand: " + biRand);

						nodo2.setDatos(biRand);

					}

					nodo.setHojaIzquierda(nodo2);
					addNodo(nodo2,altura-1);

					Nodo nodo3= new Nodo();
					if(biRand==0){

						numRand = (int) (Math.random() * operaciones.size());  
						oper= operaciones.get(numRand);   
						nodo3.setDatos(oper);

					}else{

						biRand = (int) (Math.random()* 2); 
						//System.out.println("bi rand: " + biRand);

						nodo3.setDatos(biRand);

					}

					nodo.setHojaDerecha(nodo3);
					addNodo(nodo3,altura-1);


				}


			}

		}
	}//cierra el else que va despues de preguntar si se llego a cero.	
	
  }
    public void addNodo(int altura) {
		int numRand;
		Operacion oper;
		Nodo nodo = new Nodo();
			
		numRand = (int) (Math.random() * operaciones.size()+1);  
		oper= operaciones.get(numRand);   

		nodo.setDatos(oper);
		
		this.addNodo( nodo,(altura-1));
    
	}

	private void setRaiz(Nodo nodo) {
		raiz=nodo;
	}
	
	 
    // EMPIEZA EL RECORRIDO EN PREORDEN
    public synchronized void recorridoPreorden()
    {
        ayudantePreorden(raiz);
    }
    //meoto recursivo para recorrido en preorden
     
    private void ayudantePreorden(Nodo nodo)
    {
        if(nodo == null)
            return;
        
		if (nodo.getDatos() instanceof Operacion){
			Operacion oper = (Operacion) nodo.getDatos();
			System.out.print(oper.getNombre() + " "); 
		}else{
			System.out.print(nodo.getDatos()+" ");	
		}
		
		    //mostrar datos del nodo
		ayudantePreorden(nodo.nodoizquierdo);   //recorre subarbol izquierdo
        ayudantePreorden(nodo.nododerecho);     //recorre subarbol derecho
    }
	
	 //EMPEZAR RECORRIDO INORDEN
    public synchronized void recorridoInorden()
    {
        ayudanteInorden(raiz);
    }
     
    //meoto recursivo para recorrido inorden
    private void ayudanteInorden(Nodo nodo)
    {
        if(nodo == null)
            return;
		if (nodo.getDatos() instanceof Operacion){
			Operacion oper = (Operacion) nodo.getDatos();
			System.out.print(oper.getNombre() + " "); 
		}else{
			System.out.print(nodo.getDatos()+" ");	
		}
		
        ayudanteInorden(nodo.nodoizquierdo);
        ayudanteInorden(nodo.nododerecho);
    }
    
   
	
}
