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
    private int tabla[][];
    private int filas,columnas;
    private int individuos;
    
    public CompuertasGeneticas(int filas, int columnas,int individuos) {
        operaciones= new ArrayList();
        this.filas=filas;
        this.columnas=columnas;
        iniciarComp();
        tabla = new int[filas][columnas];
        arboles= new ArrayList();
        this.individuos=individuos;
        
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
    
    public void manejadorGenetico(){
        int cont=0;
        
        while(cont<individuos){
            
            ArbolGeneticoPuertas arbol = new ArbolGeneticoPuertas(operaciones);
            arbol.addNodo(5);
            Operacion oper = (Operacion) arbol.getRaiz().getDatos();
            System.out.print("\nRaiz"+"/"+oper.getNombre() + " "); 
        
            arbol.llenarHojas(tabla, filas, columnas);// llena las hojas con los valores de la tabla 
            /**
             * el metodo llenar hojas maneja tambien el calculo de los errores parciales y 
             * el error total + la penalizacion 
             */
        
            System.out.println("\nPreorden");	
            arbol.recorridoPreorden();
            
            System.out.println("\nInorden");
            arbol.recorridoInorden();
            
            System.out.println("\nNumero de terminales:" + arbol.getBinarios().size() );
             System.out.println(" cant nodos:" + arbol.getCont());
            System.out.println("\nResultado: " + arbol.getErrorTotal());
            
            arboles.add(arbol);
                
            cont++;
           // System.out.println("cont:" + cont);
        }
    
       // System.out.println("tamaño del array de arbol: " +arboles.size());
        
    } 
    
    
    public void arrancar(){
    
        manejadorGenetico();
        
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
