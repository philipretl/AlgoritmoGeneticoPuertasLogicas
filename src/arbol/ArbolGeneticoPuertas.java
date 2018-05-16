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
    private ArrayList<Binario> binarios;
    private int cont;
    private int id=0;
    private double error;
    private ArrayList<String> abecedario;

	
     
    //construir un arbol vacio
    public ArbolGeneticoPuertas(ArrayList<Operacion> operaciones){
        this.operaciones=operaciones;
        binarios = new ArrayList();
        raiz = null;
        cont=0;
        abecedario = new ArrayList();
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        abecedario.add("a");
        abecedario.add("b");
        abecedario.add("c");
        abecedario.add("d");
        abecedario.add("e");
        abecedario.add("f");
        abecedario.add("g");
        abecedario.add("h");
        abecedario.add("i");
        abecedario.add("j");
    
    }
    
    
    public int getCont() {
            return cont;
    }

    public ArrayList<Binario> getBinarios() {
        return binarios;
    }

    public void setBinarios(ArrayList<Binario> binarios) {
        this.binarios = binarios;
    }
    
    

    private void addNodo( Nodo nodo, int altura) {
   
        Operacion oper = null;
        int numRand=0;
        int biRand=0;
        //boolean flag = nodo.getDatos() instanceof Integer;
        boolean flag = nodo.getDatos() instanceof Binario;
        
        if(flag){//se cumple que sea un numero sale, no puede llevar mas cosas debajo
            return;

        }else{

                //System.out.println("altura :" +altura + "entero? : "+ flag);	
            if(altura==0){//falta agregar el valor del nodo;//20.57

                if(!flag){
                        //System.out.println("ultimo nivel");
                        //biRand = (int) (Math.random()* 2); 
                        //biRand = (int) (Math.random() * binarios.size());  
                        
                        Binario bin= new Binario();
                        binarios.add(bin);
                        nodo.setDatos(binarios.get(binarios.size()-1));

                        addNodo(nodo,altura);
                }

                    //return;

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

                        }else{//ingresa un binario

                                /*biRand = (int) (Math.random()* 2); 
                                //System.out.println("bi rand: " + biRand);
                                
                                nodo1.setDatos(biRand);*/
                                Binario bin1= new Binario();
                                binarios.add(bin1);
                                nodo1.setDatos(binarios.get(binarios.size()-1));
                                
                                

                        }
                        biRand = (int) (Math.random()* 2); 
                            
                        if(biRand==0){// para el caso que sea not mira porque hoja se va a ir de manera aleatoria.

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

                                /*biRand = (int) (Math.random()* 2); 
                                //System.out.println("bi rand: " + biRand);

                                nodo2.setDatos(biRand);*/
                                Binario bin2= new Binario();
                                binarios.add(bin2);
                                nodo2.setDatos(binarios.get(binarios.size()-1));
                                
                                /*biRand = (int) (Math.random() * binarios.size());  
                                nodo2.setDatos(binarios.get(biRand));*/

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

                            /*biRand = (int) (Math.random()* 2); 
                            nodo3.setDatos(biRand);
                            //System.out.println("bi rand: " + biRand);nodo3.setDatos(biRand);
                            */
                            Binario bin3= new Binario();
                            binarios.add(bin3);
                            nodo3.setDatos(binarios.get(binarios.size()-1));
                            
                        }

                        nodo.setHojaDerecha(nodo3);
                        addNodo(nodo3,altura-1);


                    }


                }

            }
        }	

    }
    public void addNodo(int altura) {
        int numRand;
        Operacion oper;
        Nodo nodo = new Nodo(id);
        id++;	
        numRand = (int) (Math.random() * operaciones.size());  

        oper=operaciones.get(numRand);  

        //System.out.println("numRand" + numRand);
        nodo.setDatos(oper);

        this.addNodo(nodo,(altura-1));

    }
    
    public void llenarHojas(int tabla[][],int filas,int columnas){
        int rand;
        /**
         * Genera un aleatorio para asignar una letra aleatoria
         * a las hojas o nodos terminales
         */
        for (int p = 0; p < binarios.size(); p++) {
            
            rand= (int) (Math.random() * (columnas-1));// va la columba porque es la cantidad de letras que voy a usar
            binarios.get(p).setLetra(abecedario.get(rand));
            binarios.get(p).setId(rand);
        }
        
        
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                for (int k = 0; k < 10; k++) {
                    if(binarios.get(k).getId()==j){
                        binarios.get(k).setValor(tabla[i][j]);
                        break;
                    }
                }
                
            }
            
        }
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
			System.out.print("id: "+ nodo.getId() + "["+oper.getNombre() + "] "); 
		}else{
                        Binario bin = (Binario) nodo.getDatos();
			System.out.print("var: "+ bin.getLetra()  + "["+ bin.getValor()+"] ");	
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
                    System.out.print("id: "+ nodo.getId() + "["+oper.getNombre() +"] "); 
                }else{
                    Binario bin = (Binario) nodo.getDatos();
                    System.out.print("var: "+ bin.getLetra()  + "["+ bin.getValor()+"] ");	
		
			//System.out.print("id: "+ nodo.getId() + "["+nodo.getDatos()+"] ");	
		}
		
        
        ayudanteInorden(nodo.nododerecho);
    }
	
    public synchronized int operarArbol(Nodo nodo){

        if(nodo.getHojaIzquierda()== null || nodo.getHojaDerecha()==null){
            return 0;
        }else{
            if (nodo.getHojaIzquierda().getDatos() instanceof Integer & nodo.getHojaDerecha().getDatos() instanceof Integer){
                Operacion oper = (Operacion) nodo.getDatos();
                return oper.operar(nodo.getHojaIzquierda().getDatos(),nodo.getHojaDerecha().getDatos() );
            }else{

                if (!(nodo.getHojaIzquierda().getDatos() instanceof Integer) & !(nodo.getHojaDerecha().getDatos() instanceof Integer)){
                    Operacion oper = (Operacion) nodo.getDatos();
                    return oper.operar(operarArbol(nodo.getHojaIzquierda()),operarArbol(nodo.getHojaDerecha()));
                }else{	
                    if(!(nodo.getHojaIzquierda().getDatos() instanceof Integer)){
                        Operacion oper = (Operacion) nodo.getDatos();
                        return oper.operar(operarArbol(nodo.getHojaIzquierda()),nodo.getHojaDerecha().getDatos() );
                    }else{
                        Operacion oper= (Operacion) nodo.getDatos();
                        return oper.operar(operarArbol(nodo.getHojaDerecha()),nodo.getHojaIzquierda().getDatos() );	

                    }
                }
            }

        }
    }

   
}	
