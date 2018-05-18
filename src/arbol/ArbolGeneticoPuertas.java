package arbol;

import java.util.ArrayList;
import compuertas.*;
import java.io.Serializable;
import static java.lang.Math.pow;
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
public class ArbolGeneticoPuertas implements Serializable{

    private Nodo raiz;
    private ArrayList<Operacion> operaciones;
    private ArrayList<Binario> binarios;
    private int cont;
    private int id;
    private double errorTotal;
    private ArrayList<Double> errores;
    private ArrayList<String> abecedario;
    private int generacion;
    private boolean mutado;

	
     
    //construir un arbol vacio
    public ArbolGeneticoPuertas(ArrayList<Operacion> operaciones,int generacion){
        this.operaciones=operaciones;
        binarios = new ArrayList();
        raiz = null;
        cont=0;
        errores = new ArrayList();
        abecedario = new ArrayList();
        iniciarComponentes();
        errorTotal=0;
        this.generacion=generacion;
        mutado=false;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public boolean isMutado() {
        return mutado;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public double getErrorTotal() {
        return errorTotal;
    }
    
    
    
    public void setRaiz(Nodo nodo) {
            raiz=nodo;
    }

    public Nodo getRaiz() {
            return raiz;
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
    
    
    public synchronized void mutar(int altura){
        int lvlMut=0;
        
        lvlMut=(int) (Math.random()*altura);// Ojo le puse +1 para ver que no mute la raiz
        
        if(lvlMut==altura){
           lvlMut=lvlMut-1;
        }
        System.out.println("altura: " + altura + " lvlMut:" + lvlMut);
        this.mutar2(this.raiz,altura-1,lvlMut);
        
    }
    
    private synchronized void mutar2(Nodo nodo, int altura,int lvlMut){// requiere que ya se hayan llenado las hojas.
        
        int randMut=0;
        int randBiMut=0;
        boolean flag = nodo.getDatos() instanceof Binario;
        
        /**
         * si se cumple que se llega a un binario pero aun falta niveles por
         * seguir bajando no se puede realizar la mutacion.
         */
        if(flag & altura!=lvlMut){
            return;
        }else{
            
            if(altura==lvlMut){
                randBiMut=(int) (Math.random()*100);

                if(randBiMut<50 & !mutado){
                 /**
                  * le voy a meter un numero a este nodo
                  */
                    if(!(nodo.getDatos() instanceof Binario)){
                        
                        randMut=(int) (Math.random()*(binarios.size()-1));
                        Binario bin= new Binario();
                        
                        /*if(randMut==binarios.size()){
                                    randMut=randMut-1;
                        }*/
                        bin.setLetra(binarios.get(randMut).getLetra());
                        bin.setValor(binarios.get(randMut).getValor());
                        nodo.setDatos(bin);
                        nodo.setHojaIzquierda(null);
                        nodo.setHojaDerecha(null);
                        mutado=true;//el arbol ya muto.
                        return;
   
                    }else{
                        while(true){


                                randMut=(int) (Math.random()*(binarios.size()-1));
                                
                                /*if(randMut==binarios.size()){
                                   
                                    randMut=randMut-1;
                                }*/
                                Binario bin= (Binario) nodo.getDatos();

                                if(binarios.get(randMut).getId() != bin.getId()){
                                    bin.setLetra(binarios.get(randMut).getLetra());
                                    bin.setValor(binarios.get(randMut).getValor());
                                    nodo.setDatos(bin);
                                    mutado=true;//el arbol ya muto.
                                    break;
                                    //return;
                                }

                        }
                    }    
                    
                    return;

                }else{// le voy a meter un operador
                    
                    if(!(nodo.getDatos() instanceof Binario)){
                        
                        while(true){ //no puedo meterle el mismo operador
                            
                            randMut = (int) (Math.random() * operaciones.size());  
                            Operacion operRand;
                            operRand= operaciones.get(randMut);  
                            
                            Operacion oper=(Operacion) nodo.getDatos();
                            if(!operRand.getNombre().equals(oper.getNombre())){
                                //no es el mismo entonces se puede meter
                                if(operRand instanceof Not){
                                    // si el nuevo es una instancia de not
                                    randBiMut=(int) (Math.random()*2);
                                    
                                    if(randBiMut==0){
                                        // elimina la rama derecha
                                        nodo.setDatos(operRand);
                                        nodo.setHojaDerecha(null);
                                        mutado=true;
                                        //return;
                                        break;
                                        
                                    }else{
                                        //elimina la rama izquierda
                                        nodo.setDatos(operRand);
                                        nodo.setHojaIzquierda(null);
                                        mutado=true;
                                        break;
                                        //return;
                                    }
                                    
                                   
                                    
                                }else{
                                    nodo.setDatos(operRand);
                                    mutado=true;
                                    break;
                                    //return;
                                
                                }
                            }
                        }
                        return;
                    }else{// es un binario el antiguo debera cambiar por un operador
                        
                        /**
                         * Como es un binario el angiuo tengo que pasarlo por el metodo 
                         * addNodo para que el operado dado el caso le meta las variables que le faltan
                         * para que se cumpla que no tenga problemas que siempre deben haber
                         * hojas con terminales
                         */
                        Operacion operRand;
                        randMut = (int) (Math.random() * operaciones.size());  
                        operRand= operaciones.get(randMut);  
                       
                        nodo.setDatos(operRand);
                        mutado=true;
                        
                        this.addNodo(nodo,altura-1);
                        return;
                    
                    }                      
                }
                
            
            }else{// no se llego al nivel toca seguir bajando
                
                if(nodo.getHojaIzquierda()!=null){
                    this.mutar2(nodo.getHojaIzquierda(), altura-1, lvlMut);
                }
                
                if(nodo.getHojaDerecha()!=null){
                    this.mutar2(nodo.getHojaDerecha(), altura-1, lvlMut);
                }
            }    
        }
    
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

            if(altura==0){

                if(!flag){
                       
                        
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
        
        
        
        for (int i = 0; i < filas; i++) { //este for recorre las filas
            //System.out.println("\npatron ");
            for (int j = 0; j < columnas-1; j++) {// recorre las columnas 
                for (int k = 0; k < binarios.size(); k++) {// recorre todos los binarios buscando la letra que le corresponde
                    if(binarios.get(k).getId()==j){
                        binarios.get(k).setValor(tabla[i][j]);
                        //break;// no va por que puede encontrarlo varias veces
                    }
                }
                
            }    
            
        }
        
        /**
         * al terminar de calcular los parciales calcula el total
         */
        
        
    }
    public  void calcularErrorParcial(int tabla[][],int filas,int columnas){
        
        for (int i = 0; i < filas; i++) { //este for recorre las filas
            //System.out.println("\npatron ");
            llenarErrorParcial(tabla[i][columnas-1]);
            
        }
        
    
    }
    
    public void calcularErrorTotal(int valores){
        double error=0;
        int cont=0;
        int numErr=0;
        for (int i = 0; i < errores.size(); i++) {
            error = error + errores.get(i);
        }
        
        errorTotal= error / errores.size();
        
        for (int i = 0; i < valores; i++) {
            
            for (int j = 0; j < binarios.size(); j++) {
                 if(binarios.get(j).getId()==i){
                    cont++;
                    break;
                 }
            }
        }
        
        numErr= valores - cont;
        
        errorTotal = errorTotal + (numErr * 0.1);
        
        
    }
    
    private void llenarErrorParcial(int esperado){
        int resultado;
        double error;
        resultado=operarArbol(raiz);
        //System.out.println("resultado: " + resultado + "esperado: " + esperado);
        error=pow((esperado - resultado),2);
        
        errores.add(error);
    
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
    private void ayudantePreorden2(Nodo nodo){
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
                System.out.print("id: " + nodo.getId() + " ("+ bin.getLetra()+") "  + "["+ bin.getValor()+"] ");	
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
            System.out.print("id: " + nodo.getId() + " ("+ bin.getLetra()+") "  + "["+ bin.getValor()+"] ");	

                //System.out.print("id: "+ nodo.getId() + "["+nodo.getDatos()+"] ");	
        }
        ayudanteInorden(nodo.nododerecho);
    }
   
    
    public synchronized int operarArbol(Nodo nodo){
        
        if(nodo.getHojaIzquierda()== null || nodo.getHojaDerecha()==null){
            return 0;
        }else{
      
            if (nodo.getHojaIzquierda().getDatos() instanceof Binario & nodo.getHojaDerecha().getDatos() instanceof Binario){
                Operacion oper = (Operacion) nodo.getDatos();
                Binario binIzq = (Binario) nodo.getHojaIzquierda().getDatos();
                Binario binDer = (Binario) nodo.getHojaDerecha().getDatos();
                return oper.operar(binIzq.getValor(),binDer.getValor());
            }else{

                if (!(nodo.getHojaIzquierda().getDatos() instanceof Binario) & !(nodo.getHojaDerecha().getDatos() instanceof Binario)){
                    Operacion oper = (Operacion) nodo.getDatos();
                    return oper.operar(operarArbol(nodo.getHojaIzquierda()),operarArbol(nodo.getHojaDerecha()));
                }else{	
                    if(!(nodo.getHojaIzquierda().getDatos() instanceof Binario)){
                        Operacion oper = (Operacion) nodo.getDatos();
                        Binario binDer= (Binario) nodo.getHojaDerecha().getDatos();
                        return oper.operar(operarArbol(nodo.getHojaIzquierda()),binDer.getValor() );
                    }else{
                        Operacion oper= (Operacion) nodo.getDatos();
                        Binario binIzq = (Binario) nodo.getHojaIzquierda().getDatos();
                        // duda porque puestamente la linea comentada debia ser que pase los datos
                        // a la izquierda y la derecha como no es hoja entonces deberia pasarle el nodo
                        //pero asi estaba trabajando
                        // se me ocurre que este error en la linea haya sido el causante del desbordamiento a causa de que al ser 
                        // numero era logico que debia parar y no apuntar a a la rama que apuntaba a nulo
                        //return oper.operar(operarArbol(nodo.getHojaDerecha()),binIzq.getValor());	
                        
                        /**
                         * Nueva linea supuestamente corregida
                         */
                        return oper.operar(binIzq.getValor(),operarArbol(nodo.getHojaDerecha()));
                    }
                }
            }

        }
    }
    
     /* esta recursion funcion debo cambiarla ahora a el tipo binario	
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
    }*/

   
}	
