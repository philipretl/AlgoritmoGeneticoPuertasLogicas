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
    ArrayList<Operacion> operaciones;
	int cont;
	int id=0;

	
     
    //construir un arbol vacio
    public ArbolGeneticoPuertas(ArrayList<Operacion> operaciones)
    {	this.operaciones=operaciones;
        raiz = null;
		cont=0;
    }
    
	public int getCont() {
		return cont;
	}
	
	private void addNodo( Nodo nodo, int altura) {
        /* 2.- Partiendo de la raíz preguntamos: Nodo == null ( o no existe ) ? */
		Operacion oper = null;
		int numRand=0;
		int biRand=0;
		boolean flag = nodo.getDatos() instanceof Integer;

		if(altura==0 || flag){// este para si viene numeros
			return;
		}else{

			if(!flag & altura==0){//falta agregar el valor del nodo;
				System.out.println("ultimo nivel");
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
							
							Nodo nodo1= new Nodo(id);
							id++;
							
							
							if(biRand==0){

								numRand = (int) (Math.random() * operaciones.size());  
								oper= operaciones.get(numRand);   
								nodo1.setDatos(oper);

							}else{

								biRand = (int) (Math.random()* 2); 
								//System.out.println("bi rand: " + biRand);

								nodo1.setDatos(biRand);

							}
							biRand = (int) (Math.random()* 2); 
								
							if(biRand==0){
							
								nodo.setHojaIzquierda(nodo1);
								addNodo(nodo1,altura-1);
							}else{
								
								nodo.setHojaDerecha(nodo1);
								addNodo(nodo1,altura-1);
							}
							
							

					}else{// fue un operador binario
						
						/**
						 * Agregar un numero u operacion a la rama izquierda
						 */
						biRand = (int) (Math.random()* 2); 
						Nodo nodo2= new Nodo(id);
						id++;
						
											
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
						
						/**
						 * Agregar un numero u operacion a la rama derecha
						 */ 
						
						biRand = (int) (Math.random()* 2); 
						Nodo nodo3= new Nodo(id);
						id++;
						
						if(biRand==0){

							numRand = (int) (Math.random() * operaciones.size());  
							oper= operaciones.get(numRand);   
							nodo3.setDatos(oper);

						}else{

							biRand = (int) (Math.random()* 2); 
							nodo3.setDatos(biRand);
							//System.out.println("bi rand: " + biRand);nodo3.setDatos(biRand);

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
		Nodo nodo = new Nodo(id);
		id++;	
		numRand = (int) (Math.random() * operaciones.size());  
		
		oper=operaciones.get(numRand);  
		
		System.out.println("numRand" + numRand);
		nodo.setDatos(oper);
		
		this.addNodo(nodo,(altura-1));
    
	}

	private void setRaiz(Nodo nodo) {
		raiz=nodo;
	}
	public Nodo getRaiz() {
		return raiz;
	}
	
	 
    // EMPIEZA EL RECORRIDO EN PREORDEN
    public synchronized void recorridoPreorden()
    {	cont=0;
        ayudantePreorden(raiz);
    }
	/**
	 * borrar esta mondad
	 */
	 public synchronized void recorridoPreorden2()
    {	cont=0;
        ayudantePreorden2(raiz);
		
		
    }
	 
	 /**
	  * borrar esta otra mondad
	  * 
	  */
	 private void ayudantePreorden2(Nodo nodo)
    {
        if(nodo == null)
            return;
        
		cont++;
		    //mostrar datos del nodo
		ayudantePreorden2(nodo.nodoizquierdo);   //recorre subarbol izquierdo
        ayudantePreorden2(nodo.nododerecho);     //recorre subarbol derecho
    }
	 
    //meoto recursivo para recorrido en preorden
    
    private void ayudantePreorden(Nodo nodo)
    {
        if(nodo == null)
            return;
        
		if (nodo.getDatos() instanceof Operacion){
			Operacion oper = (Operacion) nodo.getDatos();
			System.out.print("id: "+ nodo.getId() + "/"+oper.getNombre() + " "); 
		}else{
			System.out.print("id: "+ nodo.getId() + "/"+ nodo.getDatos()+" ");	
		}
		
		cont++;
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
		
		ayudanteInorden(nodo.nodoizquierdo);
		
		if (nodo.getDatos() instanceof Operacion){
			Operacion oper = (Operacion) nodo.getDatos();
			System.out.print("id: "+ nodo.getId() + "/"+oper.getNombre() + " "); 
		}else{
			System.out.print("id: "+ nodo.getId() + "/"+nodo.getDatos()+" ");	
		}
		
        
        ayudanteInorden(nodo.nododerecho);
    }
    
   
	
}
