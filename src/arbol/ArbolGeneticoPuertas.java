package arbol;

import java.util.ArrayList;
import compuertas.*;
import java.io.Serializable;
import static java.lang.Math.pow;
import java.util.Collections;
import java.util.Comparator;
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
public class ArbolGeneticoPuertas implements Serializable, Comparable<ArbolGeneticoPuertas>{

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
    private boolean cruzado;
    private boolean encontrado;
    
	
     
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
        cruzado=false;
        encontrado=false;
    }

    public boolean isCruzado() {
        return cruzado;
    }

    public void setCruzado(boolean cruzado) {
        this.cruzado = cruzado;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }
    
    
    public ArrayList<Operacion> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(ArrayList<Operacion> operaciones) {
        this.operaciones = operaciones;
    }

    public ArrayList<Double> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<Double> errores) {
        this.errores = errores;
    }

    public ArrayList<String> getAbecedario() {
        return abecedario;
    }

    public void setAbecedario(ArrayList<String> abecedario) {
        this.abecedario = abecedario;
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

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void setErrorTotal(double errorTotal) {
        this.errorTotal = errorTotal;
    }

    public void setMutado(boolean mutado) {
        this.mutado = mutado;
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
    
    public boolean cruzar(Nodo nodo,int altura,int lvlCruce){
        boolean flag=false;
        cruzar2(this.raiz,nodo,altura,altura,lvlCruce);
        
        return flag;
    }
    
    
    private synchronized void cruzar2(Nodo base,Nodo nodo, int alturaBas,int alturaNod,int lvlCruce){// requiere que ya se hayan llenado las hojas.
        int randCruce=0;
        
        if(alturaBas==0){
            return;
        }else{
        
            if(alturaBas==lvlCruce){

                if((base.getHojaIzquierda()!=null || base.getHojaDerecha()!=null) & !cruzado ){
                   randCruce=(int) (Math.random()*2);

                   if(randCruce ==0 ){// se la meto a la izquierda
                       randCruce=(int) (Math.random()*2);
                        if(randCruce==0){//se la metera a la derecha
                            if(base.getDatos()!=null){ 
                                if(base.getDatos()!=null){ 
                                    if(!(base.getDatos() instanceof Binario) & base.getHojaIzquierda()!= null){
                                            base.getHojaIzquierda().setHojaDerecha(cruzar3(nodo,alturaNod,lvlCruce));
                                            cruzado=true;
                                        }else{

                                            return;
                                        }
                                }    
                            }    
                        }else{
                            if(base.getDatos()!=null){
                                if(!(base.getDatos() instanceof Binario)  & base.getHojaIzquierda()!= null){
                                    base.getHojaIzquierda().setHojaIzquierda(cruzar3(nodo,alturaNod,lvlCruce));
                                    cruzado=true;
                                }else{

                                    return;
                                }
                            }    
                        }
                   }else{//la meto a la derecha
                       randCruce=(int) (Math.random()*2);
                        if(randCruce==0){//se la metera a la derecha
                            
                            if(base.getDatos()!=null){
                                if(!(base.getDatos() instanceof Binario)  & base.getHojaDerecha()!= null){
                                    base.getHojaDerecha().setHojaDerecha(cruzar3(nodo,alturaNod,lvlCruce));
                                    cruzado=true;
                                }else{

                                    return;
                                }
                            }else{
                                return;
                            }    
                        }else{
                            if(base.getDatos()!=null){
                                if(!(base.getDatos() instanceof Binario) & base.getHojaDerecha()!= null ){
                                    base.getHojaDerecha().setHojaIzquierda(cruzar3(nodo,alturaNod,lvlCruce));
                                    cruzado=true;
                                }else{

                                    return;
                                }
                            }else{
                                return;
                            }        
                        }


                   }

                }

                return;


            }else{

                if(base.getHojaIzquierda() != null){
                    cruzar2(base.getHojaIzquierda(),nodo,alturaBas-1,alturaNod,lvlCruce);
                }
                if(base.getHojaDerecha() != null){
                    cruzar2(base.getHojaDerecha(),nodo,alturaBas-1,alturaNod,lvlCruce);

                }    
            }
        }
    
    }
    
    private synchronized Nodo cruzar3(Nodo nodo,int altura, int lvlCruce){
        int randCruce=0;
        if(altura==lvlCruce){
            
            if(nodo.getDatos()!=null){
                if(!(nodo.getDatos() instanceof Binario) & !encontrado){
                    return nodo;
                }else{
                     randCruce=(int) (Math.random()*2);
                    if((nodo.getHojaIzquierda().getDatos() != null)){ 
                        if(randCruce == 0 & !(nodo.getHojaIzquierda().getDatos() instanceof Binario) & !encontrado){
                            return nodo.getHojaIzquierda();

                        }else{
                            if((nodo.getHojaDerecha().getDatos() != null)){ 
                                if(!encontrado & !(nodo.getHojaDerecha().getDatos() instanceof Binario))
                                    return nodo.getHojaDerecha();
                            }    
                        }
                    }else{

                        return null;
                    }
                }
            
        
            }else{

                cruzar3(nodo.getHojaIzquierda(),altura-1,lvlCruce);
                cruzar3(nodo.getHojaDerecha(),altura-1,lvlCruce);
            }
        }else{
            return null;
        }  
        return null;
    }
    
    public void mutar(int altura){
        int lvlMut=0;
        
        lvlMut=(int) (Math.random()*altura-1);
        
        /*if(lvlMut==altura){
           lvlMut=0;
        }*/
        //System.out.println("altura: " + altura + " lvlMut:" + lvlMut);
        this.mutar2(this.raiz,altura-1,lvlMut);
        
    }
    
    private synchronized void mutar2(Nodo nodo, int altura,int lvlMut){// requiere que ya se hayan llenado las hojas.
        Operacion operRand=null;
        int randMut=0;
        int randBiMut=0;
        boolean flag = nodo.getDatos() instanceof Binario;
        
        /**
         * si se cumple que se llega a un binario pero aun falta niveles por
         * seguir bajando no se puede realizar la mutacion.
         */
        if(flag & altura!=lvlMut){ //|| (altura==0 & flag)){
            return;
        
        }else{
            
            if(altura==lvlMut){
                randBiMut=(int) (Math.random()*2);

                if(randBiMut==1 & !mutado){
                 /**
                  * le voy a meter un numero a este nodo
                  */
                    if(flag){//&& !nodo.getDatos().equals(raiz)){
                        
                        randMut=(int) (Math.random()*binarios.size()-1);
                        Binario bin= (Binario) nodo.getDatos();
                        
                        /*if(randMut==binarios.size()){
                                    randMut=randMut-1;
                        }*/
                        bin.setLetra(binarios.get(randMut).getLetra());
                        bin.setValor(binarios.get(randMut).getValor());
                        nodo.setDatos(bin);
                        mutado=true;//el arbol ya muto.
                        //return;
   
                    }else{// es una operacion le corto el resto.
                        
                        randMut=(int) (Math.random()*binarios.size()-1);
                        Binario bin= new Binario();
                        int pos=0;
                        nodo.setDatos(bin);
                        
                        binarios.add(bin);
                        pos=binarios.size()-1;
                        binarios.get(pos).setLetra(binarios.get(randMut).getLetra());
                        binarios.get(pos).setValor(binarios.get(randMut).getValor());
                        binarios.get(pos).setId(binarios.size()-1);
                        
                        
                        
                        nodo.setHojaIzquierda(null);
                        nodo.setHojaDerecha(null);
                        //
                        mutado=true;//el arbol ya muto.
                    
                    }  
                    
                }else{// le voy a meter un operador
                    
                    if(!flag){ // es un operador
                        
                        while(true){ //no puedo meterle el mismo operador
                           
                            randMut = (int) (Math.random() * operaciones.size());  
                            operRand= operaciones.get(randMut);  
                            
                            Operacion oper=(Operacion) nodo.getDatos();
                            
                            if(!(operRand.getNombre().equals(oper.getNombre()))){
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
                       // return;
                   
                    }else{// es un
                        Binario bin = (Binario) nodo.getDatos();
                        
                        for (int i = 0; i <binarios.size() ; i++) {
                            if(bin.getId()==binarios.get(i).getId()){
                                binarios.remove(i);
                            }
                        }// remuevo el binario que ya no va a estar mas ahi
                        
                        randMut = (int) (Math.random() * operaciones.size());  
                        operRand= operaciones.get(randMut);  
                       
                        nodo.setDatos(operRand);
                        mutado=true;
                        this.addNodo(nodo, altura-1);
                       
                        //return;
                    
                    }                      
                }
                
            
            }else{// no se llego al nivel toca seguir bajando
                
                if(nodo.getHojaIzquierda()!=null){
                    mutar2(nodo.getHojaIzquierda(), altura-1, lvlMut);
                }
                
                if(nodo.getHojaDerecha()!=null){
                    mutar2(nodo.getHojaDerecha(), altura-1, lvlMut);
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
            
            rand= (int) (Math.random() * (columnas-1));// va la columbna porque es la cantidad de letras que voy a usar
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
    
    public void llenarHojasMut(int tabla[][],int filas,int columnas){
        int rand;
        /**
         * Genera un aleatorio para asignar una letra aleatoria
         * a las hojas o nodos terminales
         */
        for (int p = 0; p < binarios.size(); p++) {
            
            for (int l = 0; l < columnas-1; l++) {
                //System.out.println("id binario: "+binarios.get(p).getLetra());
                if(binarios.get(p).getId()==l){
                    binarios.get(p).setLetra(abecedario.get(l));
                      
                }
            }
            
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
        
        errorTotal = errorTotal + (numErr * 0.3);
        
        
    }
    
    private void llenarErrorParcial(int esperado){
        int resultado;
        double error;
        resultado=operarArbol(this.raiz);
        
        //System.out.println("resultado: " + resultado + "esperado: " + esperado);
        error=pow((esperado - resultado),2);
        
        errores.add(error);
    
    }

   	 
    // EMPIEZA EL RECORRIDO EN PREORDEN
    public synchronized String recorridoPreorden(){	
        cont=0;
        String recorrido="";
        recorrido=ayudantePreorden(raiz,recorrido);
        recorrido=recorrido+" )";
        return recorrido;
    }
	 
	 
    //meoto recursivo para recorrido en preorden
    


    private String ayudantePreorden(Nodo nodo,String recorrido){
    //manzano me la pela

        if(nodo == null)
            return recorrido;
        
        if (nodo.getDatos() instanceof Operacion){
                Operacion oper = (Operacion) nodo.getDatos();
                System.out.print("id: "+ nodo.getId() + "["+oper.getNombre() + "] "); 
                if(cont==0)
                    recorrido=recorrido+oper.getNombre()+" ( (";
                recorrido=recorrido+oper.getNombre()+" (";
        }else{
                Binario bin = (Binario) nodo.getDatos();
                System.out.print("id: " + nodo.getId() + " ("+ bin.getLetra()+") "  + "["+ bin.getValor()+"] ");
                recorrido=recorrido+bin.getLetra()+" ) ";
        }

        cont++;
            //mostrar datos del nodo
        recorrido=ayudantePreorden(nodo.nodoizquierdo,recorrido);   //recorre subarbol izquierdo
        recorrido=ayudantePreorden(nodo.nododerecho,recorrido);     //recorre subarbol derecho
        return recorrido;
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
    
   
	
     
   


    @Override
    public int compareTo(ArbolGeneticoPuertas o) {
        Double a=new Double(this.getErrorTotal());
        Double b=new Double(o.getErrorTotal());
        return a.compareTo(b);
        
        //return new Integer(p1.getEdad()).compareTo(new Integer(p2.getEdad()));
    }

   

   
}	
