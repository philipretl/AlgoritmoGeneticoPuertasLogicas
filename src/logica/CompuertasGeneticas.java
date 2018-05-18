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
        nivel=5;
        //arbol = new ArbolGeneticoPuertas(operaciones);
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
        
       
        
        
        
        /*for (int i = 0; i < 1000; i++) {
                operaciones.add(operaciones.get((int) (Math.random() *3))); 

        }*/

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
            // luego de crear el arbol crea su mutado.
            
           // System.out.println("cont:" + cont);
        }
        
        generacion=generacion+1;
        
    
       // System.out.println("tamaño del array de arbol: " +arboles.size());
        
    } 
  
    
    private void mutar(){
        
        int indiceMut=0;
        int random=0;
        //ArbolGeneticoPuertas arbMut;//relacion 1
        
        mutados=Serialization.copy(arboles);
              
       indiceMut= (int) (individuos * 0.50);//borrar esto
      // System.out.println("indice de mutacion: "+ indiceMut);
        
        for (int i = 0; i < indiceMut; i++) {
            random= (int) (Math.random() * mutados.size()-1)+1;
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
        arbol.recorridoPreorden();

        System.out.println("\nInorden");
        arbol.recorridoInorden();

        System.out.println("\nNumero de terminales:" + arbol.getBinarios().size() );
        System.out.println(" cant nodos:" + arbol.getCont());
        System.out.println("\nError Total: " + arbol.getErrorTotal());
        System.out.println("\ngeneracion : " + arbol.getGeneracion());
    
    }
    
    public void arrancar(){
        int cont=0;
        List<ArbolGeneticoPuertas> todos = Serialization.copy(arboles);;
        
        while (cont < 10){
            
            //mutados.get(0).setGeneracion(1);

            /* for (int i = 0; i <arboles.size(); i++) {
                 System.out.println("original");
                 System.out.println("---------------------" + "(" + i+ ")" );
                 System.out.println("raiz dir:"+arboles.get(i).getRaiz());
                 imprimirInfo(arboles.get(i));
                 System.out.println("---------------------" + "(" + i+ ")" );
                 System.out.println("raiz dir:"+mutados.get(i).getRaiz());
                 imprimirInfo(mutados.get(i));// la info del mutado

             }

             

             /*for (int g = 0; g <mutados.size(); g++) {
                 System.out.println("cruzados");
                 System.out.println("---------------------" + "(" + g+ ")" );
                 System.out.println("raiz dir:"+mutados.get(g).getRaiz());
                 imprimirInfo(mutados.get(g));// la info del mutado

             }*/

            //= new ArrayList<ArbolGeneticoPuertas>(); 
            manejadorGenetico();
            mutar();
            //cruzar();
            
            Collections.sort(arboles);
            Collections.sort(mutados);
             //todos= Serialization.copy(arboles);

            

            for (int i = 0; i < mutados.size(); i++) {

             todos.add(Serialization.copy(arboles.get(i)));
                todos.add(Serialization.copy(mutados.get(i)));
            }
               
            Collections.sort(todos);
            
            for (int i = 0; i < arboles.size(); i++) {
                arboles.set(i,todos.get(i));
            }
              
             //System.out.println("tamaño todos"+ todos.size());

             System.out.println("tamaño todos" + todos.size());
             


             /*for (int i = 0; i < individuos; i++) {
                 arboles.set(i,todos.get(i));
             }*/
              
            cont ++;  
        }   
          
       
       /*for (int i = 0; i < individuos; i++) {
            System.out.print("error total:" + arboles.get(i).getErrorTotal());
        }*/
        System.out.println("\nposible solucion");
        for (int i = 0; i < individuos/2; i++) {
            imprimirInfo(todos.get(i));
        
        }
        
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

                mutados.get(i).llenarHojasMut(tabla, filas, columnas);
                mutados.get(i).setErrorTotal(0);
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
