/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import arbol.ArbolGeneticoPuertas;
import arbol.Nodo;
import arbol.Serialization;
import compuertas.And;
import compuertas.Not;
import compuertas.Operacion;
import compuertas.Or;
import compuertas.Xor;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author philipretl
 */
public class CompuertasGeneticas implements Serializable{
    // meter un array de arboles 
    private ArrayList<Operacion> operaciones;
   //ss private ArrayList<Integer> combinaciones;
    //private ArrayList<ArbolGeneticoPuertas> arboles;
    //private ArrayList<ArbolGeneticoPuertas> mutados;
    private List<ArbolGeneticoPuertas> arboles;
    private List<ArbolGeneticoPuertas> mutados;
    private int tabla[][];
    private int filas,columnas;
    private int individuos;
    private int generacion;
    private int nivel;
    
    
    public CompuertasGeneticas(int filas, int columnas,int individuos) {
        operaciones= new ArrayList();
        this.filas=filas;
        this.columnas=columnas;
        iniciarComp();
        tabla = new int[filas][columnas];
        //arboles= new ArrayList();
        arboles=new ArrayList<ArbolGeneticoPuertas>();
        this.individuos=individuos;
        generacion=0;
        //mutados=new ArrayList();
        mutados=new ArrayList<ArbolGeneticoPuertas>();
        nivel=10;
        //arbol = new ArbolGeneticoPuertas(operaciones);
    }

    public List<ArbolGeneticoPuertas> getArboles() {
        return arboles;
    }

    
    private void iniciarComp(){
        Operacion and = new And();
        Operacion not = new Not();
        Operacion or = new Or();
        Operacion xor = new Xor();
        
        
        operaciones.add(not);
        operaciones.add(and);
        operaciones.add(not);
        operaciones.add(and);
        operaciones.add(or);
        operaciones.add(xor);
        operaciones.add(or);
        operaciones.add(xor);
        operaciones.add(xor);
        operaciones.add(and);
        
       


    }
    
    /**
     * Se debe pedir la altura del arbol por la gui o dejarla generica
     */
    
    public void manejadorGenetico(){
        int cont=0;
        
        while(cont<individuos){
            /**
             * aqui debe ir la condicion de parada para que deje de generar arboles 
             * cuando el error sea poco 
             */
            
            ArbolGeneticoPuertas arbol = new ArbolGeneticoPuertas(operaciones,generacion);
            arbol.addNodo(nivel);
            arbol.llenarHojas(tabla, filas, columnas);// llena las hojas con los valores de la tabla 
            arbol.calcularErrorParcial(tabla, filas, columnas);
            arbol.calcularErrorTotal((columnas-1));
            arboles.add(arbol);
                
            cont++;
           
        }
        
        generacion=generacion+1;
        
    

        
    } 
  
    
    private void mutar(){
        
        int indiceMut=0;
        int random=0;
        //ArbolGeneticoPuertas arbMut;//relacion 1
        
        mutados=Serialization.copy(arboles);
              
       indiceMut= (int) (individuos * 0.15);//borrar esto
      // System.out.println("indice de mutacion: "+ indiceMut);
        
        for (int i = 0; i < indiceMut; i++) {
            random= (int) (Math.random() * mutados.size()-1);
           // System.out.println("id del mutado:" + random);
            mutados.get(random).mutar(nivel-1);
        }
        
        for (int i = 0; i < mutados.size(); i++) {
            mutados.get(i).setGeneracion(generacion);
            mutados.get(i).llenarHojasMut(tabla, filas, columnas);
            mutados.get(i).setErrorTotal(0);
            mutados.get(i).calcularErrorParcial(tabla, filas, columnas);
            mutados.get(i).calcularErrorTotal((columnas-1));
        }  
        generacion=generacion+1;
    }
    
  
    private void imprimirInfo(ArbolGeneticoPuertas arbol){
        
        Operacion oper = (Operacion) arbol.getRaiz().getDatos();
        System.out.print("\nRaiz"+"/"+oper.getNombre() + " "); 


        System.out.println("mutado: " + arbol.isMutado());
        System.out.println("cruzad " + arbol.isCruzado());
       
        System.out.println("\nPreorden");	
        String recorridoPreorden = arbol.recorridoPreorden();
        System.out.println("\n RECORRIDO PRE: "+ recorridoPreorden);

        System.out.println("\nInorden");
        arbol.recorridoInorden();

        System.out.println("\nNumero de terminales:" + arbol.getBinarios().size() );
        System.out.println(" cant nodos:" + arbol.getCont());
        System.out.println("\nError Total: " + arbol.getErrorTotal());
        System.out.println("\ngeneracion : " + arbol.getGeneracion());
    
    }
    
    public void arrancar(){
        int cont=0;
        manejadorGenetico();
        List<ArbolGeneticoPuertas> todos = Serialization.copy(arboles);
        
        while (cont < 100){
            
            mutar();
            cruzar();
            
            for (int i = 0; i < todos.size(); i++) {
                todos.remove(i);
            }
            
            

            for (int i = 0; i < mutados.size(); i++) {
               todos.add(i,Serialization.copy(mutados.get(i)));
            }
               
          
            
            for (int i = 0; i < arboles.size(); i++) {
              todos.add(i,Serialization.copy(arboles.get(i)));
            }
            
            Collections.sort(todos);  
     

             

            for (int i = 0; i < arboles.size(); i++) {
                arboles.set(i, Serialization.copy(todos.get(i)));
            }
              
            cont ++;  
        }   

        /*System.out.println("\nposible solucion");
        for (int i = 0; i < individuos; i++) {
            imprimirInfo(arboles.get(i));
        
        }*/
        
    }
    
   
    
    public void cruzar(){
        int randCruce=0;
        int randLvlCruce=0;
        int cont=0;
        
            for (int i = 0; i < mutados.size(); i++) {
                randCruce=(int) (Math.random()*mutados.size()-1);
                randLvlCruce=(int) (Math.random()*nivel-1);
                mutados.get(i).cruzar(mutados.get(randCruce).getRaiz(),nivel-1,randLvlCruce);
            }
            
             for (int i = 0; i < mutados.size(); i++) {

                //mutados.get(i).llenarHojasMut(tabla, filas, columnas);
                //mutados.get(i).setErrorTotal(0);
                mutados.get(i).calcularErrorParcial(tabla, filas, columnas);
                mutados.get(i).calcularErrorTotal((columnas-1));
            }
           
        
            generacion++;
    
    }
    
    public int[][] getTabla() {
        return tabla;
    }

    public void setTabla(int[][] tabla) {
        this.tabla = tabla;
    }

    
 
  
    
    
}
