/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import arbol.ArbolGeneticoPuertas;
import arbol.Nodo;
import compuertas.And;
import compuertas.Not;
import compuertas.Operacion;
import compuertas.Or;
import compuertas.Xor;
import java.util.ArrayList;

/**
 *
 * @author philipretl
 */
public class CompuertasGeneticas {
    // meter un array de arboles 
    private ArrayList<Operacion> operaciones;
   //ss private ArrayList<Integer> combinaciones;
    private ArrayList<ArbolGeneticoPuertas> arboles;
    private ArrayList<ArbolGeneticoPuertas> mutados;
    private int tabla[][];
    private int filas,columnas;
    private int individuos;
    private int generacion;
    
    
    public CompuertasGeneticas(int filas, int columnas,int individuos) {
        operaciones= new ArrayList();
        this.filas=filas;
        this.columnas=columnas;
        iniciarComp();
        tabla = new int[filas][columnas];
        arboles= new ArrayList();
        this.individuos=individuos;
        generacion=0;
        mutados=new ArrayList();
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
            arbol.addNodo(5);
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
        for (int i = 0; i < arboles.size(); i++) {
            ArbolGeneticoPuertas arbMut = new ArbolGeneticoPuertas(operaciones, generacion);
            mutados.add(arbMut);
            mutados.get(i).setBinarios(arboles.get(i).getBinarios());
            Nodo nuevoNodo = new Nodo(i);
            mutados.get(i).setRaiz(arboles.get(i).getRaiz());
            //arbMut.setRaiz(nuevoNodo);
            
            arbMut.llenarHojas(tabla, filas, columnas);// llena las hojas con los valores de la tabla 
            arbMut.calcularErrorParcial(tabla, filas, columnas);
            arbMut.calcularErrorTotal((columnas-1));
            
            
        }
        
       // indiceMut= (int) (individuos * 0.15);//
       indiceMut= (int) (individuos * 0.50);//borrar esto
       System.out.println("indice de mutacion: "+ indiceMut);
        
        for (int i = 0; i < indiceMut; i++) {
            random= (int) (Math.random() * mutados.size()-1);
            System.out.println("id del mutado:" + random);
            mutados.get(random).mutar(5);
        }
        
    }
    
    private void imprimirInfo(ArbolGeneticoPuertas arbol){
        
        Operacion oper = (Operacion) arbol.getRaiz().getDatos();
        System.out.print("\nRaiz"+"/"+oper.getNombre() + " "); 


        System.out.println("mutado: " + arbol.isMutado());
       
        System.out.println("\nPreorden");	
        arbol.recorridoPreorden();

        System.out.println("\nInorden");
        arbol.recorridoInorden();

        System.out.println("\nNumero de terminales:" + arbol.getBinarios().size() );
        System.out.println(" cant nodos:" + arbol.getCont());
        System.out.println("\nResultado: " + arbol.getErrorTotal());
    
    }
    
    public void arrancar(){
    
        manejadorGenetico();
        
       
        for (int i = 0; i <arboles.size(); i++) {
            System.out.println("original");
            System.out.println("---------------------" + "(" + i+ ")" );
            imprimirInfo(arboles.get(i));    
           
        }
        
        mutar();
        
       for (int p = 0; p < mutados.size(); p++) {
           System.out.println("mutados");
           System.out.println("---------------------" + "(" + p+ ")" );
           imprimirInfo(mutados.get(p));// la info del mutado
           
        }
        /*
        mutar();
          
        for (int i = 0; i <arboles.size(); i++) {
            System.out.println("original");
            System.out.println("---------------------" + "(" + i+ ")" );
            imprimirInfo(arboles.get(i));
            System.out.println("---------------------" + "(" + i+ ")" );
            imprimirInfo(mutados.get(i));// la info del mutado
            
           
        }
        */

        
        /* int x=0;
        while(x<50){    
            int cont=0;
            while(true){
                    arbol = new ArbolGeneticoPuertas(operaciones);
                    arbol.addNodo(5);
                    arbol.recorridoPreorden2();

                    if(arbol.getCont()<15& arbol.getCont()>3){
                            break;
                    }

                    /*Operacion oper = (Operacion) arbol.getRaiz().getDatos();
                    System.out.print("\nRaiz"+"/"+oper.getNombre() + " "); 


                    System.out.println("cant:" + arbol.getCont());
                    System.out.println("\nPreorden");	
                    arbol.recorridoPreorden();
                    System.out.println("\nInorden");
                    arbol.recorridoInorden();

                    cont++;
                    if (cont == 500000) {

                        break;
                    }      
            }


            Operacion oper = (Operacion) arbol.getRaiz().getDatos();
            System.out.print("\nRaiz"+"/"+oper.getNombre() + " "); 

            arbol.llenarHojas(tabla, filas, columnas);// llena las hojas con los valores de la tabla

            System.out.println("\ncant nodos:" + arbol.getCont());

            System.out.println("\nPreorden");	
            arbol.recorridoPreorden();
            System.out.println("\nInorden");
            arbol.recorridoInorden();
            //System.out.println("\nPosorden");
            //arbol.recorridoPosorden();
            //System.out.println("\ncontador: " + arbol.getCont());

            Nodo nod = arbol.getRaiz();

            //System.out.println("\nResultado: " + arbol.operarArbol(nod));




            System.out.println("\nNumero de terminales:" + arbol.getBinarios().size() );
            System.out.println("\nResultado: " + arbol.operarArbol(nod));

            x++;
        }
    */
    }
    
    public int[][] getTabla() {
        return tabla;
    }

    public void setTabla(int[][] tabla) {
        this.tabla = tabla;
    }
    
    
}
